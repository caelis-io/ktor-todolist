package com.maaxgr

import com.maaxgr.entities.ToDo
import com.maaxgr.entities.ToDoDraft
import com.maaxgr.repository.InMemoryToDoRepository
import com.maaxgr.repository.ToDoRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {

        val repository: ToDoRepository = InMemoryToDoRepository()

        get("/") {
            call.respondText("Hello Todolist!")
        }

        get("/todos") {
            call.respond(repository.getAllToDos())
        }

        get("/todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(
                    HttpStatusCode.BadRequest,
                    "id parameter has to be a number"
                )
                return@get
            }

            val todo = repository.getToDo(id)

            if (todo == null) {
                call.respond(
                    HttpStatusCode.NotFound,
                    "found no todo for the provided id $id"
                )
            } else {
                call.respond(todo)
            }
        }

        post("/todos") {
            val todoDraft = call.receive<ToDoDraft>()
            val todo = repository.addTodo(todoDraft)
            call.respond(todo)
        }

        put("/todos/{id}") {
            val todoDraft = call.receive<ToDoDraft>()
            val todoId = call.parameters["id"]?.toIntOrNull()

            if (todoId == null) {
                call.respond(HttpStatusCode.BadRequest,
                    "id parameter has to be a number!")
                return@put
            }

            val updated = repository.updateTodo(todoId, todoDraft)
            if (updated) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound,
                "found no todo with the id $todoId")
            }
        }

        delete("/todos/{id}") {
            val todoId = call.parameters["id"]?.toIntOrNull()

            if (todoId == null) {
                call.respond(HttpStatusCode.BadRequest,
                    "id parameter has to be a number!")
                return@delete
            }

            val removed = repository.removeTodo(todoId)
            if (removed) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound,
                    "found no todo with the id $todoId")
            }
        }
    }

}

