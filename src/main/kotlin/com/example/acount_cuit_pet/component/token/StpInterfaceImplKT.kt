package com.example.acount_cuit_pet.component.token

import cn.dev33.satoken.stp.StpInterface
import cn.dev33.satoken.stp.StpUtil
import com.example.acount_cuit_pet.component.api.ApiResponse
import com.example.acount_cuit_pet.component.exception.ProjectException
import com.example.acount_cuit_pet.component.log.Slf4j.Companion.log
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component


@Component
@Slf4j
class StpInterfaceImplKT:StpInterface {
    @Autowired
    lateinit var redisTemplate: RedisTemplate<String,String>
    /**
     * 返回指定账号id所拥有的权限码集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的权限码集合
     */
    override fun getPermissionList(loginId: Any?, loginType: String?): List<String?>? {
        return null
    }

    /**
     * 返回指定账号id所拥有的角色标识集合
     *
     * @param loginId   账号id
     * @param loginType 账号类型
     * @return 该账号id具有的角色标识集合
     */
    override fun getRoleList(loginId: Any?, loginType: String?): List<String?> {
        val username = StpUtil.getLoginIdAsString()?:throw ProjectException("id为空",ApiResponse.INNER_ERROR)
        val identity = redisTemplate.opsForValue().get(username)
        val value = StpUtil.getTokenValue()
        log.info("{}验证通过，身份为{},令牌值为{}",username,identity,value)
        return mutableListOf(identity)
    }
}