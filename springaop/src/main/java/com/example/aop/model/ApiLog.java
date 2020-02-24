package com.example.aop.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_api_log")
public class ApiLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长
     */
    @TableField("API_LOG_ID")
    private Integer apiLogId;

    /**
     * 接收地址
     */
    @TableField("API_URL")
    private String apiUrl;

    /**
     * 内容
     */
    private String contents;


}
