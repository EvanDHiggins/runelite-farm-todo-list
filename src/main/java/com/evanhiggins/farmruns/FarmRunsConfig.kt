package com.evanhiggins.farmruns;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("example")
public interface FarmRunsConfig extends Config
{

	@ConfigSection(
			name = "Allotments",
			description = "Allotment patches",
			position = 0
	)
	String allotments = "allotments";

	@ConfigItem(
		keyName = "allotment-falador",
		name = "Falador",
		description = "Whether Falador Allotments should be included in todo lists.",
		position = 1,
		section = allotments
	)
	default boolean falador()
	{
		return false;
	}
}
