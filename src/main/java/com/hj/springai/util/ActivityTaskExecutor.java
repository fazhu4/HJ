package com.hj.springai.util;

import com.hj.springai.mapper.ActivityScheduler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.*;

@Component
public class ActivityTaskExecutor implements ActivityScheduler {

    // 使用独立线程池（与服务器主业务隔离）
    private final ScheduledExecutorService taskExecutor =
            Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r);
                t.setName("Activity-Task-Thread");
                t.setDaemon(true); // 设置为守护线程，避免阻止JVM关闭
                return t;
            });

    // 线程安全的任务存储
    private final ConcurrentHashMap<String, ScheduledFuture<?>> scheduledTasks =
            new ConcurrentHashMap<>();

    public void scheduleActivityEnd(String activityId, LocalDateTime endTime) {
        long delayMs = calculateDelay(endTime);

        if (delayMs <= 0) {
            handleExpiredActivity(activityId);
            return;
        }

        ScheduledFuture<?> future = taskExecutor.schedule(() -> {
            try {
                updateActivityStatus(activityId, "已结束");
            } catch (Exception e) {
                logError(activityId, e);
            } finally {
                scheduledTasks.remove(activityId);
            }
        }, delayMs, TimeUnit.MILLISECONDS);

        scheduledTasks.put(activityId, future);
    }

    public boolean cancelScheduledTask(String activityId) {
        ScheduledFuture<?> future = scheduledTasks.get(activityId);
        if (future != null && !future.isDone()) {
            future.cancel(false); // 不中断正在执行的任务
            scheduledTasks.remove(activityId);
            return true;
        }
        return false;
    }


    public void shutdown() {
        taskExecutor.shutdownNow();
    }

    // 私有方法：计算延迟时间
    private long calculateDelay(LocalDateTime endTime) {
        return endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - System.currentTimeMillis();
    }

    // 私有方法：处理过期活动
    private void handleExpiredActivity(String activityId) {
        System.out.println("[WARN] 活动 " + activityId + " 已过期，立即关闭");
        updateActivityStatus(activityId, "已结束");
    }

    // 私有方法：更新活动状态（模拟数据库操作）
    private void updateActivityStatus(String activityId, String status) {
        System.out.println("[" + Thread.currentThread().getName() + "] 更新活动状态: "
                + activityId + " -> " + status);
        // 实际业务中调用DAO层接口
    }

    // 私有方法：错误日志记录
    private void logError(String activityId, Exception e) {
        System.err.println("活动任务执行失败: " + activityId);
        e.printStackTrace();
    }
}