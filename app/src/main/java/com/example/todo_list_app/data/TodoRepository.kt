package com.example.todo_list_app.data

import com.example.todo_list_app.data.local.TodoDao
import com.example.todo_list_app.data.local.TodoEntity
import com.example.todo_list_app.data.remote.TodoApi
import com.example.todo_list_app.data.remote.TodoDto
import com.example.todo_list_app.util.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoApi: TodoApi,
    private val todoDao: TodoDao
) {
    fun getAllTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos().map { entities ->
            entities.map { it.toTodo() }
        }
    }

    fun getTodoById(id: Int): Flow<Todo?> {
        return todoDao.getTodoById(id).map { it?.toTodo() }
    }

    suspend fun refreshTodos(): NetworkResult<Unit> {
        return try {
            val todos = todoApi.getTodos()
            todoDao.clearAll()
            todoDao.insertAll(todos.map { it.toEntity() })
            NetworkResult.Success(Unit)
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Failed to fetch todos")
        }
    }

    private fun TodoDto.toEntity() = TodoEntity(
        id = id,
        userId = userId,
        title = title,
        completed = completed
    )

    private fun TodoEntity.toTodo() = Todo(
        id = id,
        userId = userId,
        title = title,
        completed = completed
    )
}