package com.jct.mytodolistapp.data.remote

import com.jct.mytodolistapp.data.local.entity.TodoEntity
import retrofit2.http.GET
import retrofit2.Response

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): Response<List<TodoEntity>>
}

