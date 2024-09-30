package com.unidok.fabricscheduler

import net.fabricmc.api.ClientModInitializer

class Mod : ClientModInitializer {
    override fun onInitializeClient() {
        ClientScheduler.register()
    }
}