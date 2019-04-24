package com.cdkj.gchf.proxy;

import com.cdkj.gchf.exception.BizException;
import com.cdkj.gchf.exception.EBizErrorCode;

public class ReflectUtil {

    public static Object getInstance(String classname) {
        Object result = null;
        try {
            Class cls = Class.forName(classname);
            if (cls != null) {
                result = cls.newInstance();// 被代理对象
            }
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "找不到类" + classname);
        }
        return result;
    }
}
