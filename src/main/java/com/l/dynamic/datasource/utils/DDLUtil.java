package com.l.dynamic.datasource.utils;

import org.springframework.util.ObjectUtils;

public class DDLUtil {
    public static String ddl2Json(String ddl, Boolean upCase) {
        if (ObjectUtils.isEmpty(ddl)) {
            return null;
        }
        ddl = ddl.substring(ddl.indexOf("(") + 1, ddl.lastIndexOf(")"));
        String[] strings = ddl.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (String one : strings) {
            String trim = one.trim();
            String substring = trim.substring(0, trim.indexOf(" "));

            sb.append("\"").append(substring).append("\"").append(":\"\"").append(",");
        }
        String re = sb.substring(0, sb.length() - 1) + "}";
        System.out.println(re);
        return re;
    }
}
