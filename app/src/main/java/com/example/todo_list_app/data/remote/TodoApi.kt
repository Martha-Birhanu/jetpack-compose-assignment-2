package com.example.todo_list_app.data.remote

import retrofit2.http.GET

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<TodoDto>
}