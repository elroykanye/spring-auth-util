package com.elroykanye.springauthutil.business.service.impl

import com.elroykanye.springauthutil.api.dto.UserDto
import com.elroykanye.springauthutil.business.mapper.UserMapper
import com.elroykanye.springauthutil.business.service.UserService
import com.elroykanye.springauthutil.data.entity.User
import com.elroykanye.springauthutil.data.repository.UserRepository
import com.elroykanye.springauthutil.exception.UserException
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Getter
@Setter
@AllArgsConstructor
@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
    ) : UserService {



    override fun getByUsername(username: String): UserDto? {
        val userOpt: Optional<User> = userRepository.findByUsername(username)
        if(userOpt.isPresent) return userMapper.mapUserToDto(userOpt.get())
        else throw UserException.UserNotFoundWithUsernameException(username)
         // TODO("Not yet implemented")
    }

    override fun getById(id: Long): UserDto? {
        val userOpt: Optional<User> = userRepository.findById(id)
        if(userOpt.isPresent) return userMapper.mapUserToDto(userOpt.get())
        else throw UserException.UserNotFoundWithIdException(id)
        // TODO("Not yet implemented")
    }

    override fun getAll(): MutableList<UserDto> {
        return userRepository.findAll()
            .stream()
            .map { userMapper.mapUserToDto(it) }
            .collect(Collectors.toList())
        // TODO("Not yet implemented")
    }

    override fun save(userDto: UserDto): UserDto {
        if(userRepository.findByUsername(userDto.username).isPresent) {
            throw UserException.UserAlreadyExistsException(userDto.username)
        } else {
            val user = userMapper.mapDtoToUser(userDto)
            userRepository.save(user)
            return userMapper.mapUserToDto(user)
        }

        // TODO("Not yet implemented")
    }

    override fun update(user: UserDto): UserDto {
        val userOpt: Optional<User> = userRepository.findById(user.id)
        userOpt.ifPresentOrElse({
            it.username = user.username;
        }, {
            throw UserException.UserNotFoundWithIdException(user.id)
        })

        return userMapper.mapUserToDto(userRepository.save(userOpt.get()))
    }

    override fun delete(user: UserDto) {
        try {
            userRepository.deleteById(user.id)
        } catch (e: UserException.UserNotFoundWithIdException) {
            try {
                userRepository.deleteByUsername(user.username)
            } catch (e: UserException.UserNotFoundWithUsernameException) {
                throw UserException.UserNotFoundException("User with id or username not found");
            }
        }
    }

    override fun deleteById(id: Long) {
        if(!userRepository.existsById(id)) throw UserException.UserNotFoundWithIdException(id)
        userRepository.deleteById(id)
    }

    override fun deleteByUsername(username: String) {
        if(userRepository.findByUsername(username).isPresent) userRepository.deleteByUsername(username)
        else throw UserException.UserNotFoundWithUsernameException(username)
    }

    override fun deleteAll() {
        if(userRepository.count().toInt() == 0) throw UserException.UserNotFoundException()
        userRepository.deleteAll()
    }
}