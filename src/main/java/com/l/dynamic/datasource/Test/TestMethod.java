package com.l.dynamic.datasource.Test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;

public class TestMethod {


    public static String readFileByChars(String name) {
        StringBuilder builder = new StringBuilder();
        try {
            Resource resource = new ClassPathResource(name);
            InputStream is = resource.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
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

    public static void readFileByManyChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String name = "tiku.txt";
        System.out.println(readFileByChars(name));
    }

}
