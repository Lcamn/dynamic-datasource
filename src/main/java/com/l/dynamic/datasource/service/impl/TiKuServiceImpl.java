package com.l.dynamic.datasource.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.l.dynamic.datasource.mapper.TiKuMapper;
import com.l.dynamic.datasource.model.Tiku;
import com.l.dynamic.datasource.service.TiKuService;
import com.l.dynamic.datasource.utils.FileUtil;
import com.l.dynamic.datasource.utils.HttpOk;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TiKuServiceImpl extends ServiceImpl<TiKuMapper, Tiku> implements TiKuService {
    @Autowired
    private TiKuMapper tiKuMapper;

    @Override
    public List<Tiku> find() {
        return tiKuMapper.selectList(null);
    }

    @Override
    public Integer sync() {
        tiKuMapper.delete(null);
        String s = FileUtil.readFileByChars("tiku.txt");
        return updateByNet(s);
    }

    @NotNull
    private Integer updateByNet(String s) {
        JSONArray jsonArray = JSONArray.parseArray(s);
        int i = 0;
        List<Tiku> tikuList = new ArrayList<>();
        for (Object obj : jsonArray) {

            List<String> list = (List<String>) obj;

            Tiku tiku = new Tiku()
                    .setQuestion(list.get(0))
                    .setAnswer(list.get(1))
                    .setOption(list.get(2))
                    .setWronganswer(list.get(3))
                    .setWronganswer(list.get(4));

            tikuList.add(tiku);
            // tiKuMapper.insert(tiku);
            i++;
        }
        saveBatch(tikuList);
        return i;
    }

    @Override
    public Integer save() {
        Tiku tiku = new Tiku()
                .setQuestion("question")
                .setAnswer("as")
                .setOption("op")
                .setWronganswer("w")
                .setDatetime("2022-01-11 14:15:51");
        return tiKuMapper.insert(tiku);
    }

    @Override
    public void test(String sql) {
        int i = tiKuMapper.test(sql);
        log.info("数量。{}", i);
    }

    @Override
    public Integer syncNet() {
        String s = HttpOk.doGet("https://tiku.3141314.xyz/getAnswer");
        tiKuMapper.delete(null);
        return updateByNet(s);

    }
}
