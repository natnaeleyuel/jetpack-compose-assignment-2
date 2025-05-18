package com.jct.mytodolistapp.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jct.mytodolistapp.data.local.entity.TodoEntity


@Composable
fun TodoItemCard(
    todo: TodoEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clip(shape = RoundedCornerShape(4.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .animateContentSize()
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier
                    .height(7.dp)
            )

            Text(
                text = if (todo.completed) "Completed" else "Pending",
                style = MaterialTheme.typography.bodyMedium,
                color = if (todo.completed) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.error
            )
        }
    }
}


