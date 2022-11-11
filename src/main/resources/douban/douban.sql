DROP TABLE IF EXISTS douban_calender;
CREATE TABLE douban_calender
(
    id            bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
    movie_name    VARCHAR(255) COMMENT '名称',
    score         VARCHAR(255) COMMENT '评分',
    score_people  VARCHAR(255) COMMENT '评分人数',
    movie_info    VARCHAR(500) COMMENT '相关信息',
    calendar_date VARCHAR(255) COMMENT '日期',
    comment       VARCHAR(500) COMMENT '评价',
    cover_link    VARCHAR(255) COMMENT '封面图片链接',
    movie_link    VARCHAR(255) COMMENT '电影链接',
    PRIMARY KEY (id)
) COMMENT = '豆瓣日历';

select *
from douban_calender;