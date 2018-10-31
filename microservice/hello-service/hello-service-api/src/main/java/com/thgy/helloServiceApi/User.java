package com.thgy.helloServiceApi;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class User {
    @NotBlank
    @ApiModelProperty(value = "姓名", example = "box", position = 0)
    private String name;
    @NotBlank
    @ApiModelProperty(value = "年龄", example = "20", position = 1)
    private String age;

    // 注意: 我看的教程("Brixton.SR5"版本)上说此处必须要加User的默认构造函数, 不然spring cloud Feign 根据json字符串
    // 转换User对象是会抛出异常, 但是此版本的情况下并不会出现

//    public User() {
//    }

//    public User(String name, String age) {
//        this.name = name;
//        this.age = age;
//    }
}
