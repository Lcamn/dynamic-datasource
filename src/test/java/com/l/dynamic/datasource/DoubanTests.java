package com.l.dynamic.datasource;

import com.alibaba.excel.EasyExcel;
import com.l.dynamic.datasource.config.DoubleCalenderListener;
import com.l.dynamic.datasource.mapper.DoubanCalenderMapper;
import com.l.dynamic.datasource.model.DoubanCalender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class DoubanTests {

    @Autowired
    private DoubanCalenderMapper doubanCalenderMapper;

    @Test
    public void readExcel() throws Exception {
        // 读取excel
        String fileName = "E:\\Download\\豆瓣\\" + "豆瓣日历_2017.xlsx";


        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        DoubleCalenderListener listener = new DoubleCalenderListener();
        EasyExcel.read(fileName, DoubanCalender.class, listener).sheet().doRead();
        List<DoubanCalender> list = listener.list();
        doubanCalenderMapper.insertBatchSomeColumn(list);


    }

}
