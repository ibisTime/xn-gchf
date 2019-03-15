package com.cdkj.gchf.bo;

import java.util.List;

import com.cdkj.gchf.bo.base.IPaginableBO;
import com.cdkj.gchf.domain.Message;

public interface IMessageBO extends IPaginableBO<Message> {
    // 添加代发消息
    public void saveMessage(String code, String projectCode, String month,
            Long totalAmount, Long totalCutAmount, Integer number);

    // 修改代发消息
    public void refreshMessage(Message data);

    // 删除工资时更新代发消息
    public void refreshMessage4DropSalary(String code, Long totalAmount,
            Long totalCutAmount, Long totalTax);

    public void sendMessage(Message data);

    public void approveMessage(Message data, String handler, String handleNote);

    public void downLoad(Message data);

    // 当不存在工资条时删除代发消息
    public void dropNotExistSalary(String code);

    public List<Message> queryMessageList(Message condition);

    public Message getMessage(String code);

}
