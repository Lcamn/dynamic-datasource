package com.l.dynamic.datasource.controller;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataBillAccountlogQueryRequest;
import com.alipay.api.request.AlipayDataBillSellQueryRequest;
import com.alipay.api.response.AlipayDataBillAccountlogQueryResponse;
import com.alipay.api.response.AlipayDataBillSellQueryResponse;
import com.l.dynamic.datasource.entity.AccountReturn;
import com.l.dynamic.datasource.service.AlipayAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 支付宝账户表 前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-05-26
 */
@RestController
@Slf4j
@RequestMapping("/alipay-account")
public class AlipayAccountController {

    @Autowired
    private AlipayAccountService alipayAccountService;

    @PostMapping("/test")
    public String Test(){

        List<AccountReturn> list = alipayAccountService.getAll();
        for (AccountReturn accountReturn : list){
            AlipayClient alipayClient = new DefaultAlipayClient(
                    accountReturn.getGatewayUrl(),
                    accountReturn.getAppId(),
                    accountReturn.getPrivateKey(),
                    accountReturn.getFormat(),
                    accountReturn.getCharset(),
                    accountReturn.getAlipayPublicKey(),
                    accountReturn.getSignType());
            AlipayDataBillAccountlogQueryRequest request = new AlipayDataBillAccountlogQueryRequest();

            request.setBizContent("{" +
                    "  \"start_time\":\"2022-05-27 00:00:00\"," +
                    "  \"end_time\":\"2022-05-27 23:00:00\"," +
                    "  \"page_no\":\"1\"," +
                    "  \"page_size\":\"10\"" +
                    "}");
            AlipayDataBillAccountlogQueryResponse response = null;
            try {
                System.out.println("调用 。{} "+ accountReturn);
                response = alipayClient.execute(request);
            } catch (Exception e) {
                System.out.println("调用失败。{} "+ accountReturn);
            }

            if (ObjectUtils.isEmpty(response)){
                continue;
            }

            if (response.isSuccess()) {
                System.out.println("调用成功");
                System.out.println(JSON.toJSONString(response));
            } else {
                System.out.println("调用失败");
                System.out.println(accountReturn);
            }
        }

        return "ok";
    }
}

