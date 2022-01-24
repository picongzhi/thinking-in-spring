package com.pcz.thinking.in.spring.bean.scope.web.controller;

import com.pcz.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author picongzhi
 */
@Controller
public class IndexController {
    @Autowired
    private User user;

    @GetMapping("/index.html")
    public String index(Model model) {
        model.addAttribute("userObject", user);
        System.out.println(user);

        return "index";
    }
}
