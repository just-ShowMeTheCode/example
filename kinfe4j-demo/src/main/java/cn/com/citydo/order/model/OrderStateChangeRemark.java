package cn.com.citydo.order.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
 * @since 2020-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order_state_change_remark")
public class OrderStateChangeRemark implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 需求Id
     */
    private String orderNo;

    /**
     * 企业Id
     */
    private Long orderType;

    /**
     * 需求状态
     */
    private Integer orderState;

    /**
     * 贷款金额
     */
    private String reason;

    /**
     * 操作员
     */
    private Long operator;

    /**
     * 操作员类型
     */
    private Integer operatorType;

    private Long originator;

    private Integer originatorType;

    /**
     * 状态位
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


}
