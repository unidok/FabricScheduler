package me.unidok.fabricscheduler.task

open class TimerTask(
    val period: Int,
    task: TimerTask.() -> Unit
) : Task(task as Task.() -> Unit) {
    var tick = 0

    override fun tick() {
        if (tick++ % period == 0) task()
    }
}