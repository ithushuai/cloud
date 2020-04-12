package com.hushuai.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * created by it_hushuai
 * 2020/4/9 22:34
 */
@Entity(name = "xc_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户名
    private String username;

    // 密码
//    private String password;

    // 姓名
//    private String name;

    // 年龄
//    private Integer age;

    // 性别，1男性，2女性
    private String sex;

    // 出生日期
    private Date birthday;

    // 创建时间
//    private Date created;

    // 更新时间
//    private Date updated;

    // 备注
//    private String note;

    // 。。。省略getters和setters

//    private String address;
}
