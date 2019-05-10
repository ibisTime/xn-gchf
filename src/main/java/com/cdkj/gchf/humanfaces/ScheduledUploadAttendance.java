package com.cdkj.gchf.humanfaces;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.ao.IWorkerAttendanceAO;
import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.IProjectConfigBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IUserBO;
import com.cdkj.gchf.bo.IWorkerAttendanceBO;
import com.cdkj.gchf.bo.IWorkerEntryExitRecordBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.ProjectConfig;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.User;
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
    private IWorkerAttendanceAO workerAttendanceAO;

    @Autowired
    private IWorkerAttendanceBO workerAttendanceBO;

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    @Scheduled(cron = "0 0 18 * * ?")
    public void scheduleUploadAttendance() {
        User user = new User();
        user.setType("O");
        List<User> queryUserList = userBO.queryUserList(user);
        for (User tempUser : queryUserList) {
            // 获取每个项目实时考勤
            if (tempUser.getType().equals("P")) {
                continue;
            }
            String projectCode = tempUser.getOrganizationCode();
            ProjectConfig configByLocal = projectConfigBO
                .getProjectConfigByLocal(projectCode);
            if (configByLocal == null) {
                continue;
            }

            ProjectWorker condition = new ProjectWorker();
            condition.setProjectCode(user.getOrganizationCode());
            condition.setUploadStatus(
                EProjectWorkerUploadStatus.UPLOAD_UPDATE.getCode());
            // 项目人员
            List<ProjectWorker> queryProjectWorkerList = projectWorkerBO
                .queryProjectWorkerList(condition);

            for (ProjectWorker projectWorker : queryProjectWorkerList) {
                String workerCode = projectWorker.getWorkerCode();
                // 早晚考勤记录
                if (StringUtils.isBlank(workerCode)) {
                    continue;
                }
                WorkerEntryExitRecord morningEntryExitRecord = workerEntryExitRecordBO
                    .getMorningEntryExitRecord(projectCode, workerCode);

                WorkerEntryExitRecord afternoonEntryExitRecord = workerEntryExitRecordBO
                    .getAfternoonEntryExitRecord(projectCode, workerCode);
                if (morningEntryExitRecord == null
                        || afternoonEntryExitRecord == null) {
                    continue;
                }
                EquipmentInfo morningEquipmentInfo = equipmentInfoBO
                    .getEquipmentInfoByKey(
                        morningEntryExitRecord.getDeviceKey(), projectCode);
                EquipmentInfo afternoonEquipmentInfo = equipmentInfoBO
                    .getEquipmentInfoByKey(
                        afternoonEntryExitRecord.getDeviceKey(), projectCode);

                workerAttendanceBO.addWorkerAttendace(morningEntryExitRecord,
                    morningEquipmentInfo, morningEntryExitRecord.getDate(),
                    morningEntryExitRecord.getImage(),
                    morningEntryExitRecord.getAttendType());
                workerAttendanceBO.addWorkerAttendace(morningEntryExitRecord,
                    afternoonEquipmentInfo, afternoonEntryExitRecord.getDate(),
                    afternoonEntryExitRecord.getImage(),
                    afternoonEntryExitRecord.getAttendType());

                // 上传
                workerAttendanceAO.uploadWorkerAttendanceList(user.getUserId(),
                    Arrays.asList(morningEntryExitRecord.getCode(),
                        afternoonEntryExitRecord.getCode()));
            }

        }

    }
}
