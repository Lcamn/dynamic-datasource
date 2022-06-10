package com.l.dynamic.datasource.utils;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;

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

    public static String wps2String(InputStream fs) throws IOException {
        Document document = new Document();
        document.loadFromStream(fs, FileFormat.Auto);
        String[] split = document.getText().split("\n");
        document.close();
        fs.close();
        return ObjectUtils.isEmpty(split[0].replaceAll("\r", "")) ?
                split[1].replaceAll("\r", "") : split[0].replaceAll("\r", "").trim();
    }


    public static String doc2String(File file) throws IOException {
        //wps 兼容 office
        return wps2String(new BufferedInputStream((new FileInputStream(file))));
    }

    public static String zip(String filePath, String subject, int count) throws Exception {
        File file = new File(filePath);
        String outputFIleName = filePath + subject + count + "篇.zip";
        ArrayList<File> fileList = new ArrayList<>();
        if (file.isDirectory()) {
            fileList.addAll(Arrays.asList(file.listFiles()));
        } else {
            fileList.add(file);
        }
        FileInputStream fileInputStream = null;
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(new FileOutputStream(outputFIleName), new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
        for (File f : fileList) {
            if (f.isDirectory()) {
                continue;
            }
            zipOutputStream.putNextEntry(new ZipEntry(f.getName()));
            fileInputStream = new FileInputStream(f);
            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                zipOutputStream.write(bytes, 0, len);
            }
        }
        fileInputStream.close();
        zipOutputStream.close();
        System.out.println("打包完成。 " + outputFIleName);
        return outputFIleName;
    }


    public static void main(String[] args) {
        File file = new File("C:\\Users\\L\\Desktop\\新建文件夹\\2\\123.docx");
        try {
            System.out.println(doc2String(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
