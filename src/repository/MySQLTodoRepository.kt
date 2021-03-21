package com.maaxgr.repository

import com.maaxgr.database.DatabaseManager
import com.maaxgr.entities.ToDo
import com.maaxgr.entities.ToDoDraft

class MySQLTodoRepository : ToDoRepository {

    private val database = DatabaseManager()

    override fun getAllToDos(): List<ToDo> {
        return database.getAllTodos()
            .map { ToDo(it.id, it.title, it.done) }
    }

    override fun getToDo(id: Int): ToDo? {
        TODO("Not yet implemented")
    }

    override fun addTodo(draft: ToDoDraft): ToDo {
        TODO("Not yet implemented")
    }

    override fun removeTodo(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateTodo(id: Int, draft: ToDoDraft): Boolean {
        TODO("Not yet implemented")
    }
}