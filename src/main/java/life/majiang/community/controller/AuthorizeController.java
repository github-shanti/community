package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider ;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @GetMapping("/callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state, HttpServletRequest request) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO(
                clientId,
                clientSecret,
                code,
                redirectUrl,
                state
        );

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("accessToken = " + accessToken);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println("user = " + user);

        if (user != null) {
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
//        return "index";
    }
}
