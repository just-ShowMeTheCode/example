package com.example.aop.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("t_opt_log_summary")
public class OptLogSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长
     */
    @TableId(value = "opt_log_id", type = IdType.ID_WORKER)
    private Long optLogId;

    /**
     * 操作人id
     */
    private Integer userId;

    /**
     * 操作人用户名
     */
    private String userName;

    /**
     * 用户ip
     */
    private String ip;

    /**
     * 运行时间
     */
    private Long runTime;

    /**
     * 操作前后字段值差异比对结果
     */
    private String diffFieldValue;

    /**
     * 操作类型
     */
    private String operatorType;

    /**
     * 操作概述
     */
    private String optSummary;

    /**
     * 唯一追踪id
     */
    private Long traceId;

    /**
     * 操作时间
     */
    private LocalDateTime createTime;


}
