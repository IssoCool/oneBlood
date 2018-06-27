package IssoCool.tmall.service;

import IssoCool.tmall.pojo.Category;
import IssoCool.tmall.util.Page;

import java.util.List;

public interface CategoryService {
    List<Category> list();
    void add(Category category);
    void delete(int id);
    Category get(int id);
    void update(Category category);
}
