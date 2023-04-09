package com.virgin.jetpack_compose.repository

import Login
import ServiceBuilder
import ServiceEndpoints
import com.virgin.jetpack_compose.model.UserList
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class UserRepository(
    private val userName: String,
    private val password: String
) : BaseRepository() {
    suspend fun validateLogin(): Flow<Response<List<Login>>> {
        return flow {
            emit(
                ServiceBuilder.buildService(ServiceEndpoints::class.java)
                    .getLoginUser(userName, password)
            )
        }
    }


}