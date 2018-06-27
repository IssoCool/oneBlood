package IssoCool.tmall.controller;

import IssoCool.tmall.pojo.Category;
import IssoCool.tmall.pojo.Product;
import IssoCool.tmall.service.CategoryService;
import IssoCool.tmall.service.ProductService;
import IssoCool.tmall.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_product_list")
    public String list(Model model, int cid, Page page) throws IOException {
        Category c = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List ps = productService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        model.addAttribute("ps",ps);
        model.addAttribute("page",page);
        model.addAttribute("c",c);

        return "admin/listProduct";
    }

    @RequestMapping("admin_product_add")
    public String add(Product p) throws IOException{
        productService.add(p);

        return "redirect:admin_product_list?cid="+p.getCid();
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id)throws IOException{
        Product p = productService.get(id);
        productService.delete(id);

        return "redirect:admin_product_list?cid="+p.getCid();
    }

    @RequestMapping("admin_product_edit")
    public String edit(int id,Model model)throws IOException{
        Product p = productService.get(id);
        Category c = categoryService.get(p.getCid());
        p.setCategory(c);
        model.addAttribute("p",p);

        return "admin/editProduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Product p)throws IOException{
        productService.update(p);

        return "redirect:admin_product_list?cid="+p.getCid();
    }
}
