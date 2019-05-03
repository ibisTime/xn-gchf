package com.cdkj.gchf.humanfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CallBackIdentity {

    @RequestMapping(value = "/Identity", method = RequestMethod.POST)
    @ResponseBody
    public String getIdentity(String deviceKey, String personGuid,
            String showTime, String photoUrl, String type, String data,
            String recMode, String idCardInfo) {
        System.out.println("======");

        System.out
            .println("device:" + deviceKey + "person" + personGuid + "showtime"
                    + showTime + "photoUrl" + photoUrl + "type" + type + "data"
                    + data + "recmode" + recMode + "idcardInfo" + idCardInfo);

        return "device:" + deviceKey + "person" + personGuid + "showtime"
                + showTime + "photoUrl" + photoUrl + "type" + type + "data"
                + data + "recmode" + recMode + "idcardInfo" + idCardInfo;
    }

}
