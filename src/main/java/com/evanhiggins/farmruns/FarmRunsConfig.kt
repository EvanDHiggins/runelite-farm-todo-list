package com.evanhiggins.farmruns

import net.runelite.client.config.Config
import net.runelite.client.config.ConfigGroup
import net.runelite.client.config.ConfigItem
import net.runelite.client.config.ConfigSection

@ConfigGroup("example")
interface FarmRunsConfig : Config {
    @ConfigItem(
        keyName = "allotment-falador",
        name = "Falador",
        description = "Whether Falador Allotments should be included in todo lists.",
        position = 1,
        section = allotments
    )
    fun falador(): Boolean {
        return false
    }

    companion object {
        @ConfigSection(name = "Allotments", description = "Allotment patches", position = 0)
        const val allotments: String = "allotments"
    }
}
