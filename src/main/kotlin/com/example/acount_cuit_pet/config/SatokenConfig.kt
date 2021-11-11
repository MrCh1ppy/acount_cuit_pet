package com.example.acount_cuit_pet.config

import cn.dev33.satoken.config.SaTokenConfig
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class SatokenConfig {

    fun getSaTokenConfig(): SaTokenConfig? {
        return SaTokenConfig().apply {
            tokenName = "satoken"
            timeout = TimeUnit.MINUTES.toMillis(20)
            activityTimeout = -1
            isConcurrent = true
            isShare = false
            tokenStyle = "uuid"
            isLog = true
        }
    }
}