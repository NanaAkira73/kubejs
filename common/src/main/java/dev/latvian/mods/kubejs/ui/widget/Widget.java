package dev.latvian.mods.kubejs.ui.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Widget {
	public Panel parent;
	public int x;
	public int y;
	public int width;
	public int height;
	public int w;
	public int h;
	public Component name;
	public String action;
	public float alpha;
	public boolean enabled;
	public boolean shadow;
	public int color;
	public int hoverColor;
	public ResourceLocation texture;
	public ResourceLocation hoverTexture;
	public List<Component> hoverTextComponents;

	public Widget() {
		this.parent = null;
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.w = 0;
		this.h = 0;
		this.name = null;
		this.action = null;
		this.alpha = 1F;
		this.enabled = true;
		this.shadow = false;
		this.color = 0xFFFFFFFF;
		this.hoverColor = 0xFFFFFFFF;
		this.texture = null;
		this.hoverTexture = null;
		this.hoverTextComponents = null;
	}

	public UI getUi() {
		Widget w = this;
		while (w.parent != null) {
			w = w.parent;
		}
		return (UI) w;
	}

	public void setName(Component name) {
		this.name = name;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isMouseOver(double mouseX, double mouseY) {
		return mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
	}

	public void renderBackground(GuiGraphics graphics, float partialTicks) {
	}

	public void renderForeground(GuiGraphics graphics, float partialTicks) {
	}

	public void tick() {
	}

	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (action != null && !action.isEmpty()) {
			getUi().screen.handleAction(action);
		}
	}

	public void mouseReleased(double mouseX, double mouseY, int button) {
	}

	public void mouseScrolled(double mouseX, double mouseY, double scroll) {
	}

	public void keyPressed(int keyCode, int scanCode, int modifiers) {
	}

	public void keyReleased(int keyCode, int scanCode, int modifiers) {
	}

	public void charTyped(char codePoint, int modifiers) {
	}

	public void collectWidgets(List<Widget> list) {
		list.add(this);
	}

	public void setHoverText(List<Component> text) {
		this.hoverTextComponents = text;
	}

	@Nullable
	public List<Component> getHoverText() {
		return hoverTextComponents;
	}
}