package com.maaxgr.repository

import com.maaxgr.entities.ToDo

interface ToDoRepository {

    fun getAllToDos(): List<ToDo>

    fun getToDo(id: Int): ToDo?

}