package cn.tao.bookstore.service;

import java.util.List;

import cn.tao.bookstore.dao.IBookDao;
import cn.tao.bookstore.dao.ICategoryDao;
import cn.tao.bookstore.domain.Category;
import cn.tao.bookstore.exception.CategoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@EnableTransactionManagement
@Transactional
public class CategoryService {
    @Autowired
    private ICategoryDao categoryDao;
    @Autowired
    private IBookDao bookDao;

    /**
     * 查询所有分类
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Transactional(readOnly = true)
    public Category findByCid(String cid) {
        return categoryDao.findByCid(cid);
    }

    /**
     * 添加分类
     *
     * @param category
     * @throws CategoryException
     */
    public void add(Category category) throws CategoryException {
        Category c = categoryDao.findByCname(category.getCname());
        if (c != null) {
            throw new CategoryException("该分类已存在！");
        }

        categoryDao.add(category);
    }

    public void deleteByCid(String cid) throws CategoryException {
        int count = bookDao.getCountByCid(cid);

        if (count > 0) {
            throw new CategoryException("该分类还有图书，不能删除！");
        }

        categoryDao.deleteByCid(cid);
    }

    public void editCnameByCid(Category category) {
        categoryDao.editCnameByCid(category);
    }
}
