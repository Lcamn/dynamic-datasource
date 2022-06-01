package com.l.dynamic.datasource.Tools;

public class TestMail {
    public static void test() {
        int i = 0;
        int MAX_COUNT = 10;

        long start = System.currentTimeMillis();
        while (i < MAX_COUNT) {
            // 以下是每封邮件不同的地方
            Mail mail = new Mail();
            mail.setTail(",先生（女士）:你的信用卡账单...");
            mail.setSubject("@");
            // 然后发送邮件
            sendMail(mail);
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println("用时:" + (end - start));
    }

    public static void testClone() throws CloneNotSupportedException {
        int i = 0;
        int MAX_COUNT = 10;
        long start = System.currentTimeMillis();
        Mail mail = new Mail();
        while (i < MAX_COUNT) {
            // 以下是每封邮件不同的地方
            Mail cloneMail = mail.clone();
            cloneMail.setTail(",先生（女士）:你的信用卡账单2...");
            cloneMail.setSubject("@2");
            // 然后发送邮件
            sendMail(cloneMail);
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println("用时:" + (end - start));
    }

    private static void sendMail(Mail mail) {
        //
        System.out.println(mail);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        test();
        testClone();
    }
}
