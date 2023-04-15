package com.virgin.jetpack_compose.data.repository

import Login
import ServiceBuilder
import ServiceEndpoints
import com.virgin.jetpack_compose.data.model.UserList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class UserRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : com.virgin.jetpack_compose.data.repository.BaseRepository() {
    suspend fun validateLogin(
        userName: String,
        password: String,
    ): Flow<Response<List<Login>>> {
        return flow {
            emit(
                getServiceBuilder()
                    .getLoginUser(userName, password)
            )
        }.flowOn(defaultDispatcher)
    }


}