package com.example.acount_cuit_pet.config

import cn.dev33.satoken.config.SaTokenConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.util.concurrent.TimeUnit

@Configuration
class SatokenConfig {
    @get:Bean(name = ["SaTokenConfigure"])
    @get:Primary
    val saTokenConfig: SaTokenConfig
        get() {
            val config = SaTokenConfig()
            config.tokenName = "token"
            config.timeout = TimeUnit.MINUTES.toMillis(GlobalVariablePool.TOKEN_LAST_TIME)
            config.activityTimeout = -1
            config.isConcurrent = true
            config.isShare = false
            config.tokenStyle = "uuid"
            config.isLog = true
            config.tokenStyle = "uuid"
            return config
        }
}