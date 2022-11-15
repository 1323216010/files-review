package com.atguigu.ggkt.vod.utils;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class GithubPageHelper {

    private static final ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    public static Integer toInt(Object value)
    {
        return toInt(value, null);
    }

    public static String toStr(Object value, String defaultValue)
    {
        if (null == value)
        {
            return defaultValue;
        }
        if (value instanceof String)
        {
            return (String) value;
        }
        return value.toString();
    }

    public static Integer toInt(Object value, Integer defaultValue)
    {
        if (value == null)
        {
            return defaultValue;
        }
        if (value instanceof Integer)
        {
            return (Integer) value;
        }
        if (value instanceof Number)
        {
            return ((Number) value).intValue();
        }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr))
        {
            return defaultValue;
        }
        try
        {
            return Integer.parseInt(valueStr.trim());
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    public static Integer getParameterToInt(String name)
    {
        return toInt(requestAttributes.getRequest().getParameter(name));
    }

    public static void startPage() {

        Integer pageNum = getParameterToInt("pageNum");

        Integer pageSize = getParameterToInt("pageSize");

        PageHelper.startPage(pageNum, pageSize);
    }
}
