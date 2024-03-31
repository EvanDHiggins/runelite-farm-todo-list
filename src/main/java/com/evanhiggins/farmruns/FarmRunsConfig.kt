package com.evanhiggins.farmruns

import net.runelite.client.config.Config
import net.runelite.client.config.ConfigGroup
import net.runelite.client.config.ConfigItem
import net.runelite.client.config.ConfigSection

@ConfigGroup(FarmRunsConfig.CONFIG_GROUP)
interface FarmRunsConfig : Config {
    @ConfigItem(
        keyName = "allotment-falador",
        name = "Falador",
        description = "Whether Falador Allotments should be included in todo lists.",
        section = allotments
    )
    fun falador_allotment(): Boolean = false

    @ConfigItem(
            keyName = "herb-falador",
            name = "Falador",
            description = "Whether the Falador herb patch should be included.",
            section = herbPatches
    )
    fun falador_herb(): Boolean = false

    @ConfigItem(
            keyName = "flower-falador",
            name = "Falador",
            description = "Whether the Falador flower patch should be included.",
            section = flowerPatches
    )
    fun falador_flower(): Boolean = false

    companion object {
        @ConfigSection(name = "Allotments", description = "Allotment patches", position = 0)
        const val allotments: String = "allotments"

        @ConfigSection(name = "Herbs", description = "Herb patches", position = 1)
        const val herbPatches: String = "herbs"

        @ConfigSection(name = "Flowers", description = "Herb patches", position = 2)
        const val flowerPatches: String = "flowers"

        const val CONFIG_GROUP = "farm-runs-todo-list"
    }
}
