package com.interswitch.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "customAsyncExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);  // Number of threads to keep alive
        executor.setMaxPoolSize(50);   // Max thread pool size
        executor.setQueueCapacity(100); // Queue size before rejecting tasks
        executor.setThreadNamePrefix("AsynchThread-");  // Set thread name prefix
        executor.initialize();
        return executor;
    }
}

