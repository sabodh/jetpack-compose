package com.virgin.jetpack_compose.data.repository

import ServiceEndpoints

open class BaseRepository {
    fun getServiceBuilder(): ServiceEndpoints {
        return ServiceBuilder.buildService(ServiceEndpoints::class.java)
    }
}