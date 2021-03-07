package com.maaxgr.repository

import com.maaxgr.entities.ToDo

class InMemoryToDoRepository : ToDoRepository {

    private val todos = listOf<ToDo>(
        ToDo(1, "Plan content for video #2", true),
        ToDo(2, "Record video #2", false),
        ToDo(3, "Upload video #2", false)
    )

    override fun getAllToDos(): List<ToDo> {
        return todos
    }

    override fun getToDo(id: Int): ToDo? {
        return todos.firstOrNull { it.id == id }
    }
}