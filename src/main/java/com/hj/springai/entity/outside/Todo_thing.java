package com.hj.springai.entity.outside;

import com.hj.springai.entity.Todo_list;
import lombok.Data;

/**
 * @FileName todo_thing
 * @Description
 * @Author fazhu
 * @date 2025-01-21
 **/
@Data
public class Todo_thing {
    private Todo_list[] did; // 待办事项
    private Todo_list[] doing; // 状态
}
