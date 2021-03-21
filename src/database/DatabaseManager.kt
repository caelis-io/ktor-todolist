package com.maaxgr.database

import org.ktorm.database.Database
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {

    // config
    private val hostname = "vm-core.fritz.box"
    private val databaseName = "ktor_todo"
    private val username = "ktor_todo"
    private val password = System.getenv("KTOR_TODOLIST_DB_PW")

    // database
    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllTodos(): List<DBTodoEntity> {
        return ktormDatabase.sequenceOf(DBTodoTable).toList()
    }


}