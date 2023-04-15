package com.virgin.jetpack_compose.data.repository

import VCategory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class CategoryRepository(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO

) : BaseRepository() {
    /**
     * Suspend function is used because of it is invoked from coroutine scope,
     * flow used to get the response from network call and emit the
     * result to the view model.
     */
    suspend fun getCategory(): Flow<Response<VCategory>> {
        return flow {
            emit(
                getServiceBuilder().getCategory()
            )
        }.flowOn(defaultDispatcher)

    }
}