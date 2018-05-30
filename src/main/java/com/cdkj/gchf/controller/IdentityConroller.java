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
import com.cdkj.gchf.ao.IStaffAO;

@Controller
public class IdentityConroller {

    @Autowired
    public IStaffAO staffAO;

    @RequestMapping(value = "/ids/license", method = RequestMethod.GET)
    public void doTokenCallback(HttpServletRequest request,
            HttpServletResponse response) {
        JSONObject json = JSONObject
            .parseObject(request.getParameter("json").toString());
        if ("cdkjws".equalsIgnoreCase(json.getString("license"))) {
            String staffFeat = staffAO.getStaffFeatList();
            PrintWriter writer;
            try {
                writer = response.getWriter();
                writer.append(staffFeat);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
