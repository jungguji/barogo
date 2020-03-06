package barogo.user.repository;

import java.time.LocalTime;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import useInfo.UserVO;

@Mapper
public interface UserMapper {

    @Select("SELECT password FROM admin WHERE id=#{userId}")
    String findPasswordById(@Param("userId") String userId);
    
    @Insert("INSERT INTO admin VALUES(#{userId}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createAdmin(@Param("userId") String id, @Param("password") String password);
    
    @Select("SELECT * FROM user WHERE name=#{name}")
    List<UserVO> findByName(@Param("name") String name);
    
    @Select("SELECT * FROM user WHERE user_id=#{userId}")
    UserVO findByUserId(@Param("userId") String userId);
    
    @Select("SELECT * FROM user WHERE pc_number=${pcNumber}")
    UserVO findByPcNumber(@Param("pcNumber") int pcNumber);
    
    @Update("UPDATE user SET remain_time = #{time} WHERE user_id = #{userId}")
    void updateRemianTimeByUserId(@Param("time") LocalTime time, @Param("userId") String userId);
}
