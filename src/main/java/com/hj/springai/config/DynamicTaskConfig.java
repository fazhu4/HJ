package com.hj.springai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Configuration
@EnableScheduling
public class DynamicTaskConfig implements SchedulingConfigurer {
    private ScheduledTaskRegistrar taskRegistrar;
    private Map<String, ScheduledFuture<?>> taskFutures = new HashMap<>();;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
    }

    public void addTask(String taskId, Runnable task, String cronExpression) {
        CronTask cronTask = new CronTask(task, cronExpression);
        ScheduledFuture<?> future = taskRegistrar.scheduleCronTask(cronTask);
        taskFutures.put(taskId, future);
    }

    public void removeTask(String taskId) {
        ScheduledFuture<?> future = taskFutures.get(taskId);
        if (future != null) {
            future.cancel(true);
            taskFutures.remove(taskId);
        }
    }

    public void updateTask(String taskId, String cronExpression) {
        removeTask(taskId);
        Runnable task = taskRegistrar.getCronTasK(taskId).getRunnable();
        addTask(taskId, task, cronExpression);
    }

    public void startTask(String taskId) {
        ScheduledFuture<?> future = taskFutures.get(taskId);
        if (future != null && future.isCancelled()) {
            CronTask cronTask = taskRegistrar.getCronTask(taskId);
            ScheduledFuture<?> newFuture = taskRegistrar.scheduleCronTask(cronTask);
            taskFutures.put(taskId, newFuture);
        }
    }

    public void stopTask(String taskId) {
        removeTask(taskId);
    }

    @Component
    public class DynamicTask implements Runnable {

        @Override
        public void run() {
            // 定时任务逻辑代码
            System.out.println("Dynamic Task: " + new Date());
        }
    }

}
