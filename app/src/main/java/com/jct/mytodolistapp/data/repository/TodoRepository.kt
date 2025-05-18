package com.jct.mytodolistapp.data.repository

import com.jct.mytodolistapp.data.local.dao.TodoDao
import com.jct.mytodolistapp.data.local.entity.TodoEntity
import com.jct.mytodolistapp.data.remote.TodoApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao,
    private val apiService: TodoApiService
) {
    fun getTodos(): Flow<List<TodoEntity>> = flow {
        val localTodos = todoDao.getAllTodos().first()
        emit(localTodos)  // this emits the local data first

        try {
            val response = apiService.getTodos()
            if (response.isSuccessful) {
                response.body()?.let { remoteTodos ->
                    todoDao.insertAll(remoteTodos) // this update the local database with remote data
                }
            } else {
                throw IOException("HTTP ${response.code()}")
            }
        } catch (e: Exception) {
            if (localTodos.isEmpty()) throw e
        }
    }.catch { e ->
        throw e
    }

    fun getTodoById(id: Int): Flow<TodoEntity> {
        return todoDao.getTodoById(id)
    }
}