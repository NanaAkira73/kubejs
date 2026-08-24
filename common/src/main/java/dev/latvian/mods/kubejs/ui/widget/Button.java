package dev.latvian.mods.kubejs.ui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class Button extends Widget {
	public SoundEvent clickSound = null;

	public Button() {
		super();
		this.clickSound = SoundEvents.UI_BUTTON_CLICK;
	}

	@Override
	public void renderBackground(GuiGraphics graphics, float partialTicks) {
		ResourceLocation tex = texture;

		boolean hovered = isMouseOver(getUi().mouse.x, getUi().mouse.y);
		if (hovered && hoverTexture != null) {
			tex = hoverTexture;
		}

		if (tex != null) {
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.setShaderTexture(0, tex);
			RenderSystem.setShaderColor(1F, 1F, 1F, alpha);

			if (hovered && hoverColor != 0xFFFFFFFF) {
				float r = ((hoverColor >> 16) & 0xFF) / 255F;
				float g = ((hoverColor >> 8) & 0xFF) / 255F;
				float b = (hoverColor & 0xFF) / 255F;
				RenderSystem.setShaderColor(r, g, b, alpha);
			}

			graphics.blit(tex, x, y, 0, 0, width, height, width, height);

			RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
			RenderSystem.disableBlend();
		}
	}

	@Override
	public void renderForeground(GuiGraphics graphics, float partialTicks) {
		if (name != null) {
			Minecraft mc = Minecraft.getInstance();
			int textWidth = mc.font.width(name);
			int drawX = x + (width - textWidth) / 2;
			int drawY = y + (height - 8) / 2;

			boolean hovered = isMouseOver(getUi().mouse.x, getUi().mouse.y);
			int c = hovered && hoverColor != 0xFFFFFFFF ? hoverColor : color;

			graphics.drawString(mc.font, name, drawX, drawY, c);
		}
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (clickSound != null) {
			Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(clickSound, 1.0F));
		}
		super.mouseClicked(mouseX, mouseY, button);
	}
}