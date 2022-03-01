package com.elroykanye.springauthutil.business.service

import com.elroykanye.springauthutil.api.dto.UserDto

interface UserService {
    // find user
    fun getByUsername(username: String): UserDto?
    fun getById(id: Long): UserDto?
    fun getAll(): MutableList<UserDto>

    // save user
    fun save(userDto: UserDto): UserDto

    // update user
    fun update(user: UserDto): UserDto

    // delete user
    fun delete(user: UserDto)
    fun deleteById(id: Long)
    fun deleteByUsername(username: String)
    fun deleteAll()

}