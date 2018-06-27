package IssoCool.tmall.service.impl;

import IssoCool.tmall.mapper.CategoryMapper;
import IssoCool.tmall.pojo.Category;
import IssoCool.tmall.pojo.CategoryExample;
import IssoCool.tmall.service.CategoryService;
import IssoCool.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public List<Category> list() {
        CategoryExample example = new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }

    public void add(Category category){
        categoryMapper.insert(category);
    }

    public void delete(int id){
        categoryMapper.deleteByPrimaryKey(id);
    }

    public Category get(int id){
        return categoryMapper.selectByPrimaryKey(id);
    }

    public void update(Category category){
        categoryMapper.updateByPrimaryKeySelective(category);
    }
}
