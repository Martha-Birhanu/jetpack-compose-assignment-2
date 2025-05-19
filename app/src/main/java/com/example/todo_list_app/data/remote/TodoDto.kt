package com.example.todo_list_app.data.remote

import com.google.gson.annotations.SerializedName

data class TodoDto(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)