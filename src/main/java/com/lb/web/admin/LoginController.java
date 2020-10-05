package com.lb.web.admin;

import com.lb.entity.User;
import com.lb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;
    //跳转登陆页面
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @GetMapping("/index")
    public String index(HttpSession session,Model model){
        model.addAttribute("user",session.getAttribute("user"));
        return "admin/index";
    }

    //登陆判断进行逻辑处理
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes ,Model model){
        User user = userService.checkUser(username, password);
        if (user!=null){
            //把密码设置为空不让他传到前端去
            user.setPassword(null);
            //将user对象存放在session中
            session.setAttribute("user",user);
            model.addAttribute("user",user);
            return "redirect:/admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误！");
            return "redirect:/admin";
        }
    }
    //注销用户(退出)
    @GetMapping("/loginout")
    public String loginout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }
}
