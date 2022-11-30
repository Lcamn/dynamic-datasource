package com.l.dynamic.datasource.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;

public class Test {
    private static final String FONT = "C:\\Windows\\Fonts\\simhei.ttf";


    public static void main(String[] args) throws Exception {

        // multiplication();
        // AdditionAndSubtraction();


        String path = "C:\\Users\\L\\Desktop\\新建文件夹 (2)\\";
        String targetPath = "C:\\Users\\L\\Desktop\\新建文件夹 (2)\\解密\\";
        String lastPath = "C:\\Users\\L\\Desktop\\新建文件夹 (2)\\剪裁\\";
        File file = new File(path);
        File[] files = file.listFiles();

        File lastFile = new File(targetPath);
        File[] lastFiles = lastFile.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                continue;
            }
            String newName = f.getName();
            String oldName = f.getName();

            newName = newName.replaceAll("（", "(").replaceAll("）", ")");
            newName = newName.substring(0, 5) + newName.substring(newName.indexOf("("), newName.indexOf(")") + 1) + ".pdf";
            PDFUtil.deletePDFEncrypt(path + oldName, targetPath + newName);
        }

        for (File f : lastFiles) {
            if (f.isDirectory()) {
                continue;
            }

            String oldName = f.getName();
            int year = Integer.parseInt(oldName.substring(0, 4));
            if (year < 2021) {
                PDFUtil.splitPDF(targetPath + oldName, lastPath + oldName);
            }

        }


        System.out.println("all done！");

    }

    public static void AdditionAndSubtraction() throws Exception {
        Random random = new Random();
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayList<String> questionList = new ArrayList<>();
        String path = "E:\\临时文件夹\\测试写文件\\write1.txt";
        int sum = 1000;
        for (int i = 0; i < sum; i++) {
            int j = random.nextInt(1000);
            int k = random.nextInt(1000);
            int s = random.nextInt(2);// 0:减法 1：加法

            if (j < k) {
                sum += 1;
                continue;
            }
            String question = "";
            if (s == 0) {
                question = j + "-" + k + "=___";
                answer.add(j - k);
            } else {
                question = j + "+" + k + "=___";
                answer.add(j + k);
            }
            questionList.add(question);
        }
        StringBuilder questionBuild = new StringBuilder();
        StringBuilder answerBuild = new StringBuilder();
        for (int i = 0; i < answer.size(); i++) {
            questionBuild.append("(").append(i).append("). ").append(questionList.get(i)).append("    ");
            answerBuild.append("(").append(i).append("). ").append(answer.get(i)).append("    ");
            if (i % 5 == 0) {
                answerBuild.append("\n");
            }
            if (i % 3 == 0) {
                questionBuild.append("\n");
            }

        }

        String write = questionBuild + "\n" + answerBuild;
        WriteUtil.fileWriterTest(path, write);
        String pre = path.substring(0, path.lastIndexOf("."));
        text2pdf(path, pre + ".pdf");
        System.out.println("完毕");
    }

    public static void multiplication() {
        int j = 27;

        for (int i = 11; i < j; i++) {
            for (int k = 2; k < i; k++) {
                System.out.print(k + "X" + i + "=" + i * k + " ");
            }
            System.out.println("");
        }

    }

    public static void text2pdf(String text, String pdf) throws Exception {
        Document document = new Document();
        OutputStream os = Files.newOutputStream(new File(pdf).toPath());
        PdfWriter.getInstance(document, os);
        document.open();
        //方法一：使用Windows系统字体(TrueType)
        BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
        InputStreamReader isr = new InputStreamReader(Files.newInputStream(new File(text).toPath()), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(isr);
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            document.add(new Paragraph(str, font));
        }
        document.close();
    }


}
