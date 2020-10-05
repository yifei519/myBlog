package com.lb.web.admin;


import com.lb.entity.Type;
import com.lb.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    public String types(@PageableDefault(size = 6,sort ={"id"},direction = Sort.Direction.DESC) Pageable pageable
                         , Model model, HttpSession session){
        Page<Type> types = typeService.listType(pageable);
        model.addAttribute("page",types);
        model.addAttribute("user",session.getAttribute("user"));
        return "admin/types";
    }

    //点击新增跳转到新增的页面
    @GetMapping("/types/input")
    public String input(Model model,HttpSession session){
        model.addAttribute("type",new Type());
        model.addAttribute("user",session.getAttribute("user"));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String input(@Valid Type type, BindingResult result, RedirectAttributes attributes){
        //用于增加时判断数据库是否存在该值
        Type typeQuery= typeService.getTypeBnName(type.getName());
        if (typeQuery!=null){
            result.rejectValue("name","nameError","不能添加重复的类");
        }
        //用于校验
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.saveType(type);
        if(type1==null){
            attributes.addFlashAttribute("message","添加失败！");
        }else {
            attributes.addFlashAttribute("message","添加成功！");

        }
        return "redirect:/admin/types";
    }

    //编辑（修改）
    @GetMapping("/types/{id}/input")
    public String updateInput(@PathVariable Long id, Model model,HttpSession session){
        Type type = typeService.getType(id);
        model.addAttribute("type",type);
        model.addAttribute("user",session.getAttribute("user"));

        return "admin/types-input";
    }
    //具体修改操作
    @PostMapping("/types/{id}")
    public String input(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes,HttpSession session){
        //用于增加时判断数据库是否存在该值
        Type typeQuery= typeService.getTypeBnName(type.getName());
        attributes.addAttribute("user",session.getAttribute("user"));
        if (typeQuery!=null){
            result.rejectValue("name","nameError","不能添加重复的类");
        }
        //用于校验
        if(result.hasErrors()){
            return "admin/types-input";
        }
        //修改调用方法
        Type type1 = typeService.updateType(id,type);
        if(type1==null){
            attributes.addFlashAttribute("message","修改失败！");
        }else {
            attributes.addFlashAttribute("message","修改成功！");

        }
        return "redirect:/admin/types";
    }

    //删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }





}
