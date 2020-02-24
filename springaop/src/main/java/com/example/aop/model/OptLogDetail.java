package com.example.aop.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fumj
 * @since 2020-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_opt_log_detail")
public class OptLogDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "opt_log_detail_id", type = IdType.AUTO)
    private Long optLogDetailId;

    private Long optLogId;

    /**
     * 操作前后字段值差异比对结果
     */
    private String diffFieldValue;


}
