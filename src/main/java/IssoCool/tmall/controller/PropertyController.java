package IssoCool.tmall.controller;


import IssoCool.tmall.pojo.Category;
import IssoCool.tmall.pojo.Property;
import IssoCool.tmall.service.CategoryService;
import IssoCool.tmall.service.PropertyService;
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
public class PropertyController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_list")
    public String list(Model model,int cid, Page page) throws IOException{
        Category c = categoryService.get(cid);

        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        model.addAttribute("ps",ps);
        model.addAttribute("page",page);
        model.addAttribute("c",c);

        return "admin/listProperty";
    }

    @RequestMapping("admin_property_add")
    public String add(Property p) throws IOException{
        propertyService.add(p);

        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id)throws IOException{
        Property p = propertyService.get(id);
        propertyService.delete(id);

        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(int id,Model model)throws IOException{
        Property p = propertyService.get(id);
        Category c = categoryService.get(p.getCid());
        p.setCategory(c);
        model.addAttribute("p",p);

        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property p)throws IOException{
        propertyService.update(p);

        return "redirect:admin_property_list?cid="+p.getCid();
    }
}
