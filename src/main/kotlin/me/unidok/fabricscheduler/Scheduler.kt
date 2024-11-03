package me.unidok.fabricscheduler

import me.unidok.fabricscheduler.task.Task
import java.util.concurrent.ConcurrentHashMap

interface Scheduler {
    val tasks: ConcurrentHashMap<Int, Task>

    fun register()

    fun run(task: Task) {
        tasks[task.id] = task
    }

    companion object {
        private var taskCounter = 0
        fun newTaskId(): Int = taskCounter++
    }
}