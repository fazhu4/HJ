package com.hj.springai.mapper;

import com.hj.springai.entity.Note_type_time;
import com.hj.springai.entity.Todo_list;
import com.hj.springai.entity.outside.Note_type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @FileName TimeMapper
 * @Description
 * @Author fazhu
 * @date 2025-01-20
 **/
@Mapper
public interface TimeMapper {

    /* 签 到 类 */

    //查询用户签到表
    @Select("select days from clockin where uid=#{uid}")
    String clockSelect(int uid);

    //更新用户签到表
    @Update("UPDATE clockin SET days=#{days} WHERE uid=#{uid} ")
    void clockUpdate(int uid,String days);

    /* 代 办 项 类 */

    //添加代办项
    @Insert("INSERT INTO todo_list (uid,todo_thing,status) VALUES(#{uid}, #{todoThing},0)")
    void addTodo(int uid,String todoThing);

    //完成代办项
    @Update("UPDATE todo_list SET status=1 WHERE uid=#{uid} AND id=#{id}")
    void finishTodo(int uid,int id);

    //查询未完成代办项
    @Select("select * from todo_list where uid=#{uid} AND status=0 ORDER BY id DESC")
    Todo_list[] selectTodoing(int uid);

    //查询已完成代办项
    @Select("select * from todo_list where uid=#{uid} AND status=1 ORDER BY id")
    Todo_list[] selectTodo(int uid);

    /* 笔 记 类 */

    //返回笔记类型
    @Select("select * from note_type ")
    Note_type[] selectNoteType();

    //查询用户所有笔记使用时间
    @Select("select type,COALESCE(SUM(time), 0) AS time from note_type_time where uid=#{uid} GROUP BY type")
    Note_type_time[] selectNoteTime(int uid);

    //查询指定笔记类型时间
    @Select("SELECT COALESCE(SUM(time), 0) AS time FROM note_type_time WHERE uid = #{uid} AND type = #{type}")
    Integer selectNoteTypeTime( int uid,  int type);

    //更新笔记类型时间
    @Update("UPDATE note_type_time SET time=#{time} WHERE uid=#{uid} AND type=#{type}")
    void updateNoteTypeTime(int uid, int type, int time);

    //添加笔记类型时间
    @Insert("INSERT INTO note_type_time (uid, type, time) VALUES(#{uid}, #{type}, #{time})")
    void addNoteTypeTime(int uid, int type,int time);
}
