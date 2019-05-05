package com.cdkj.gchf.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportUtil {

    private final static Logger log = LoggerFactory.getLogger(ImportUtil.class);

    public static <E> String checkRepeat(List<E> dateList, String columnName) {

        if (CollectionUtils.isEmpty(dateList)) {
            return null;
        }

        try {
            List<String> tempList = new ArrayList<>();
            String getMethod = "get" + columnName.substring(0, 1).toUpperCase()
                    + columnName.substring(1);

            for (E date : dateList) {

                Method method = date.getClass().getMethod(getMethod);

                String tempValue = method.invoke(date).toString();

                if (!tempList.contains(tempValue)) {
                    tempList.add(tempValue);
                } else {
                    return tempValue;
                }

            }
        } catch (Exception e) {
            log.info("导入数据校验失败:{}", e.getMessage());
        }

        return null;

    }

}
