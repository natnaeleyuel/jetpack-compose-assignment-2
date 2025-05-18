package com.jct.mytodolistapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jct.mytodolistapp.data.local.entity.TodoEntity
import com.jct.mytodolistapp.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<TodoListUiState>(TodoListUiState.Loading)
    val uiState: StateFlow<TodoListUiState> = _uiState

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            _uiState.value = TodoListUiState.Loading
            try {
                repository.getTodos()
                    .collect { todos ->
                        _uiState.value = TodoListUiState.Success(todos)
                    }
            } catch (e: Exception) {
                _uiState.value = TodoListUiState.Error(
                    e.message ?: "Failed to load todos"
                )
            }
        }
    }

    fun refresh() {
        loadTodos()
    }
}

sealed class TodoListUiState {
    data object Loading : TodoListUiState()
    data class Success(val todos: List<TodoEntity>) : TodoListUiState()
    data class Error(val message: String) : TodoListUiState()
}