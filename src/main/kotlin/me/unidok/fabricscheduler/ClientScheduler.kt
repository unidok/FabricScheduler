package me.unidok.fabricscheduler

import me.unidok.fabricscheduler.task.Task
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import java.util.concurrent.ConcurrentHashMap

object ClientScheduler : Scheduler {
    override val tasks = ConcurrentHashMap<Int, Task>()

    override fun register() {
        ClientTickEvents.START_CLIENT_TICK.register { _ ->
            val iterator = tasks.values.iterator()
            while (iterator.hasNext()) {
                val task = iterator.next()
                task.tick()
                if (task.isCancelled) iterator.remove()
            }
        }
    }
}