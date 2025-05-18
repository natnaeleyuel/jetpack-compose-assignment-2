package com.jct.mytodolistapp.di

import android.content.Context
import androidx.room.Room
import com.jct.mytodolistapp.data.local.AppDatabase
import com.jct.mytodolistapp.data.local.dao.TodoDao
import com.jct.mytodolistapp.data.remote.RetrofitClient
import com.jct.mytodolistapp.data.remote.TodoApiService
import com.jct.mytodolistapp.data.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    fun provideTodoDao(database: AppDatabase): TodoDao = database.todoDao()

    @Provides
    @Singleton
    fun provideTodoApiService(): TodoApiService = RetrofitClient.instance
}


