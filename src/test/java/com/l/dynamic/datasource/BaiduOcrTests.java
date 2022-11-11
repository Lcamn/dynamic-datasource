package com.l.dynamic.datasource;


import com.alibaba.fastjson.JSON;
import com.l.dynamic.datasource.entity.TcentParam;
import com.l.dynamic.datasource.entity.Ticket;
import com.l.dynamic.datasource.entity.WordAndResult;
import com.l.dynamic.datasource.entity.WordResult;
import com.l.dynamic.datasource.utils.AuthUtil;
import com.l.dynamic.datasource.utils.Base64Util;
import com.l.dynamic.datasource.utils.FileUtil;
import com.l.dynamic.datasource.utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BaiduOcrTests {


    @Test
    void OCR() {
        // 请求url
        // String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/train_ticket"; // 获取火车票
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";  // 普通识别
        try {
            // 本地文件路径
            String filePath = "E:\\Download\\train_ticket.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;
            System.out.println(AuthUtil.getAuth());

            // 获取accessToken
            String accessToken = AuthUtil.getAuth();

            String result = HttpUtil.post(url, accessToken, param);

            WordResult re = JSON.parseObject(result, WordResult.class);
            System.out.println(re);
            Ticket ticket = new Ticket();
            Field[] field = ticket.getClass().getDeclaredFields();
            List<WordAndResult> wordsResult = re.getWords_result();

            for (int i = 0; i < wordsResult.size(); i++) {
                String words = wordsResult.get(i).getWords();
                field[i].setAccessible(true);
                //给属性赋值
                field[i].set(ticket, field[i].getType().getConstructor(field[i].getType()).newInstance(words));
            }
            System.out.println(ticket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void tes() {
        Date date = new Date();
        String timestamp = date.getTime() + "";
        TcentParam param = new TcentParam();
        param.setTimestamp(timestamp);
    }

}
