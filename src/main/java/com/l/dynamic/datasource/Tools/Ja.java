package com.l.dynamic.datasource.Tools;

import com.l.dynamic.datasource.utils.DocUtil;
import org.springframework.util.ObjectUtils;

import java.io.File;

public class Ja {

    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\L\\Desktop\\新建文件夹\\新建文件夹 (2)\\84\\";
        File newFileDirectory = new File(path + "新建");
        boolean b = true;
        if (!newFileDirectory.exists()) {
            b = newFileDirectory.mkdir();
        } else {
            File[] files = newFileDirectory.listFiles();
            if (!ObjectUtils.isEmpty(files)) {
                for (File fs : files) {
                    b = fs.delete();
                }
            }

        }
        if (b) {
            DocUtil.copyFile(path);
        }

    }


}
