package com.l.dynamic.datasource.Test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static String sout(Integer i) {
        String s = i + "";
        System.out.println(i);
        return s;
    }

    public static void main(String[] args) throws IOException {
        // String reg = "[^0-9]";
        // String location = "珍岛13号楼2楼";
        // Pattern compile = Pattern.compile(reg);
        // Matcher matcher = compile.matcher(location);
        // if (matcher.find()){
        //     //String s = matcher.group(1);
        //     String all = matcher.replaceAll("").trim();
        //     System.out.println(all);
        //     String building = all.substring(all.length()-1);
        //     System.out.println(building);
        //     String floor = all.substring(0,all.length()-1);
        //     System.out.println(floor);
        // }

        LocalDateTime minDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).with(DayOfWeek.MONDAY);
        LocalDateTime MaxDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX).with(DayOfWeek.SUNDAY);
        System.out.println(MaxDay);

        HashMap<DishTime, Integer> maps = new HashMap<>();

        DishTime dishTime1 = new DishTime();
        DishTime dishTime2 = new DishTime();
        //System.out.println("输出" + maps.get("1"));

        maps.put(dishTime1, 1);
        maps.put(dishTime2, 3);
        System.out.println(maps);
        System.out.println(LocalDateTime.now().minusDays(1));

        LocalDate localDate = LocalDate.now().minusDays(1);
        LocalTime localTime = LocalTime.now().minusHours(1);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println(localDateTime);

        String s = "113号楼12楼";
        String reg = "[0-9]{1,3}";
        Pattern compile = Pattern.compile(reg + "号");
        Pattern compile2 = Pattern.compile(reg + "楼");
        Matcher matcher = compile.matcher(s);
        Matcher matcher2 = compile2.matcher(s);
        if (matcher2.find()) {
            String af = "楼 + ： " + matcher2.group();
            String b = matcher2.group();
            System.out.println(af + " -- " + matcher2.group().length());
            int building = Integer.parseInt(b.substring(0, b.length() - 1));
            System.out.println(building);
        }

        if (matcher.find()) {
            String hao = "号 + : " + matcher.group();
            System.out.println(hao);
            String f = matcher.group();
            int floor = Integer.parseInt(f.substring(0, f.length() - 1));
            System.out.println(floor);
        }
        List<String> strings = new ArrayList<>();
        strings.add("?");
        System.out.println(strings.size());


        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        String k = String.valueOf(currentDate.getDayOfWeek());

        System.out.println("K->" + k);

        int openDay = 10;
        LocalDate startDate = LocalDate.now().plusDays(1);
        int a = 0;
        for (int i = 0; i < openDay; i++) {
            DayOfWeek week = startDate.plusDays(i).getDayOfWeek();
            String day = String.valueOf(week);

            if (day.equals("SATURDAY") || day.equals("SUNDAY")) {
                a++;
                openDay++;
            }
            System.out.println(day + " a = " + a);
        }

        System.out.println("a-->" + a);
    }

}
