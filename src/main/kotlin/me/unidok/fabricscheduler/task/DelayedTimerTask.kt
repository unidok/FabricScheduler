package me.unidok.fabricscheduler.task

class DelayedTimerTask(
    override var delay: Int,
    period: Int,
    task: DelayedTimerTask.() -> Unit
) : TimerTask(period, task as TimerTask.() -> Unit), Delayed {
    override fun tick() {
        if (delay-- > 0) return
        if (tick++ % period != 0) return
        task()
    }
}