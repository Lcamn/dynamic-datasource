package com.l.dynamic.datasource.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public static String readFileByChars(String name) {
        StringBuilder builder = new StringBuilder();
        try {
            Resource resource = new ClassPathResource(name);
            InputStream is = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            // 一次读一个字符
            int tempchar;
            while ((tempchar = br.read()) != -1) {
                builder.append((char) tempchar);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
