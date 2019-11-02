package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into PUBLIC.QUESTION(TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, COMMENT_COUNT, VIEW_COUNT, LIKE_COUNT, TAG)" +
            "        values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{commentCount}, #{viewCount}, #{likeCount}, #{tag})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();
}
