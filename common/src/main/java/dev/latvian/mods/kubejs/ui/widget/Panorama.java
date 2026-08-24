package dev.latvian.mods.kubejs.ui.widget;

import net.minecraft.client.gui.GuiGraphics;

public class Panorama extends Widget {
	public Panorama() {
		super();
	}

	@Override
	public void renderBackground(GuiGraphics graphics, float partialTicks) {
		// Panorama rendering is handled by the screen itself
		// This widget just serves as a marker
	}
}