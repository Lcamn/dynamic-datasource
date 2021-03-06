package com.l.dynamic.datasource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AccountLogItemResult;
import com.alipay.api.request.AlipayDataBillAccountlogQueryRequest;
import com.alipay.api.request.AlipayDataBillBuyQueryRequest;
import com.alipay.api.request.AlipayDataBillSellQueryRequest;
import com.alipay.api.response.AlipayDataBillAccountlogQueryResponse;
import com.alipay.api.response.AlipayDataBillBuyQueryResponse;
import com.alipay.api.response.AlipayDataBillSellQueryResponse;
import com.l.dynamic.datasource.config.AlipayCommon;
import com.l.dynamic.datasource.config.RedisKeyConsts;
import com.l.dynamic.datasource.utils.HttpOk;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class DynamicDatasourceApplicationTests {
    @Autowired
    private AlipayCommon alipayCommon;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testTiku() {
        String s = HttpOk.doGet("https://tiku.786345.xyz/getAnswerByQuestion");
        JSONArray jsonArray = JSONArray.parseArray(s);
        System.out.println(jsonArray.size());
    }

    @Test
    void ti() {
        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        System.out.println(time);
    }

    @Test
    void al() throws AlipayApiException {
        String appid = alipayCommon.getAppId();
        String alipayPublicKey = alipayCommon.getAlipayPublicKey();
        System.out.println("appid--->" + appid);
        System.out.println("alipayPublicKey--->" + alipayPublicKey);
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayCommon.getGatewayUrl(),
                alipayCommon.getAppId(),
                alipayCommon.getPrivateKey(),
                alipayCommon.getFormat(),
                alipayCommon.getCharset(),
                alipayCommon.getAlipayPublicKey(),
                alipayCommon.getSignType());

        AlipayDataBillAccountlogQueryRequest request = new AlipayDataBillAccountlogQueryRequest();
        request.setBizContent("{" +
                "  \"start_time\":\"2022-06-01 00:00:00\"," +
                "  \"end_time\":\"2022-06-12 23:00:00\"" +
                "}");
        AlipayDataBillAccountlogQueryResponse response = alipayClient.execute(request);
        System.out.println(response);
        System.out.println(JSON.toJSONString(response));

        List<AccountLogItemResult> detailListOld = response.getDetailList();
        //TODO  ????????????
        for (AccountLogItemResult item : detailListOld) {
            String opsKey = RedisKeyConsts.ALIPAY_CODE_OPERATE + item.getAccountLogId();
            String opsValue = stringRedisTemplate.opsForValue().get(opsKey);
            if (ObjectUtils.isEmpty(opsValue)) {
                //??????????????????
                stringRedisTemplate.opsForValue().set(opsKey, item.toString(), RedisKeyConsts.DEFAULT_LOCK_PERIOD_SECOND, TimeUnit.DAYS);

                String s = stringRedisTemplate.opsForValue().get(opsKey);
                System.out.println(("key:" + opsKey + " value???" + s));
            }

        }

        if (response.isSuccess()) {
            System.out.println("????????????");
        } else {
            System.out.println("????????????");
        }

    }

    @Test
    void out() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayCommon.getGatewayUrl(),
                alipayCommon.getAppId(),
                alipayCommon.getPrivateKey(),
                alipayCommon.getFormat(),
                alipayCommon.getCharset(),
                alipayCommon.getAlipayPublicKey(),
                alipayCommon.getSignType());
        AlipayDataBillSellQueryRequest request = new AlipayDataBillSellQueryRequest();

        request.setBizContent("{" +
                "  \"start_time\":\"2022-05-01 00:00:00\"," +
                "  \"end_time\":\"2022-05-31 23:00:00\"" +
                "}");
        AlipayDataBillSellQueryResponse response = alipayClient.execute(request);

        if (response.isSuccess()) {
            System.out.println("????????????");
            System.out.println(JSON.toJSONString(response));
        } else {
            System.out.println("????????????");
        }

    }

    @Test
    void in() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayCommon.getGatewayUrl(),
                alipayCommon.getAppId(),
                alipayCommon.getPrivateKey(),
                alipayCommon.getFormat(),
                alipayCommon.getCharset(),
                alipayCommon.getAlipayPublicKey(),
                alipayCommon.getSignType());
        AlipayDataBillBuyQueryRequest request = new AlipayDataBillBuyQueryRequest();

        request.setBizContent("{" +
                "  \"start_time\":\"2022-06-01 00:00:00\"," +
                "  \"end_time\":\"2022-06-03 23:00:00\"" +
                "}");
        AlipayDataBillBuyQueryResponse response = alipayClient.execute(request);

        if (response.isSuccess()) {
            System.out.println("????????????");
            System.out.println(JSON.toJSONString(response));
        } else {
            System.out.println("????????????");
        }

    }

    @Test
    void testM() {
        String result = "T?????????????????????";
        Pattern p = Pattern.compile("(??????)|(?????????)|(??????)|(??????)|(??????)");
        //Pattern p = Pattern.compile("(??????)");
        Matcher m = p.matcher(result);
        while (m.find()) {
            System.out.println(m.group());

        }


    }

    @Test
    void testC() {
        LocalDate date = null;
        String result = "2022/12/3";
        if (result.contains("/")) {

        }
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/M/d");
        date = LocalDate.parse(result, fmt);
        System.out.println(date);


    }


}
