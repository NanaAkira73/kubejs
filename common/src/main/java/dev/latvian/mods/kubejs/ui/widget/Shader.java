package dev.latvian.mods.kubejs.ui.widget;

import net.minecraft.client.gui.GuiGraphics;

public class Shader extends Widget {
	public Shader() {
		super();
	}

	@Override
	public void renderBackground(GuiGraphics graphics, float partialTicks) {
		getUi().screen.hasShader = true;
	}
}