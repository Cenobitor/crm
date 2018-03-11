package com.pri.crm.utils;

import org.apache.struts2.util.StrutsTypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 2:29 PM 27/02/2018
 * @Modified By:
 */

/**
 * 需在src下配置xwork-conversion.properties
 */
public class DateCoverter extends StrutsTypeConverter {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        try {

            return simpleDateFormat.parse(strings[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String convertToString(Map map, Object o) {
        return simpleDateFormat.format(o);
    }
}
