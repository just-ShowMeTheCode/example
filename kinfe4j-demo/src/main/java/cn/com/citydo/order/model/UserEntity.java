package cn.com.citydo.order.model;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/6/1913:50
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "用户模型")
public class UserEntity {
    @ApiModelProperty(value="id" ,required= true,example = "123")
    private Integer id;
    @ApiModelProperty(value="用户姓名" ,required=true,example = "郑钦锋")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoDoctor [id=" + id + ", name=" + name + "]";
    }

}
