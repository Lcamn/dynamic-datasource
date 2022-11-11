package com.l.dynamic.datasource.entity;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {
    private String ticketNum;
    private String checkNum;
    private String startingStation;
    private String trainNum;
    private String destinationStation;
    private String startingStationEn;
    private String destinationStationEn;
    private String dateTime;
    private String seatNum;
    private String ticketRates;
    private String buyType;
    private String seatCategory;
    private String seatCategoryNum;
    private String ticketType;
    private String ticketChange;
    private String idAndName;
    private String box1;
    private String box2;
    private String serialAndSellStation;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
