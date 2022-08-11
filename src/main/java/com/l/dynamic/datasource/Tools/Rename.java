package com.l.dynamic.datasource.Tools;

import com.l.dynamic.datasource.utils.DocUtil;
import org.springframework.util.ObjectUtils;

import java.io.File;

public class Rename {
    public static void renameWithSubject(String path, String subject) throws Exception {
        char c = path.charAt(path.length() - 1);
        if (!(c == '\\')) {
            path = path + "\\";
        }
        System.out.println(path);
        File folder = new File(path);
        String oldString = "-";
        String newString = "+";
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        //System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        //recursiveTraversalFolder(file.getAbsolutePath(), subject);
                    } else {//是文件，判断是否需要重命名
                        newName = fileName = file.getName();
                        //读取word第一行
                        String docTitle = DocUtil.doc2String(file);
                        System.out.println("第一行：" + docTitle);

                        parentPath = file.getParentFile();
                        if (fileName.contains(oldString)) {//文件名包含需要被替换的字符串
                            newName = fileName.replaceAll(oldString, newString);//新名字
                        }

                        String suffix = newName.substring(newName.lastIndexOf("."));
                        String num = newName.split("\\+")[0] + "+";
                        newDir = new File(parentPath + "\\" + num + subject + "+" + docTitle + suffix);
                        System.out.println("更改后文件名: " + newDir.getName());
                        file.renameTo(newDir);

                    }
                }

                //打包
                DocUtil.zip(path, subject, fileArr.length);
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void renameWithoutSubject(String path, String zipName) throws Exception {
        char c = path.charAt(path.length() - 1);
        if (!(c == '\\')) {
            path = path + "\\";
        }
        System.out.println(path);
        File folder = new File(path);
        String oldString = "-";
        String newString = "+";
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        //System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        //recursiveTraversalFolder(file.getAbsolutePath(), subject);
                    } else {//是文件，判断是否需要重命名
                        newName = fileName = file.getName();
                        //读取word第一行
                        String docTitle = DocUtil.doc2String(file);
                        System.out.println("第一行：" + docTitle);
                        parentPath = file.getParentFile();
                        if (fileName.contains(oldString)) {//文件名包含需要被替换的字符串
                            newName = fileName.replaceAll(oldString, newString);//新名字
                        }

                        String suffix = newName.substring(newName.lastIndexOf("."));

                        newDir = new File(parentPath + "\\" + docTitle + suffix);
                        System.out.println("更改后文件名: " + newDir.getName());
                        file.renameTo(newDir);

                    }
                }

                //打包
                DocUtil.zip(path, zipName, fileArr.length);
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void renameAuto(String path, String zipName, Boolean zip) throws Exception {
        // 保留 + 前面全部内容
        char c = path.charAt(path.length() - 1);
        if (!(c == '\\')) {
            path = path + "\\";
        }
        String operatePath = DocUtil.copyFile(path);
        if (ObjectUtils.isEmpty(operatePath)) {
            System.out.println("操作失败");
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

        File newDir;//文件所在文件夹路径+新文件名
        String newName;//新文件名
        String fileName;//旧文件名
        File parentPath;//文件所在父级路径
        for (File file : fileArr) {
            if (file.isDirectory()) {
                continue;
            }

            newName = fileName = file.getName();//带后缀
            for (String s : oldString) {
                if (fileName.contains(s)) {//文件名包含需要被替换的字符串
                    newName = fileName.replaceAll(s, newString);
                }
            }
            //读取word第一行作为标题
            String docTitle = DocUtil.doc2String(file);
            System.out.println("第一行：" + docTitle);

            parentPath = file.getParentFile();
            String suffix = newName.substring(newName.lastIndexOf("."));
            String prefix = "";
            if (newName.contains("+")) {
                // 前缀 有问题需要取最后一个+
                prefix = newName.substring(0, newName.lastIndexOf("+") + 1);
            }
            newDir = new File(parentPath + "\\" + prefix + docTitle + suffix);
            System.out.println("更改后文件名: " + newDir.getName());
            file.renameTo(newDir);
        }
        if (zip) {
            //打包
            DocUtil.zip(operatePath, zipName, fileArr.length);
        }
    }


    public static void main(String[] args) throws Exception {

        renameAuto("C:\\Users\\L\\Desktop\\新建文件夹\\新建文件夹 (2)\\84", "zip", true);


    }
}
