package com.l.dynamic.datasource.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Getter;
import lombok.Setter;

/**
 * 豆瓣日历(DoubanCalender)实体类
 *
 * @author EasyCode
 * @since 2022-11-11 16:30:08
 */
@Getter
@Setter
@TableName("douban_calender")
public class DoubanCalender extends Model<DoubanCalender> {
  @TableId(type = IdType.AUTO)
  /**
   * ID
   */
  private Long id;
  /**
   * 名称
   */
  @ExcelProperty(value = "标题")
  private String movieName;
  /**
   * 评分
   */
  @ExcelProperty(value = "评分")
  private String score;
  /**
   * 评分人数
   */
  @ExcelProperty(value = "评分人数")
  private String scorePeople;
  /**
   * 相关信息
   */
  @ExcelProperty(value = "电影其他信息")
  private String movieInfo;
  /**
   * 日期
   */
  @ExcelProperty(value = "日期")
  private String calendarDate;
  /**
   * 评价
   */
  @ExcelProperty(value = "评价")
  private String comment;
  /**
   * 封面图片链接
   */
  @ExcelProperty(value = "封面")
  private String coverLink;
  /**
   * 电影链接
   */
  @ExcelProperty(value = "链接")
  private String movieLink;

  @Override
  public String toString() {
    return JSON.toJSONString(this);
  }


}

