package com.cdkj.gchf.humanfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdkj.gchf.bo.IEquipmentInfoBO;
import com.cdkj.gchf.bo.IProjectWorkerBO;
import com.cdkj.gchf.bo.IWorkerEntryExitRecordBO;
import com.cdkj.gchf.domain.EquipmentInfo;
import com.cdkj.gchf.domain.ProjectWorker;

/**
 * @ClassName: CallBackIdentity
 * @Description:人脸识别回调
 * @author: Old3
 * @date: 2019年5月6日 下午5:13:40
 * @Copyright:
 */
@Controller
public class CallBackIdentity {

    @Autowired
    private IProjectWorkerBO projectWorkerBO;

    @Autowired
    private IWorkerEntryExitRecordBO workerEntryExitRecordBO;

    @Autowired
    private IEquipmentInfoBO equipmentInfoBO;

    /**
     * @Description: 回调函数
     * @param: @param deviceKey 设备序列号
     * @param: @param personGuid 人员id
     * @param: @param showTime 识别时间
     * @param: @param photoUrl 现场照url
     * @param: @param type 识别出的人员分类
     * @param: @param data 其他数据
     * @param: @param recMode 识别模式
     * @param: @param idCardInfo 人证比对详细
     */
    @RequestMapping(value = "/Identity", method = RequestMethod.POST)
    @ResponseBody
    public Object getIdentity(@RequestParam String deviceKey,
                              @RequestParam String personGuid, @RequestParam String showTime,
                              @RequestParam String photoUrl, @RequestParam String type,
                              @RequestParam String data, @RequestParam String recMode,
                              @RequestParam String guid, @RequestParam String userGuid,
                              @RequestParam(value = "idCardInfo", defaultValue = "", required = false) String idCardInfo) {

        ProjectWorker workerByGuid = projectWorkerBO
                .getProjectWorkerByGuid(personGuid, deviceKey);
        if (null != workerByGuid) {

            EquipmentInfo equipmentInfo = equipmentInfoBO
                    .getEquipmentInfoByKey(deviceKey);
            // 生成进出记录
            workerEntryExitRecordBO.saveWorkerEntryExitRecord(equipmentInfo,
                    workerByGuid, showTime, equipmentInfo.getDirection(), photoUrl,
                    recMode, type);

        }

        return "SUCCESS";
    }

}
