package com.virgin.jetpack_compose.repository

import ServiceEndpoints

open class BaseRepository {
    fun getServiceBuilder(): ServiceEndpoints {
        return ServiceBuilder.buildService(ServiceEndpoints::class.java)
    }
}