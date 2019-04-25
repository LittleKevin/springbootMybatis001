package com.kevin.mybatis_demo.dao;

import com.kevin.mybatis_demo.model.Hero;

import java.util.List;

public interface HeroDao {
    int insert(Hero record);
    int update(Hero record);
    int delete(Hero record);
    List<Hero> selectHeroes();
}
