package com.evanhiggins.farmruns

import com.google.inject.Provides
import lombok.extern.slf4j.Slf4j
import net.runelite.api.Client
import net.runelite.api.GameState
import net.runelite.api.events.GameStateChanged
import net.runelite.client.config.ConfigManager
import net.runelite.client.eventbus.Subscribe
import net.runelite.client.plugins.Plugin
import net.runelite.client.plugins.PluginDescriptor
import net.runelite.client.ui.overlay.OverlayManager
import javax.inject.Inject

@Slf4j
@PluginDescriptor(name = "Farm Runs")
class FarmRunsPlugin : Plugin() {
    @Inject
    private var farmRunsOverlay: FarmRunsOverlay? = null

    @Inject
    private var overlayManager: OverlayManager? = null

    @Subscribe
    fun onGameStateChanged(gameStateChanged: GameStateChanged) {
        if (gameStateChanged.gameState == GameState.LOGGED_IN) {
            overlayManager!!.add(farmRunsOverlay)
        }
    }

    @Provides
    fun provideConfig(configManager: ConfigManager): FarmRunsConfig =
        configManager.getConfig(FarmRunsConfig::class.java)
}
