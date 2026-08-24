package dev.latvian.mods.kubejs.ui.widget;

import dev.latvian.mods.kubejs.ui.ScreenKubeJSUI;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class UI extends Panel {
	public ScreenKubeJSUI screen;
	public Mouse mouse;
	public List<Widget> allWidgets;
	public ResourceLocation widgetTexture;
	public int tick;
	public long startTime;
	public boolean hasShader;

	public UI(ScreenKubeJSUI screen) {
		super();
		this.screen = screen;
		this.mouse = new Mouse();
		this.allWidgets = new ArrayList<>();
		this.widgetTexture = new ResourceLocation("minecraft", "textures/gui/widgets.png");
		this.tick = 0;
		this.startTime = System.currentTimeMillis();
		this.hasShader = false;
	}

	@Override
	public UI getUi() {
		return this;
	}

	@Override
	public void renderBackground(GuiGraphics graphics, float partialTicks) {
		hasShader = false;
		super.renderBackground(graphics, partialTicks);
	}

	@Override
	public void renderForeground(GuiGraphics graphics, float partialTicks) {
		super.renderForeground(graphics, partialTicks);
	}

	@Override
	public void tick() {
		tick++;
		super.tick();
	}

	@Override
	public void collectWidgets(List<Widget> list) {
		allWidgets.clear();
		super.collectWidgets(list);
		allWidgets.addAll(list);
	}

	public void init() {
		allWidgets.clear();
		collectWidgets(allWidgets);
	}
}