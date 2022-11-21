package com.l.dynamic.datasource.utils;

import org.springframework.util.ObjectUtils;

public class Test {
    public static void main(String[] args) {

        String ddl = "create table tb_digital_dictionary_classify\n" +
                "(\n" +
                "    id          bigint(11)   not null comment '主键'\n" +
                "        primary key,\n" +
                "    dict_number varchar(20)  null comment '字典编号',\n" +
                "    dict_name   varchar(100) null comment '字典名称',\n" +
                "    status      int(20)      null comment '状态(1:启用 2：禁用)',\n" +
                "    remark      varchar(500) null comment '备注',\n" +
                "    create_by   bigint       null comment '创建人id',\n" +
                "    create_at   datetime     null comment '创建时间',\n" +
                "    update_by   bigint       null comment '更新人',\n" +
                "    update_at   datetime     null comment '更新时间'\n" +
                ")\n" +
                "    comment '数字字典表分类';";

        tt(ddl, false);
    }

    public static String tt(String ddl, Boolean upCase) {
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
            // System.out.println(substring);

            sb.append("\"").append(substring).append("\"").append(":\"\"").append(",");
        }
        String re = sb.substring(0, sb.length() - 1) + "}";
        System.out.println(re);
        return re;
    }
}
