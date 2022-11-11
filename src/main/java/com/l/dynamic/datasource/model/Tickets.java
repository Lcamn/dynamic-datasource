package com.l.dynamic.datasource.model;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * (Tickets)实体类
 *
 * @author EasyCode
 * @since 2022-11-03 15:17:50
 */
@Getter
@Setter
@TableName("tb_tickets")
public class Tickets extends Model<Tickets> {
    @TableId(type = IdType.AUTO)
    /**
     * id
     */
    private Long id;
    /**
     * 编号
     */
    private String ticketNum;
    /**
     * 检票口
     */
    private String checkNum;
    /**
     * 出发地
     */
    private String startingStation;
    /**
     * 火车班次号
     */
    private String trainNum;
    /**
     * 目的地
     */
    private String destinationStation;
    /**
     * 出发地英文
     */
    private String startingStationEn;
    /**
     * 目的地英文
     */
    private String destinationStationEn;
    /**
     * 发车时间
     */
    private String dateAndTime;
    /**
     * 发车时间
     */
    private LocalDateTime dateTime;
    /**
     * 座位号
     */
    private String seatNum;
    /**
     * 票价
     */
    private String ticketRates;
    /**
     * 购买途径
     */
    private String buyType;
    /**
     * 几等坐
     */
    private String seatCategory;
    /**
     * 几等坐
     */
    private String seatCategoryNum;
    /**
     * 几等坐
     */
    private String seatLevel;
    /**
     * 乘车类型
     */
    private String ticketType;
    /**
     * 身份证和姓名
     */
    private String idAndName;
    /**
     * 身份证
     */
    private String idNum;
    /**
     * 姓名
     */
    private String name;
    /**
     * box1
     */
    private String box1;
    /**
     * box2
     */
    private String box2;
    /**
     * 序列号和售票站
     */
    private String serialAndSellStation;
    /**
     * 序列号
     */
    private String serialNum;
    /**
     * 售票站
     */
    private String sellStation;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


}

