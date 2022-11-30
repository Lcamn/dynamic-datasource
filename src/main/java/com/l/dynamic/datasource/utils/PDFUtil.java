package com.l.dynamic.datasource.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PDFUtil {

    public static void splitPDF() throws Exception {
        Document document;
        PdfCopy copy;
        String pdfFile = "E:\\临时文件夹\\测试写文件\\write1 - 副本.pdf";

        PdfReader reader = new PdfReader(pdfFile);
        int from = 2;
        int n = reader.getNumberOfPages();
        List<String> savePaths = new ArrayList<>();
        int a = pdfFile.lastIndexOf(".pdf");
        String staticPath = pdfFile.substring(0, a);
        String savePath = staticPath + "_from_" + from + "_to_" + n + "_.pdf";
        savePaths.add(savePath);
        document = new Document(reader.getPageSize(1));
        copy = new PdfCopy(document, Files.newOutputStream(Paths.get(savePaths.get(0))));
        document.open();
        for (int j = from; j <= n; j++) {
            document.newPage();
            PdfImportedPage page = copy.getImportedPage(reader, j);
            copy.addPage(page);
        }
        document.close();

    }


    /**
     * 去掉PDF的加密
     *
     * @param sourceFullName 源文件路径
     * @param newFullName    目标文件路径
     */
    public static void deletePDFEncrypt(String sourceFullName, String newFullName) throws Exception {


        // 创建一个PdfReader对象
        PdfReader reader = new PdfReader(sourceFullName);
        PdfReader.unethicalreading = true;
        // 获得文档页数
        int n = reader.getNumberOfPages();
        // 获得第一页的大小
        Rectangle pagesize = reader.getPageSize(1);

        // 创建一个文档变量
        Document document = new Document(pagesize, 50, 50, 50, 50);
        // 创建该文档
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(newFullName)));
        // 打开文档
        document.open();
        // 添加内容

        PdfContentByte cb = writer.getDirectContent();
        int i = 0;

        while (i < n) {
            document.newPage();
            i++;
            PdfImportedPage page1 = writer.getImportedPage(reader, i);
            cb.addTemplate(page1, 1f, 0, 0, 1f, 0, 0);
        }
        // 关闭文档
        document.close();
        System.out.println("完成");
    }
}
