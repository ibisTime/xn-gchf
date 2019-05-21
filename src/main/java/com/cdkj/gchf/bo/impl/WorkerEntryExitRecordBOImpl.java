package com.cdkj.gchf.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.gchf.bo.IWorkerEntryExitRecordBO;
import com.cdkj.gchf.bo.base.PaginableBOImpl;
import com.cdkj.gchf.common.DateUtil;
import com.cdkj.gchf.core.OrderNoGenerater;
import com.cdkj.gchf.dao.IWorkerEntryExitRecordDAO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.ProjectWorker;
import com.cdkj.gchf.domain.WorkerEntryExitRecord;
import com.cdkj.gchf.enums.EDeleteStatus;
import com.cdkj.gchf.enums.EGeneratePrefix;
import com.cdkj.gchf.exception.BizException;

@Component
public class WorkerEntryExitRecordBOImpl
        extends PaginableBOImpl<WorkerEntryExitRecord>
        implements IWorkerEntryExitRecordBO {

    @Autowired
    private IWorkerEntryExitRecordDAO workerEntryExitRecordDAO;

    @Override
    public String saveWorkerEntryExitRecord(EquipmentInfo equipmentInfo,
            ProjectWorker projectWorker, String date, String direction,
            String image, String recMode, String attendType) {
        WorkerEntryExitRecord workerEntryExitRecord = new WorkerEntryExitRecord();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.WorkerEntryExitRecord.getCode());

        workerEntryExitRecord.setCode(code);
        workerEntryExitRecord.setProjectCode(projectWorker.getProjectCode());
        workerEntryExitRecord.setProjectName(projectWorker.getProjectName());
        workerEntryExitRecord.setDeviceCode(equipmentInfo.getCode());
        workerEntryExitRecord.setDeviceKey(equipmentInfo.getDeviceKey());

        workerEntryExitRecord.setDeviceName(equipmentInfo.getName());
        workerEntryExitRecord.setTeamCode(projectWorker.getTeamSysNo());
        workerEntryExitRecord.setTeamName(projectWorker.getTeamName());
        workerEntryExitRecord.setWorkerCode(projectWorker.getCode());
        workerEntryExitRecord.setWorkerName(projectWorker.getWorkerName());

        workerEntryExitRecord.setIdcardNumber(projectWorker.getIdcardNumber());
        workerEntryExitRecord
            .setDate(DateUtil.strToDate(date, DateUtil.DATA_TIME_PATTERN_1));
        workerEntryExitRecord.setDirection(direction);
        workerEntryExitRecord.setImage(image);
        workerEntryExitRecord.setRecMode(recMode);

        workerEntryExitRecord.setAttendType(attendType);
        workerEntryExitRecord.setDeleteStatus(EDeleteStatus.NORMAL.getCode());

        workerEntryExitRecordDAO.insert(workerEntryExitRecord);
        return code;
    }

    @Override
    public List<WorkerEntryExitRecord> queryWorkerEntryExitRecordList(
            WorkerEntryExitRecord condition) {
        return workerEntryExitRecordDAO.selectList(condition);
    }

    @Override
    public WorkerEntryExitRecord getWorkerEntryExitRecord(String code) {
        WorkerEntryExitRecord data = null;
        if (StringUtils.isNotBlank(code)) {
            WorkerEntryExitRecord condition = new WorkerEntryExitRecord();
            condition.setCode(code);
            data = workerEntryExitRecordDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "人员进程记录不存在");
            }
        }
        return data;
    }

    @Override
    public WorkerEntryExitRecord getMorningEntryExitRecord(String projectCode,
            String workerCode) {
        WorkerEntryExitRecord workerEntryExitRecord = new WorkerEntryExitRecord();
        workerEntryExitRecord.setProjectCode(projectCode);
        workerEntryExitRecord.setWorkerCode(workerCode);
        return workerEntryExitRecordDAO
            .selectMorningRecoder(workerEntryExitRecord);
    }

    @Override
    public WorkerEntryExitRecord getAfternoonEntryExitRecord(String projectCode,
            String workerCode) {
        WorkerEntryExitRecord workerEntryExitRecord = new WorkerEntryExitRecord();
        workerEntryExitRecord.setProjectCode(projectCode);
        workerEntryExitRecord.setWorkerCode(workerCode);
        return workerEntryExitRecordDAO
            .selectAfternoonRecoder(workerEntryExitRecord);
    }

    @Override
    public Long selectRecordCountByUserId(String userId) {
        return workerEntryExitRecordDAO.selectProjectRecordCount(userId);
    }
}
