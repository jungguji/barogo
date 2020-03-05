package barogo.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import useInfo.UserVO;

@Mapper
public interface UserMapper {

    @Select("SELECT password FROM admin WHERE id=#{userId}")
    String findPasswordById(@Param("userId") String userId);
    
    @Insert("INSERT INTO admin VALUES(#{id}, #{password})")
    int createAdmin(@Param("id") String id, @Param("password") String password);
    
    @Select("SELECT * FROM user WHERE name=#{name}")
    List<UserVO> findByName(@Param("name") String name);
    
    @Select("SELECT * FROM user WHERE userId=#{userId}")
    UserVO findByUserId(@Param("userId") String userId);
}
