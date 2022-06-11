package com.l.dynamic.datasource.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付宝账户表
 * </p>
 *
 * @author ${author}
 * @since 2022-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_alipay_account")
@ApiModel(value="AlipayAccount对象", description="支付宝账户表")
public class AlipayAccount implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账号")
    private String accountNo;

    @ApiModelProperty(value = "公司")
    private Long companyId;

    @ApiModelProperty(value = "推送状态")
    private Integer state;

    @ApiModelProperty(value = "是否作废")
    private Integer cancelled;

    @ApiModelProperty(value = "币别")
    private String currencyNo;

    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
