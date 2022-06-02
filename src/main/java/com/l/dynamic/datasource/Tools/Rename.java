package com.l.dynamic.datasource.Tools;

import com.l.dynamic.datasource.utils.DocUtil;

import java.io.File;

public class Rename {
    public static void recursiveTraversalFolder(String path, String subject) throws Exception {
        subject = subject + "+";
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
                        newDir = new File(parentPath + "\\" + num + subject + docTitle + suffix);
                        System.out.println("new Name: " + newDir.getName());
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


    public static void main(String[] args) throws Exception {
        recursiveTraversalFolder("C:\\Users\\L\\Desktop\\新建文件夹\\3\\备份", "逛逛图文");
    }
}
