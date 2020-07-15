package cn.com.citydo.order.controller;


import cn.com.citydo.order.model.OrderStateChangeRemark;
import cn.com.citydo.order.service.OrderStateChangeRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fumj
 * @since 2020-06-19
 */
@RestController
@RequestMapping("/order/orderStateChangeRemark")
public class OrderStateChangeRemarkController {

    private OrderStateChangeRemarkService targetService;

    @Autowired
    public OrderStateChangeRemarkController(OrderStateChangeRemarkService targetService) {
        this.targetService = targetService;
    }


    /**
     * 获取数据列表
     */
    @PostMapping("/page")
    @ResponseBody
    public ResponseEntity page() {
        return ResponseEntity.ok(null);
    }


    /**
     * 获取全部数据
     */
    @PostMapping("/all")
    @ResponseBody
    public ResponseEntity findAll() {
        return ResponseEntity.ok(null);
    }


    /**
     * 根据ID查找数据
     */
    @PostMapping("/find")
    @ResponseBody
    public ResponseEntity find(@RequestParam("id") Long id) {
        return ResponseEntity.ok(null);
    }


    /**
     * 添加数据
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity addItem(@RequestBody OrderStateChangeRemark OrderStateChangeRemark) {
        return ResponseEntity.ok(null);
    }


    /**
     * 更新数据
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseEntity updateItem(@RequestBody OrderStateChangeRemark OrderStateChangeRemark) {
        return ResponseEntity.ok(null);
    }


    /**
     * 删除数据
     */
    @DeleteMapping("/del")
    @ResponseBody
    public ResponseEntity deleteItems(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(null);
    }
}

