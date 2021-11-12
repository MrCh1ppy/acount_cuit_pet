package com.example.acount_cuit_pet.entity

import com.example.acount_cuit_pet.vo.UserVo
import lombok.EqualsAndHashCode
import javax.persistence.*

@Entity
@EqualsAndHashCode
class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @Version
    var version: Int? = null
    var username: String? = null
    var passwordMd5: String? = null
    var nickname: String? = null
    var identity: String? = null

    fun getVo(): UserVo {
        return UserVo(
            username = this.username ?: "未定义用户名",
            nickname = this.nickname ?: "未定义昵称",
            identity = this.identity ?: "未定义身份",
            id=this.id?:-1
        )
    }

    override fun toString(): String {
        return "ProjectUser(id=$id, version=$version, username=$username, passwordMd5=$passwordMd5, nickname=$nickname, identity=$identity)"
    }
}