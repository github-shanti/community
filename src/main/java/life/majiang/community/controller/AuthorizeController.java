package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider ;
    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state, HttpServletRequest request, HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(
                clientId,
                clientSecret,
                code,
                redirectUrl,
                state
        );

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("accessToken = " + accessToken);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println("githubUser = " + githubUser);

        if (githubUser != null) {

            User user = new User();
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));

            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAvatarUrl(githubUser.getAvatarUrl());
//            userMapper.inster(user);
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));


//            request.getSession().setAttribute("githubUser",githubUser);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
//        return "index";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
