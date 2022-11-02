package com.l.dynamic.datasource.Tools;

import com.l.dynamic.datasource.utils.DocUtil;
import org.apache.commons.lang3.ObjectUtils;

import java.io.File;

public class Rename {

    public static void renameAuto(String path, String zipName, Boolean zip) throws Exception {
        // 保留  +前面全部内容
        path = DocUtil.checkPath(path);
        String operatePath = DocUtil.copyFile(path);


        if (ObjectUtils.isEmpty(operatePath)) {
            System.out.println("复制文件失败");
            return;
        }

        File folder = new File(operatePath);
        String[] oldString = { "-", "_", "＋", "—" };
        String newString = "+";

        if (!folder.exists()) {
            System.out.println("文件不存在!");
            return;
        }

        File[] fileArr = folder.listFiles();
        if (null == fileArr || fileArr.length == 0) {
            System.out.println("文件夹是空的!");
            return;
        }

        File newDir;// 文件所在文件夹路径+新文件名
        String newName;// 新文件名
        String fileName;// 旧文件名
        File parentPath;// 文件所在父级路径
        for (File file : fileArr) {
            if (file.isDirectory()) {
                continue;
            }

            newName = fileName = file.getName();// 带后缀
            if (fileName.contains("- 副本")) {
                newName = fileName = fileName.replaceAll("- 副本", "");
            }
            for (String s : oldString) {
                if (fileName.contains(s)) {// 文件名包含需要被替换的字符串
                    newName = fileName.replaceAll(s, newString);
                }
            }
            // 读取word第一行作为标题
            String docTitle = DocUtil.doc2String(file);
            System.out.println("第一行：" + docTitle);

            parentPath = file.getParentFile();
            String suffix = newName.substring(newName.lastIndexOf("."));
            String prefix = "";
            if (newName.contains("+")) {
                prefix = newName.substring(0, newName.lastIndexOf("+") + 1);
            }
            newDir = new File(parentPath + "\\" + prefix + docTitle + suffix);
            System.out.println("更改后文件名: " + newDir.getName());
            boolean rename = file.renameTo(newDir);
        }

        if (zip) {
            // 打包
            DocUtil.zip(operatePath, zipName, fileArr.length);
        }

    }


    public static void main(String[] args) throws Exception {

        renameAuto("C:\\Users\\L\\Desktop\\新建文件夹\\word", "海信E7W ", true);


    }
}
