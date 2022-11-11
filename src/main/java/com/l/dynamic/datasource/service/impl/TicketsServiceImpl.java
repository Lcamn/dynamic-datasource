package com.l.dynamic.datasource.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.l.dynamic.datasource.entity.Ticket;
import com.l.dynamic.datasource.entity.WordAndResult;
import com.l.dynamic.datasource.entity.WordResult;
import com.l.dynamic.datasource.mapper.TicketsMapper;
import com.l.dynamic.datasource.model.Tickets;
import com.l.dynamic.datasource.service.TicketsService;
import com.l.dynamic.datasource.utils.AuthUtil;
import com.l.dynamic.datasource.utils.Base64Util;
import com.l.dynamic.datasource.utils.FileUtil;
import com.l.dynamic.datasource.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;

/**
 * (Tickets)表服务实现类
 *
 * @author EasyCode
 * @since 2022-11-03 15:17:58
 */
@Service("ticketsService")
@Slf4j
public class TicketsServiceImpl extends ServiceImpl<TicketsMapper, Tickets> implements TicketsService {
    @Autowired
    private TicketsMapper ticketsMapper;

    @DS("db1")
    @Override
    public void add() throws Exception {

        // String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/train_ticket"; // 获取火车票
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";  // 普通识别

        // 本地文件路径
        String filePath = "E:\\Download\\微信截图_20221103154502.png";
        byte[] imgData = FileUtil.readFileByBytes(filePath);
        String imgStr = Base64Util.encode(imgData);
        String imgParam = URLEncoder.encode(imgStr, "UTF-8");

        String param = "image=" + imgParam;

        // 获取accessToken
        String accessToken = AuthUtil.getAuth();

        String result = HttpUtil.post(url, accessToken, param);
        WordResult re = JSON.parseObject(result, WordResult.class);

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
        log.info("ticket: {}", ticket);


        Tickets tickets = new Tickets();
        BeanUtils.copyProperties(ticket, tickets);
        tickets.setDateAndTime(ticket.getDateTime());
        ticketsMapper.insert(tickets);


    }
}
