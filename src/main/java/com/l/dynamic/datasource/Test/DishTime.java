package com.l.dynamic.datasource.Test;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class DishTime {

    private String dishTime;

    private String dishName;

    private String rangeTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishTime dishTime1 = (DishTime) o;
        return Objects.equals(dishTime, dishTime1.dishTime)
                && Objects.equals(dishName, dishTime1.dishName)
                && Objects.equals(rangeTime, dishTime1.rangeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishTime, dishName, rangeTime);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
