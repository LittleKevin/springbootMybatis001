package com.kevin.mybatis_demo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Hero {
    private Integer heroId;
    private String hero;
    private String skin_name;
    private String description;
    private String loc;
}
