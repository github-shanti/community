package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "age", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/")
    public String index() {
        System.out.println("------哈哈哈哈哈哈-------------");
        return "index";
    }

}
