package com.hj.springai.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hj.springai.Service.TimeService;
import com.hj.springai.common.AuthAccess;
import com.hj.springai.common.Result;
import com.hj.springai.entity.Note_type_time;
import com.hj.springai.entity.Todo_list;
import com.hj.springai.entity.inside.Fin;
import com.hj.springai.entity.inside.YMD;
import com.hj.springai.entity.inside.YearMonthData;
import com.hj.springai.entity.outside.Todo_thing;
import com.hj.springai.mapper.TimeMapper;
import com.hj.springai.util.TokenUtils;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName TimeController
 * @Description
 * @Author fazhu
 * @date 2025-01-20
 **/
@RestController
public class TimeController {
    @Resource
    TimeService timeService;
    @Resource
    TimeMapper timeMapper;


    /**
     * 考勤打卡
     *
     * @return
     */
    @AuthAccess
    @PostMapping("/time/clock/attendance")
    public Result attendance(@RequestBody YMD ymd) {
        int uid = 0;
        try {
            uid = TokenUtils.getCurrentUser().getUid();
        } catch (Exception e) {
            return Result.error("Token失效，请重新登录");
        }
        String jsonString = timeMapper.clockSelect(uid);

        String year = ymd.getYear(), month = ymd.getMonth();
        int day = Integer.valueOf(ymd.getDay());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // 将JSON字符串转换为List<YearMonthData>对象
            List<YearMonthData> yearMonthDataList = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, YearMonthData.class));
            // 打印解析后的对象列表
//            System.out.println("解析后的对象列表: " + yearMonthDataList);

            boolean yearExists = true;
            if (yearMonthDataList.isEmpty()) {
                yearExists = false;
                //如果为空，则创建一个新的YearMonthData对象并添加到列表中
                YearMonthData newYearMonthData = new YearMonthData();
                Map<String, Map<String, List<Integer>>> data = new HashMap<>();
                data.put(year, new HashMap<>() {{
                    put(month, new ArrayList<>() {{
                        add(day);
                    }});
                }});
                newYearMonthData.setData(data);
                yearMonthDataList.add(newYearMonthData);
            } else {

                //添加属性
                for (YearMonthData yearMonthData : yearMonthDataList) {
                    Map<String, Map<String, List<Integer>>> data = yearMonthData.getData();
                    if (data.containsKey(year)) {
                        yearExists = false;
                        Map<String, List<Integer>> monthData = data.get(year);
                        if (monthData.containsKey(month)) {
                            if (monthData.get(month).contains(day)) {
                                // 如果打卡记录已经存在，则不添加
                                return Result.error("打卡记录已存在");
                            }
                            monthData.get(month).add(day);
                        } else {
                            monthData.put(month, new ArrayList<Integer>() {{
                                add(day);
                            }});
                        }
                        break;  // 如果只需要添加一次，可以退出循环
                    }
                }
            }
            if (yearExists) {
                // 如果年份不存在，则创建一个新的YearMonthData对象并添加到列表中
                YearMonthData newYearMonthData = new YearMonthData();
                Map<String, Map<String, List<Integer>>> data = new HashMap<>();
                data.put(year, new HashMap<>() {{
                    put(month, new ArrayList<>() {{
                        add(day);
                    }});
                }});
                newYearMonthData.setData(data);
                yearMonthDataList.add(newYearMonthData);
            }
            // 假设这里有一个方法getFirst()返回第一个元素
            YearMonthData firstElement = yearMonthDataList.get(0);

            String jsonTOStr = objectMapper.writeValueAsString(yearMonthDataList);
            timeMapper.clockUpdate(uid, jsonTOStr);


            return Result.success("签到成功");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("服务器错误");        }
    }

    /**
     * 查询考勤打卡
     */
    @GetMapping("/time/clock/select")
    public Result select() {
        int uid = 0;
        try {
            uid = TokenUtils.getCurrentUser().getUid();

        } catch (Exception e) {
            return Result.error("Token失效，请重新登录");
        }
        try {
            return Result.success(timeMapper.clockSelect(uid));
        } catch (Exception e) {
            return Result.error("服务器异常，查询失败");
        }
    }

    /**
     * 添加代办项
     *
     * @return Result
     */
    @PostMapping("/time/items/insert")
    public Result itemsInsert(@RequestBody Todo_list todo) {
        System.out.println(todo);
        String s = todo.getTodo_thing();
        int uid = 0;
        try {
            uid = TokenUtils.getCurrentUser().getUid();
        } catch (Exception e) {
            return Result.error("Token失效，请重新登录");
        }
        try {
            timeMapper.addTodo(uid, s);
        } catch (Exception e) {
            return Result.error("服务器异常，添加失败");
        }
        return Result.success();
    }

    /**
     * 查询代办项
     *
     * @return
     */

    @GetMapping("/time/items/select")
    public Result itemsSelect() {
        int uid = 0;
        try {
            uid = TokenUtils.getCurrentUser().getUid();
        } catch (Exception e) {
            return Result.error("Token失效，请重新登录");
        }
        try {
            Todo_thing s = new Todo_thing();
            s.setDoing(timeMapper.selectTodoing(uid));
            s.setDid(timeMapper.selectTodo(uid));
            return Result.success(s);
        } catch (Exception e) {
            return Result.error("服务器异常，查询失败");
        }
    }
    /**
     * 完成代办项
     * @param fin
     * @return Result 返回结果
     */
    @PostMapping("/time/items/finish")
    public Result finish(@RequestBody Todo_list fin) {
        int uid = 0;
        try {
            uid = TokenUtils.getCurrentUser().getUid();
        } catch (Exception e) {
            return Result.error("Token失效，请重新登录");
        }
        try {
            timeMapper.finishTodo(uid, fin.getId());
        } catch (Exception e) {
            return Result.error("服务器异常，完成失败");
        }
        return Result.success();
    }
    /**
     * 返回笔记类型
     */
    @GetMapping("/time/noteType")
    public Result selectNoteType() {
        return Result.success(timeMapper.selectNoteType());
    }

    /**
     * 添加笔记时间记录
     * @return
     */
    @PostMapping("/time/logging/insert")
    public Result Time_logging(@RequestBody Note_type_time note_type_time) {
        System.out.println(note_type_time);
        int uid = 0;
        try {
            uid = TokenUtils.getCurrentUser().getUid();
        } catch (Exception e) {
            return Result.error("Token失效，请重新登录");
        }
        try {
           int time=timeMapper.selectNoteTypeTime(uid, note_type_time.getType());
           System.out.println(time);
           if (time<=0) {
               timeMapper.addNoteTypeTime(uid, note_type_time.getType(),note_type_time.getTime());
           }else{
               timeMapper.updateNoteTypeTime(uid, note_type_time.getType(),time+note_type_time.getTime());
           }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("服务器异常，添加失败");
        }
        return Result.success("提交成功");
    }

    /**
     * 查询笔记时间记录
     */
    @GetMapping("/time/logging/select")
    public Result selectNoteTypeTime() {
        int uid = 0;
        try {
            uid = TokenUtils.getCurrentUser().getUid();
        } catch (Exception e) {
            return Result.error("Token失效，请重新登录");
        }
        try {
            return Result.success(timeMapper.selectNoteTime(uid));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("服务器异常，查询失败");
        }
    }

}


