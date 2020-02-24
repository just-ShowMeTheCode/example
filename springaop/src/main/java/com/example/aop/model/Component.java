package com.example.aop.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 零件表
 * </p>
 *
 * @author fumj
 * @since 2020-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_component")
public class Component implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增长
     */
    @TableId(value = "COMPONENT_ID", type = IdType.AUTO)
    private Long componentId;

    /**
     * 零件编号
     */
    @TableField("COMPONENT_NO")
    private String componentNo;

    /**
     * 名称
     */
    @TableField("COMPONENT_NAME")
    private String componentName;

    /**
     * 交换码
     */
    @TableField("SWICH_CODE")
    private String swichCode;

    /**
     * 创建人
     */
    @TableField("ORIGINATOR")
    private Integer originator;

    /**
     * 操作员
     */
    @TableField("OPERATOR")
    private Integer operator;

    /**
     * 类型 0-原始零件 1-合成零件
     */
    @TableField("TYPE")
    private Integer type;

    /**
     * 零件用途
     */
    @TableField("PURPOSE")
    private String purpose;

    /**
     * 单位机构ID
     */
    @TableField("OFFICE_ID")
    private Long officeId;

    /**
     * 部门ID
     */
    @TableField("DEPARTMENT_ID")
    private Long departmentId;

    /**
     * 数据源ID
     */
    @TableField("SOURCE_ID")
    private Long sourceId;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 0-未生效 1-已生效 2-已失效
     */
    @TableField("EFFECT_STATE")
    private Integer effectState;

    /**
     * 生效时间
     */
    @TableField("EFFECT_DATE")
    private LocalDate effectDate;

    /**
     * 组合零件表达式
     */
    @TableField("COMPONENT_CONTENT")
    private String componentContent;

    /**
     * 组合零件表达式(WEB显示时使用)
     */
    @TableField("COMPONENT_CONTENT_CN")
    private String componentContentCn;

    /**
     * 组合零件包含的零件
     */
    @TableField("INCLUDE_COMPONENTS")
    private String includeComponents;

    /**
     * 零件字段长度
     */
    @TableField("COMPONENT_FIELD_LENGTH")
    private String componentFieldLength;

    /**
     * 零件字段类型 0-STRING 1-DATE 2-NUMBER
     */
    @TableField("COMPONENT_FIELD_TYPE")
    private String componentFieldType;

    /**
     * 零件内容类型 0 计算框，1条件判断，3规则
     */
    @TableField("COMPONENT_CONTENT_TYPE")
    private Integer componentContentType;

    /**
     * 状态 0-正常 1-删除
     */
    @TableField("STATE")
    private Integer state;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;


}
