package com.kevin.mybatis_demo.service;

import com.github.pagehelper.PageInfo;
import com.kevin.mybatis_demo.model.Hero;
import org.apache.ibatis.annotations.Param;

public interface HeroService {
    int addUser(Hero hero);
    int updateUser(Hero hero);
    int deleteUser(Hero hero);
    PageInfo<Hero> findAllUser(int pageNum, int pageSize);
    void dynamicTable(String tableName,String hero,String heroId);
}
