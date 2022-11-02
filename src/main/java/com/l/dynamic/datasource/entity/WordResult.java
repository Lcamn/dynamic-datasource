package com.l.dynamic.datasource.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class WordResult {
    private List<WordAndResult> words_result;
    private int words_result_num;
    private long log_id;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
