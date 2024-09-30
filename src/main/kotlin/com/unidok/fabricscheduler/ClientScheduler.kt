package com.unidok.fabricscheduler

import com.unidok.fabricscheduler.task.Task
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.minecraft.client.MinecraftClient
import java.util.concurrent.ConcurrentHashMap

object ClientScheduler : Scheduler {
    override val tasks = ConcurrentHashMap<Int, Task>()

    override fun register() {
        ClientTickEvents.START_CLIENT_TICK.register(ClientScheduler::onTick)
    }

    fun onTick(client: MinecraftClient) {
        val iterator = tasks.values.iterator()
        while (iterator.hasNext()) {
            val task = iterator.next()
            task.tick()
            if (task.isCancelled) iterator.remove()
        }
    }
}