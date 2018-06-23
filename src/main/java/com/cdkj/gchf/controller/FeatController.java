package com.cdkj.gchf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.gchf.http.BizConnecter;

@Controller
public class FeatController {

    @RequestMapping(value = "/getFeat", method = RequestMethod.GET)
    public void getFeat(HttpServletRequest request,
            HttpServletResponse response) {
        String str = null;
        try {
            str = URLDecoder.decode(request.getQueryString(), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        JSONObject json = JSONObject.parseObject(str);
        String result = null;
        result = BizConnecter.getFeat(json.getString("pict1"));

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
