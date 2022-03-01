package com.elroykanye.springauthutil.business.mapper

import com.elroykanye.springauthutil.api.dto.UserDto
import com.elroykanye.springauthutil.data.entity.User
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
interface UserMapper {
    fun mapUserToDto(user: User): UserDto
    fun mapDtoToUser(userDto: UserDto): User
}