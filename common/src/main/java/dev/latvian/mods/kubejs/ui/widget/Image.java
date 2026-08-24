package dev.latvian.mods.kubejs.ui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class Image extends Widget {
	public int tileSize = 0;
	public int[] uv = null;

	public Image() {
		super();
	}

	public void setTexture(String texture) {
		this.texture = new ResourceLocation(texture);
	}

	public void setHoverTexture(String hoverTexture) {
		this.hoverTexture = new ResourceLocation(hoverTexture);
	}

	public void setUv(int[] uv) {
		this.uv = uv;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	@Override
	public void renderBackground(GuiGraphics graphics, float partialTicks) {
		ResourceLocation tex = texture;

		if (hoverTexture != null && isMouseOver(getUi().mouse.x, getUi().mouse.y)) {
			tex = hoverTexture;
		}

		if (tex != null) {
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			RenderSystem.setShaderTexture(0, tex);
			RenderSystem.setShaderColor(1F, 1F, 1F, alpha);

			if (tileSize > 0) {
				int tilesX = (width + tileSize - 1) / tileSize;
				int tilesY = (height + tileSize - 1) / tileSize;
				for (int tx = 0; tx < tilesX; tx++) {
					for (int ty = 0; ty < tilesY; ty++) {
						int drawW = Math.min(tileSize, width - tx * tileSize);
						int drawH = Math.min(tileSize, height - ty * tileSize);
						graphics.blit(tex, x + tx * tileSize, y + ty * tileSize, 0, 0, drawW, drawH, drawW, drawH);
					}
				}
			} else if (uv != null && uv.length >= 4) {
				int u0 = uv[0];
				int v0 = uv[1];
				int u1 = uv.length >= 4 ? uv[2] : u0 + width;
				int v1 = uv.length >= 4 ? uv[3] : v0 + height;
				graphics.blit(tex, x, y, u0, v0, width, height, u1 - u0, v1 - v0);
			} else {
				graphics.blit(tex, x, y, 0, 0, width, height, width, height);
			}

			RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
			RenderSystem.disableBlend();
		}
	}
}