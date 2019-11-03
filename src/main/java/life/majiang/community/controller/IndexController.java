package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "age", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
    @RequestParam(name="page",defaultValue = "1") Integer page,
    @RequestParam(name="size",defaultValue = "2") Integer size
    ) {

        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    User user = userMapper.findByToken(cookie.getValue());
                    if (user != null) {
        request.getSession().setAttribute("user", user);
        }
        break;
        }
        }
        }
        PaginationDTO pagination = questionService.list(page,size);

        model.addAttribute("pagination", pagination);

        System.out.println("------哈哈哈哈哈哈    555-------------");
        return "index";
        }

        }
