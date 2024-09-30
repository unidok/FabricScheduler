package com.unidok.fabricscheduler.task

@Suppress("UNCHECKED_CAST")
class DelayedTask(
    override var delay: Int,
    task: DelayedTask.() -> Unit
) : Task(task as Task.() -> Unit), Delayed {
    override fun tick() {
        if (delay-- > 0) return
        task()
        cancel()
    }
}