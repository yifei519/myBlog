package com.lb.web.admin;

import com.lb.entity.Blog;
import com.lb.entity.User;
import com.lb.service.BlogService;
import com.lb.service.TagService;
import com.lb.service.TypeService;
import com.lb.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {
    private  static  final  String INPUT="admin/blogs-input";
    private  static  final  String LIST="admin/blogs";
    private  static  final  String REDIRECT_LIST="redirect:/admin/blogs";
    @Autowired
    private TypeService typeService;
    @Autowired
    BlogService blogService;
    @Autowired
    private TagService tagService;
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 4,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable
                        ,BlogQuery blog, Model model,HttpSession session){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        model.addAttribute("user",session.getAttribute("user"));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 4,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable
            ,BlogQuery blog, Model model,HttpSession session){
        System.out.println("搜索的方法进入了");
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        model.addAttribute("user",session.getAttribute("user"));
        return "admin/blogs :: blogList";
    }


    @GetMapping("/blogs/input")
    public String input(Model model,HttpSession session){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("blog",new Blog());
        model.addAttribute("user",session.getAttribute("user"));
        return INPUT;
    }

    //新增时候的提交
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        attributes.addAttribute("user",session.getAttribute("user"));
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b==null){
             attributes.addFlashAttribute("message","添加失败!!");
        }else {
            attributes.addFlashAttribute("message","添加成功!!");
        }
        return REDIRECT_LIST;
    }

    //博客修改页面
    @GetMapping("/blogs/{id}/input")
    public String updateinput(@PathVariable Long id, Model model,HttpSession session){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        model.addAttribute("user",session.getAttribute("user"));
        return INPUT;
    }

    //删除博客内容
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
//        System.out.println("删除方法！");
        blogService.delete(id);
        attributes.addFlashAttribute("message","删除成功！！");
        return REDIRECT_LIST;
    }

}



















