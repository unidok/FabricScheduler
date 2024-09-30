package com.unidok.fabricscheduler.task

import com.unidok.fabricscheduler.Scheduler

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

    final override fun hashCode() = id
    final override fun equals(other: Any?) = other is Task && other.id == id
}