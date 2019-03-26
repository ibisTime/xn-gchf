package com.cdkj.gchf.dto.req;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;

public class SerializeFilterHolder {

    public static SerializeFilter XN631916Filter() {
        return new PropertyFilter() {

            @Override
            public boolean apply(Object object, String name, Object value) {
                if (name.equals("unit")
                        && StringUtils.isBlank((String) value)) {
                    return false;
                }
                if (name.equals("unitPrice")
                        && StringUtils.isBlank((String) value)) {
                    return false;
                }
                return true;
            }

        };
    }
}
