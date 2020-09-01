package com.voidx.user.domain.mapper

import com.voidx.core.data.Mapper
import com.voidx.user.data.model.User
import com.voidx.user.domain.model.UserDTO

internal class UserToUserDtoMapper : com.voidx.core.data.Mapper<User, UserDTO> {
    override fun map(from: User): UserDTO {
        return UserDTO().apply {
            id = from.id
            login = from.login
            avatar = from.avatar ?: ""
        }
    }
}