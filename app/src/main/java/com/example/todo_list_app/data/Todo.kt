package com.example.todo_list_app.data

data class Todo(
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)