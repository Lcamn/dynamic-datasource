package com.l.dynamic.datasource.utils;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class DocUtil {
    public static String doc2String(InputStream fs) throws IOException {
        StringBuilder result = new StringBuilder();
        String[] split = null;
        if (FileMagic.valueOf(fs) == FileMagic.OLE2) {
            WordExtractor ex = new WordExtractor(fs);
            split = ex.getText().split("\n");
            ex.close();
        } else if (FileMagic.valueOf(fs) == FileMagic.OOXML) {
            XWPFDocument doc = new XWPFDocument(fs);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            result.append(extractor.getText());
            split = result.toString().split("\n");
            extractor.close();
        }
        return split[0];
    }

    public static String doc2String(File file) throws IOException {
        return doc2String(new BufferedInputStream((new FileInputStream(file))));
    }

    public static void main(String[] args) {
        File file = new File("E:\\测试Excel\\123.docx");
        try {
            System.out.println(doc2String(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
