package com.hj.springai.mapper;

import java.time.LocalDateTime;

public interface ActivityScheduler {
    /**
     * 添加活动截止任务（线程安全，与服务器其他接口隔离）
     * @param activityId 活动唯一ID
     * @param endTime    活动截止时间（精确到秒）
     */
    void scheduleActivityEnd(String activityId, LocalDateTime endTime);

    /**
     * 取消未执行的定时任务
     * @param activityId 活动唯一ID
     * @return 是否取消成功
     */
    boolean cancelScheduledTask(String activityId);

    /**
     * 安全关闭调度器（释放所有资源）
     */
    void shutdown();
}
