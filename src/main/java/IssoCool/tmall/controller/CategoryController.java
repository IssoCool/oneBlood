package IssoCool.tmall.controller;

import IssoCool.tmall.pojo.Category;
import IssoCool.tmall.service.CategoryService;
import IssoCool.tmall.util.ImageUtil;
import IssoCool.tmall.util.Page;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = categoryService.list();
        int total = (int) new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs",cs);
        model.addAttribute("page",page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session,MultipartFile image)
        throws IOException{
        System.out.println(c.getId());
        categoryService.add(c);
        System.out.println(c.getId());
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,c.getId()+".jpg");
        if(!file.getParentFile().exists())
           file.getParentFile().mkdirs();
        System.out.println(image);
        System.out.println(file);
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img,"jpg",file);

        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session)throws IOException{
        categoryService.delete(id);

        File file = new File(session.getServletContext().getRealPath("img/category"));
        File f = new File(file,id+".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String get(int id,Model model)throws IOException{
        Category c = categoryService.get(id);
        model.addAttribute("c",c);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category c,HttpSession session,MultipartFile image)throws IOException{
        categoryService.update(c);
        if(image != null && !image.isEmpty()) {
            File file = new File(session.getServletContext().getRealPath("img/category"));
            File f = new File(file, c.getId() + ".jpg");
            image.transferTo(f);
            BufferedImage img = ImageUtil.change2jpg(f);
            ImageIO.write(img,"jpg",f);
        }
        return "redirect:/admin_category_list";
    }

}
