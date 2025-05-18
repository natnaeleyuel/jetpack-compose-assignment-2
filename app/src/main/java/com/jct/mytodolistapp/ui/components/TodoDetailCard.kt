package com.jct.mytodolistapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jct.mytodolistapp.data.local.entity.TodoEntity

@Composable
fun TodoDetailCard(todo: TodoEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "ID: ${todo.id}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "User ID: ${todo.userId}",
                style = MaterialTheme.typography.bodyLarge
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Title: ${todo.title}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Status:",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(
                    modifier = Modifier
                        .width(2.dp)
                )
                Text(
                    text = if (todo.completed) "Completed" else "Pending",
                    color = if (todo.completed) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}



