package life.majiang.community.controller;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper ;
    @Autowired
    private CommentService commentService ;


    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public  Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        User user = null ;

        HttpSession session = request.getSession();
        if (session != null) {
             user = (User) session.getAttribute("user");
        }
        if (null == user) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }


        Comment comment = new Comment();
        comment.setType(commentDTO.getType());
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);

        commentService.insert(comment);

//        Map<Object, Object> map = new HashMap<>();
//        map.put("message", "成功");
        return ResultDTO.okOf() ;
    }
}
