package com.l.dynamic.datasource;

import com.alibaba.excel.EasyExcel;
import com.l.dynamic.datasource.config.DoubleCalenderListener;
import com.l.dynamic.datasource.mapper.DoubanCalenderMapper;
import com.l.dynamic.datasource.model.DoubanCalender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class DoubanTests {

    @Autowired
    private DoubanCalenderMapper doubanCalenderMapper;

    @Test
    public void readExcelAndSave() throws Exception {
        // 读取excel
        String[] location = { "E:\\Download\\豆瓣\\" + "豆瓣日历_2019.xlsx", "E:\\Download\\豆瓣\\" + "豆瓣日历_2020.xlsx", "E:\\Download\\豆瓣\\" + "豆瓣日历_2021.xlsx", "E:\\Download\\豆瓣\\" + "豆瓣日历_2022.xlsx", };
        System.out.println(Arrays.toString(location));
        for (String lo : location) {
            DoubleCalenderListener listener = new DoubleCalenderListener();
            EasyExcel.read(lo, DoubanCalender.class, listener).sheet().doRead();
            List<DoubanCalender> list = listener.list();
            doubanCalenderMapper.insertBatchSomeColumn(list);
        }

    }


    @Test
    public void update() {
        List<DoubanCalender> calenderList = doubanCalenderMapper.selectList(null);
        List<Long> longs = calenderList.stream().filter(s -> s.getComment().contains("—")).map(DoubanCalender::getId).collect(Collectors.toList());
        for (DoubanCalender it : calenderList) {
            String comment = it.getComment();
            String memorial = it.getMemorial();

            try {


            } catch (Exception e) {

                log.error("报错了。。{}", comment);
            }


        }

    }

    @Test
    public void tt() {
        String calendarDate = "2019.02.25【第91届奥斯卡金像奖颁奖典礼】";
        String datetime = calendarDate.substring(0, calendarDate.indexOf('【'));
        String mem = calendarDate.substring(calendarDate.indexOf('【'));
        System.out.println(datetime);
        System.out.println(mem);
    }

}
