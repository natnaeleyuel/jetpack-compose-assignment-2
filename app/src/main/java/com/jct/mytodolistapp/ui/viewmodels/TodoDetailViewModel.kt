package com.jct.mytodolistapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jct.mytodolistapp.data.local.entity.TodoEntity
import com.jct.mytodolistapp.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoDetailViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<TodoDetailUiState>(TodoDetailUiState.Loading)
    val uiState: StateFlow<TodoDetailUiState> = _uiState

    fun loadTodo(id: Int) {
        viewModelScope.launch {
            repository.getTodoById(id).collectLatest { todo ->
                _uiState.value = TodoDetailUiState.Success(todo)
            }
        }
    }
}

sealed class TodoDetailUiState {
    data object Loading : TodoDetailUiState()
    data class Success(val todo: TodoEntity) : TodoDetailUiState()
    data class Error(val message: String) : TodoDetailUiState()
}



