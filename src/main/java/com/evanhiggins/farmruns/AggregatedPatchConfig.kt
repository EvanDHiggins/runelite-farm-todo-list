package com.evanhiggins.farmruns

import net.runelite.client.ui.overlay.components.LayoutableRenderableEntity
import net.runelite.client.ui.overlay.components.LineComponent
import net.runelite.client.ui.overlay.components.PanelComponent
import java.awt.Panel
import javax.inject.Inject

data class Patch(val name: String, val enabled: Boolean)

data class Allotment(
        val name: String,
        val allotmentEnabled: Boolean,
        val herbPatchEnabled: Boolean,
        val flowerPatchEnabled: Boolean = false
) {
    private val enabledCount = listOf(allotmentEnabled, herbPatchEnabled, flowerPatchEnabled).count { it }
    fun render(): LayoutableRenderableEntity? {
        return if (enabledCount > 1) {
            aggregatePanelComponent()
        } else if (enabledCount == 1) {
            getLineComponents().first()
        } else {
            null
        }
    }

    private fun getPatches(): List<Patch> =
            listOf(
                    Patch("Allotment", allotmentEnabled),
                    Patch("Herb", herbPatchEnabled),
                    Patch("Flower", flowerPatchEnabled))

    private fun getLineComponents(): List<LayoutableRenderableEntity> =
            listOfNotNull(allotmentLineComponent(), herbLineComponent(), flowerLineComponent())

    private fun aggregatePanelComponent(): PanelComponent = PanelComponent().apply {
        children.add(LineComponent.builder().left(name).build())
        for (patch in getPatches()) {
            if (!patch.enabled) {
                continue
            }
            children.add(LineComponent.builder().left(patch.name).right("X").build())
        }
    }

    private fun lineComponent(name: String?, type: String): LineComponent {
        val leftText = if (name != null) {
            "$name $type Patch"
        } else {
            "$type Patch"
        }
        return LineComponent.builder().left(leftText).right("X").build()
    }

    private fun allotmentLineComponent(): LineComponent? =
            if (allotmentEnabled) lineComponent(name, "Allotment") else null

    private fun herbLineComponent(): LineComponent? =
            if (herbPatchEnabled) lineComponent(name, "Herb") else null

    private fun flowerLineComponent(): LineComponent? =
            if (flowerPatchEnabled) lineComponent(name, "Flower") else null
}

class AggregatedPatchConfig @Inject constructor(
        val config: FarmRunsConfig
) {
    val allotments: List<Allotment>
        get() =
            listOf(
                    Allotment(
                            name = "Falador",
                            allotmentEnabled = config.falador_allotment(),
                            herbPatchEnabled = config.falador_herb(),
                            flowerPatchEnabled = config.falador_flower(),
                    )
            )

}