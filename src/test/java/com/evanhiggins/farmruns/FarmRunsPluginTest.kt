package com.evanhiggins.farmruns

import net.runelite.client.RuneLite
import net.runelite.client.externalplugins.ExternalPluginManager

object FarmRunsPluginTest {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        ExternalPluginManager.loadBuiltin(FarmRunsPlugin::class.java)
        RuneLite.main(args)
    }
}