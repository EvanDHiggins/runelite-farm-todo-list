package com.evanhiggins.farmruns;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.lang.Exception;

@Slf4j
@PluginDescriptor(
	name = "Farm Runs"
)
public class FarmRunsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private FarmRunsConfig config;

	@Inject
	private FarmRunsOverlay farmRunsOverlay;

	@Inject
	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");

	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			overlayManager.add(farmRunsOverlay);
		}
	}

	@Provides
	FarmRunsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(FarmRunsConfig.class);
	}
}
