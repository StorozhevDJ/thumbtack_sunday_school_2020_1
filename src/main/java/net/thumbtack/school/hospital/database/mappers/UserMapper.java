package net.thumbtack.school.hospital.database.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import net.thumbtack.school.hospital.database.model.User;

public interface UserMapper {

    @Insert("INSERT INTO `user` ( `firstName`, `lastName`, `patronymic`, `login`, `password`, `token`) "
            + "VALUES ( #{firstName}, #{lastName}, #{patronymic}, #{login}, MD5(#{password}), #{token} )")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT id, firstName, lastName, patronymic, type, login, token "
            + "FROM user "
            + "WHERE login = #{login} AND password = MD5(#{password});")
    User getByLogin(@Param("login") String login, @Param("password") String password);

    @Select("SELECT id, firstName, lastName, patronymic, type, login, user.token "
            + "FROM user "
            + "WHERE token = #{token};")
    User getByToken(String token);

    @Delete("DELETE FROM user")
    void deleteAll();

}