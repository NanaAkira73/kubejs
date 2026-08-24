package dev.latvian.mods.kubejs.ui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class Label extends Widget {
	public boolean centered = false;
	public int lineSpacing = 10;

	public Label() {
		super();
		this.shadow = false;
		this.color = 0xFFFFFFFF;
	}

	@Override
	public void renderForeground(GuiGraphics graphics, float partialTicks) {
		if (name != null) {
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();

			Minecraft mc = Minecraft.getInstance();
			int textWidth = mc.font.width(name);
			int drawX = centered ? x + (width - textWidth) / 2 : x;
			int drawY = y;

			if (shadow) {
				graphics.drawString(mc.font, name, drawX, drawY, color);
			} else {
				graphics.drawString(mc.font, name, drawX, drawY, color);
			}

			RenderSystem.disableBlend();
		}
	}
}