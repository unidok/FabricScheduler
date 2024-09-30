package com.unidok.fabricscheduler.task

@Suppress("UNCHECKED_CAST")
open class WhenTask(
    val timeout: Int,
    val predicate: () -> Boolean,
    task: WhenTask.() -> Unit
) : Task(task as Task.() -> Unit) {
    var tick = 0

    override fun tick() {
        if (predicate()) {
            task()
            cancel()
        } else if (tick++ == timeout) {
            cancel()
        }
    }
}