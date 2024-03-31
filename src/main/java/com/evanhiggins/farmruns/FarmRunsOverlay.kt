package com.evanhiggins.farmruns


import net.runelite.api.MenuAction
import net.runelite.client.eventbus.EventBus
import net.runelite.client.eventbus.Subscribe
import net.runelite.client.events.ConfigChanged
import net.runelite.client.ui.FontManager
import net.runelite.client.ui.overlay.*
import net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE
import net.runelite.client.ui.overlay.components.PanelComponent
import net.runelite.client.ui.overlay.components.TitleComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.Rectangle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FarmRunsOverlay @Inject constructor(
        plugin: FarmRunsPlugin,
        eventBus: EventBus,
        val config: AggregatedPatchConfig,
) : OverlayPanel(plugin) {

    private val log: Logger = LoggerFactory.getLogger(FarmRunsOverlay::class.java)

    val farmRunComponent = PanelComponent()

    private val titleComponent = TitleComponent.builder().text("Farm Run").build()

    init {
        eventBus.register(this)
        farmRunComponent.setBorder(Rectangle(2, 1, 4, 0))
        farmRunComponent.backgroundColor = null

        menuEntries.add(OverlayMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Farm Runs"))
        isClearChildren = false
        position = OverlayPosition.DYNAMIC
        isMovable = true
        isSnappable = true
        layer = OverlayLayer.ABOVE_WIDGETS

        updateOverlay()
    }

    override fun render(graphics: Graphics2D?): Dimension {
        if (graphics == null) {
            return super.render(null)
        }
        graphics.font = FontManager.getRunescapeSmallFont()

        return super.render(graphics)
    }

    @Subscribe
    fun onConfigChanged(event: ConfigChanged) {
        if (event.group != FarmRunsConfig.CONFIG_GROUP) {
            return
        }
        updateOverlay()
    }

    private fun updateOverlay() {
        farmRunComponent.children.clear()
        farmRunComponent.children.add(titleComponent)
        for (allotment in config.allotments) {
            allotment.render()?.let { farmRunComponent.children.add(it) }
        }
        panelComponent.children.clear()
        panelComponent.children.add(farmRunComponent)
    }
}