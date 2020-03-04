package barogo.login.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT password FROM admin WHERE id=#{userId}")
    String findPasswordById(@Param("userId") String userId);
    
    
    @Insert("INSERT INTO admin VALUES(#{id}, #{password})")
    int createAdmin(@Param("id") String id, @Param("password") String password);
}
