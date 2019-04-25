package com.kevin.mybatis_demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int no_id;
    private String account;
    private String user_name;
    private String password;
    private int status;
    private String role_name;
    private String mobile;
    private int dep_id;
    private int warehouse_id;
    private String warehouse_addr;
}
