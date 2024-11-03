package me.unidok.fabricscheduler.task

import me.unidok.fabricscheduler.Scheduler

open class Task(
    val task: Task.() -> Unit
) {
    val id = Scheduler.newTaskId()
    var isCancelled = false

    open fun tick() {
        task()
        cancel()
    }

    fun cancel() {
        isCancelled = true
    }

    override fun hashCode() = id
    override fun equals(other: Any?) = other is Task && other.id == id
}