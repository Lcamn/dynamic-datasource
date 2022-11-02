package com.l.dynamic.datasource;


import com.alibaba.fastjson.JSON;
import com.l.dynamic.datasource.entity.WordAndResult;
import com.l.dynamic.datasource.entity.WordResult;
import com.l.dynamic.datasource.utils.AuthUtil;
import com.l.dynamic.datasource.utils.Base64Util;
import com.l.dynamic.datasource.utils.FileUtil;
import com.l.dynamic.datasource.utils.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URLEncoder;
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

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthUtil.getAuth();

            String result = HttpUtil.post(url, accessToken, param);

            WordResult re = JSON.parseObject(result, WordResult.class);
            System.out.println(re);

            List<WordAndResult> wordsResult = re.getWords_result();
            for (WordAndResult wordAndResult : wordsResult) {
                System.out.println(wordAndResult.getWords());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
