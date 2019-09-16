package com.cdkj.gchf.humanfaces;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
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
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    @Autowired
    private IWorkerAttendanceAO workerAttendanceAO;

    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduleUploadMorningAttendance() {

        List<Project> projects = projectBO.queryProjectList(new Project());

        for (Project project : projects) {

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

                if (morningEntryExitRecord == null) {
                    continue;
                }
                EquipmentInfo morningEquipmentInfo = equipmentInfoBO
                    .getEquipmentInfoByKey(
                        morningEntryExitRecord.getDeviceKey());

                String code = workerAttendanceBO.addWorkerAttendace(morningEntryExitRecord,
                    morningEquipmentInfo, morningEntryExitRecord.getDate(),
                    morningEntryExitRecord.getImage(),
                    morningEntryExitRecord.getAttendType());

                List<String> codeList = new ArrayList<>();
                codeList.add(code);
                workerAttendanceAO.uploadWorkerAttendanceList("USYS201800000000001", codeList);
            }

        }

    }

    @Scheduled(cron = "0 0 19 * * ?")
    public void scheduleUploadNoonAttendance() {

        List<Project> projects = projectBO.queryProjectList(new Project());

        for (Project project : projects) {

            // 项目人员
            ProjectWorker condition = new ProjectWorker();
            condition.setProjectCode(project.getCode());
            condition.setUploadStatus(
                    EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode());
            List<ProjectWorker> queryProjectWorkerList = projectWorkerBO
                    .queryProjectWorkerList(condition);

            for (ProjectWorker projectWorker : queryProjectWorkerList) {

                WorkerEntryExitRecord afternoonEntryExitRecord = workerEntryExitRecordBO
                        .getAfternoonEntryExitRecord(project.getCode(),
                                projectWorker.getCode());
                if (afternoonEntryExitRecord == null) {
                    continue;
                }
                EquipmentInfo afternoonEquipmentInfo = equipmentInfoBO
                        .getEquipmentInfoByKey(
                                afternoonEntryExitRecord.getDeviceKey());

                String code = workerAttendanceBO.addWorkerAttendace(afternoonEntryExitRecord,
                        afternoonEquipmentInfo, afternoonEntryExitRecord.getDate(),
                        afternoonEntryExitRecord.getImage(),
                        afternoonEntryExitRecord.getAttendType());

                List<String> codeList = new ArrayList<>();
                codeList.add(code);
                workerAttendanceAO.uploadWorkerAttendanceList("USYS201800000000001", codeList);
            }

        }

    }
}
