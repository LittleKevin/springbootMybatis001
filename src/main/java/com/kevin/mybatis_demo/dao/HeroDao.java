package com.kevin.mybatis_demo.dao;

import com.kevin.mybatis_demo.model.Hero;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeroDao {
    int insert(Hero record);
    int update(Hero record);
    int delete(Hero record);
    List<Hero> selectHeroes();
    void dynamicTable(@Param(value="tableName") String tableName, @Param(value="hero")String hero, @Param(value="heroId") String heroId);
}
