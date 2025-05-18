package com.jct.mytodolistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jct.mytodolistapp.ui.screens.TodoDetailScreen
import com.jct.mytodolistapp.ui.screens.TodoListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") {
            TodoListScreen(
                onItemClick = { todoId ->
                    navController.navigate("todo_detail/$todoId")
                }
            )
        }
        composable("todo_detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: 0
            TodoDetailScreen(
                todoId = todoId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}