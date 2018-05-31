package com.cdkj.gchf.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdkj.gchf.ao.IAttendanceAO;

@Controller
public class AttendanceController {

    @Autowired
    IAttendanceAO attendanceAO;

    @RequestMapping(value = "/doClockIn", method = RequestMethod.POST)
    public void doClockIn(HttpServletRequest request,
            HttpServletResponse response) {
        String staffCode = request.getParameter("id");
        String projectCode = request.getParameter("unit_code");
        String attendTime = request.getParameter("attend_time");
        String result = attendanceAO.clockIn(projectCode, staffCode,
            attendTime);
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
