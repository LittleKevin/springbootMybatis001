package com.kevin.mybatis_demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kevin.mybatis_demo.dao.HeroDao;
import com.kevin.mybatis_demo.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "heroService")
public class HeroServiceImp implements HeroService {
    @Autowired
    private HeroDao heroDao;

    @Override
    public int addUser(Hero hero) {
        return heroDao.insert(hero);
    }

    @Override
    public int updateUser(Hero hero) {
        return heroDao.update(hero);
    }

    @Override
    public int deleteUser(Hero hero) {
        return heroDao.delete(hero);
    }
    /* * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * * pageNum 开始页数
     * * pageSize 每页显示的数据条数
     * */

    @Override
    public PageInfo<Hero> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页
        PageHelper.startPage(pageNum, pageSize);
        List<Hero> heroes = heroDao.selectHeroes();
        PageInfo result = new PageInfo(heroes);
        return result;
    }

    @Override
    public void dynamicTable(String tableName, String hero, String heroId) {
        hero ="'"+hero+"'";
        heroId="'"+heroId+"'";
        heroDao.dynamicTable(tableName,hero,heroId);
    }
}
