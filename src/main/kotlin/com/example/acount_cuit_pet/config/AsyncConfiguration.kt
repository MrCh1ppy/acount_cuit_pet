package com.example.acount_cuit_pet.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor

@Configuration
class AsyncConfiguration {

    fun doSomethingExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.apply {
            corePoolSize = 10
            maxPoolSize = 20
            setQueueCapacity(500)
            keepAliveSeconds = 60
            setThreadNamePrefix("ch1ppy")
            setRejectedExecutionHandler(ThreadPoolExecutor.DiscardPolicy())
            executor.initialize()
        }
        return executor
    }
}