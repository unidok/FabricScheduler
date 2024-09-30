# Releases
- Releases: https://github.com/unidok/FabricScheduler/tree/master/releases
- Last release: https://github.com/unidok/FabricScheduler/blob/master/releases/FabricScheduler-1.0.jar

# Examples
## Using delayed task
```kt
fun foo() {
    ClientScheduler.run(DelayedTask(10) {
        println("This message was printed after 10 ticks!")
    })
}
```
---
## Using timer task
```kt
fun foo() {
    ClientScheduler.run(TimerTask(20) {
        println("This message prints every 20 ticks!")
    })
}
```
---
## Custom task *(Indexed timer task for example)*
```kt
// IndexedTimerTask.kt
class IndexedTimerTask(
    val limit: Int = -1,
    period: Int,
    task: IndexedTimerTask.() -> Unit
) : TimerTask(period, task as TimerTask.() -> Unit) {
    var iteration = 0
    override fun tick() {
        if (iteration++ == limit) {
            cancel()
            return
        }
        if (tick++ % period == 0) {
            task()
        }
    }
}
```
---
## Custom scheduler *(Server scheduler for example)*
```kt
// ServerScheduler.kt
object ServerScheduler : Scheduler {
    override val tasks = ConcurrentHashMap<Int, Task>()

    override fun register() {
        ServerTickEvents.START_SERVER_TICK.register(this::onTick)
    }

    fun onTick(server: MinecraftServer) {
        val iterator = tasks.values.iterator()
        while (iterator.hasNext()) {
            val task = iterator.next()
            task.tick()
            if (task.isCancelled) iterator.remove()
        }
    }
}

// The created scheduler must be registered:

// Mod.kt
class Mod : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        ServerScheduler.register()
    }
}
```

## Registration scheduler
```kt

```
