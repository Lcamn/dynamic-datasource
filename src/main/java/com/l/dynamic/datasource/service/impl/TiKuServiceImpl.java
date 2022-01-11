package com.l.dynamic.datasource.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.l.dynamic.datasource.mapper.TiKuMapper;
import com.l.dynamic.datasource.model.Tiku;
import com.l.dynamic.datasource.service.TiKuService;
import com.l.dynamic.datasource.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TiKuServiceImpl implements TiKuService {
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
        JSONArray jsonArray = JSONArray.parseArray(s);
        int i = 0;
        for (Object obj : jsonArray) {

            List<String> list = (List<String>) obj;

            Tiku tiku = new Tiku()
                    .setQuestion(list.get(0))
                    .setAnswer(list.get(1))
                    .setOption(list.get(2))
                    .setWronganswer(list.get(3))
                    .setWronganswer(list.get(4));
            tiKuMapper.insert(tiku);
            i++;

            if (i > 10) return i;
        }
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
}
