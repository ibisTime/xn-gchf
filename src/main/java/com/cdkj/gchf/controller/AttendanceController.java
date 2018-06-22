package com.cdkj.gchf.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.ao.IAttendanceAO;

@Controller
public class AttendanceController {

    @Autowired
    IAttendanceAO attendanceAO;

    @RequestMapping(value = "/receive-attend", method = RequestMethod.POST)
    public void doClockIn(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        System.out.println("请求来啦");

        JSONObject json = JSONObject
            .parseObject(request.getParameter("json").toString());
        System.out.println(request.getParameter("json").toString());
        String sim = json.getString("sim");
        String staffCode = json.getString("id");
        String projectCode = json.getString("unit_code");
        String attendTime = json.getString("attend_time");
        String terminalCode = json.getString("terminal_code");
        // 提交考勤记录，考虑落地本地
        String result = attendanceAO.clockIn(sim, projectCode, staffCode,
            attendTime, terminalCode);

        /**
         * 1 没有考勤记录
         * 
         * 
         */
        // true 成功 false 失败
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.append(result);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
