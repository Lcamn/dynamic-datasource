package com.l.dynamic.datasource.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.l.dynamic.datasource.entity.Res;
import com.l.dynamic.datasource.model.TiaoZhan;
import com.l.dynamic.datasource.service.TiKuService;
import com.l.dynamic.datasource.service.TiaoZhanService;
import com.l.dynamic.datasource.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/tiku")
public class TiKuController {
    @Autowired
    private TiKuService tiKuService;
    @Autowired
    private TiaoZhanService tiaoZhanService;


    /**
     * 更新本地题库
     *
     * @return
     */
    @PostMapping("/sync")
    public Res<Integer> sync() {
        Integer i = tiKuService.sync();
        return Res.ok(i);
    }


    /**
     * 同步题库 接口(手机用)
     *
     * @return
     */
    @GetMapping("/net")
    public String getNetQuestions() {
        String s = FileUtil.readFileByChars("排序2.json");
        JSONObject jsonObject = JSONObject.parseObject(s);
        ArrayList<TiaoZhan> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            TiaoZhan tiaozhan = new TiaoZhan();
            String s1 = entry.getKey().replaceAll(" ", "");
            String question = s1.substring(0, s1.indexOf("|"));
            String option = s1.substring(s1.indexOf("|")).replaceAll("\\|", "");
            String answer = (String) entry.getValue();
            tiaozhan.setQuestion(question).setAnswer(answer).setOption(option);
            list.add(tiaozhan);

        }
        tiaoZhanService.sync(list);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
        return array.toString();
    }


}
