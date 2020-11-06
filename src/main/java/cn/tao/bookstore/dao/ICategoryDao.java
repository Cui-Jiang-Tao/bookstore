package cn.tao.bookstore.dao;

import cn.tao.bookstore.domain.Category;

import java.util.List;

public interface ICategoryDao {

    public List<Category> findAll();

    public void add(Category category);

    public void deleteByCid(String cid);

    public Category findByCid(String cid);

    public Category findByCname(String cname);

    public void editCnameByCid(Category category);

}
