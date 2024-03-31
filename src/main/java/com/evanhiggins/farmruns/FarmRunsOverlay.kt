package com.evanhiggins.farmruns


import net.runelite.api.MenuAction
import net.runelite.client.ui.FontManager
import net.runelite.client.ui.overlay.Overlay
import net.runelite.client.ui.overlay.OverlayLayer
import net.runelite.client.ui.overlay.OverlayMenuEntry
import net.runelite.client.ui.overlay.OverlayPanel
import net.runelite.client.ui.overlay.OverlayPosition
import net.runelite.client.ui.overlay.components.LineComponent
import net.runelite.client.ui.overlay.components.PanelComponent
import net.runelite.client.ui.overlay.components.TitleComponent
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.Rectangle
import javax.inject.Inject
import javax.inject.Singleton
import net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE
import net.runelite.client.ui.overlay.OverlayPriority

@Singleton
class FarmRunsOverlay @Inject constructor(
    val plugin: FarmRunsPlugin,
    val config: FarmRunsConfig,
) : OverlayPanel(plugin) {

    val farmRunComponent = PanelComponent()

    init {
        farmRunComponent.setBorder(Rectangle(2, 1, 4, 0))
        farmRunComponent.backgroundColor = null
        farmRunComponent.children.add(TitleComponent.builder().text("Farm Run").build())
        farmRunComponent.children.add(LineComponent.builder().left("Falador").right("Not Done!").build())

        menuEntries.add(OverlayMenuEntry(MenuAction.RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Farm Runs"))
        isClearChildren = false
        position = OverlayPosition.DYNAMIC
        isMovable = true
        isSnappable = true
        panelComponent.children.add(farmRunComponent)
        layer = OverlayLayer.ABOVE_WIDGETS

    }

    override fun render(graphics: Graphics2D?): Dimension {
        if (graphics == null) {
            return super.render(null)
        }
        graphics.font = FontManager.getRunescapeSmallFont()

        return super.render(graphics)
    }
}