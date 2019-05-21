package com.cdkj.gchf.humanfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.IProjectBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.IWorkerEntryExitRecordBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.Project;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;
import com.cdkj.gchf.enums.EProjectWorkerUploadStatus;

/**
 * 
 * @ClassName:  ScheduledUploadAttendance   
 * @Description:TODO
 * @author: Old3
 * @date:   2019年5月9日 下午7:15:53     
 * @Copyright:
 */
@Component
public class ScheduledUploadAttendance {
    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProjectConfigBO projectConfigBO;

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IWorkerEntryExitRecordBO workerEntryExitRecordBO;

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    @Autowired
    private IProjectBO projectBO;

    @Scheduled(cron = "0 0 18 * * ?")
    public void scheduleUploadAttendance() {

        List<Project> projects = projectBO.queryProjectList(new Project());

        for (Project project : projects) {
            if (!"P201904241633378691561".equals(project.getCode())) {
                continue;
            }

            // 项目人员
            ProjectWorker condition = new ProjectWorker();
            condition.setProjectCode(project.getCode());
            condition.setUploadStatus(
                EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode());
            List<ProjectWorker> queryProjectWorkerList = projectWorkerBO
                .queryProjectWorkerList(condition);

            for (ProjectWorker projectWorker : queryProjectWorkerList) {

                WorkerEntryExitRecord morningEntryExitRecord = workerEntryExitRecordBO
                    .getMorningEntryExitRecord(project.getCode(),
                        projectWorker.getCode());

                WorkerEntryExitRecord afternoonEntryExitRecord = workerEntryExitRecordBO
                    .getAfternoonEntryExitRecord(project.getCode(),
                        projectWorker.getCode());
                if (morningEntryExitRecord == null
                        || afternoonEntryExitRecord == null) {
                    continue;
                }
                EquipmentInfo morningEquipmentInfo = equipmentInfoBO
                    .getEquipmentInfoByKey(
                        morningEntryExitRecord.getDeviceKey());
                EquipmentInfo afternoonEquipmentInfo = equipmentInfoBO
                    .getEquipmentInfoByKey(
                        afternoonEntryExitRecord.getDeviceKey());

                workerAttendanceBO.addWorkerAttendace(morningEntryExitRecord,
                    morningEquipmentInfo, morningEntryExitRecord.getDate(),
                    morningEntryExitRecord.getImage(),
                    morningEntryExitRecord.getAttendType());
                workerAttendanceBO.addWorkerAttendace(afternoonEntryExitRecord,
                    afternoonEquipmentInfo, afternoonEntryExitRecord.getDate(),
                    afternoonEntryExitRecord.getImage(),
                    afternoonEntryExitRecord.getAttendType());
            }

        }

    }
}
