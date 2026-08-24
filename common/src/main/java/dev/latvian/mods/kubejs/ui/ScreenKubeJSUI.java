package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.ui.widget.UI;
import dev.latvian.mods.kubejs.ui.widget.Widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;
import java.util.function.Consumer;

public class ScreenKubeJSUI extends Screen {
	public final Screen original;
	public final UI ui;
	public final Consumer<UI> consumer;
	public final int forcedScale;
	public boolean hasShader;

	public ScreenKubeJSUI(String id, Screen original, Consumer<UI> consumer, int forcedScale) {
		super(Component.literal("KubeJS UI: " + id));
		this.original = original;
		this.ui = new UI(this);
		this.consumer = consumer;
		this.forcedScale = forcedScale;
		this.hasShader = false;
	}

	@Override
	protected void init() {
		super.init();

		ui.width = width;
		ui.height = height;
		ui.w = width;
		ui.h = height;

		ui.children.clear();

		try {
			consumer.accept(ui);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ui.init();
	}

	@Override
	public void tick() {
		super.tick();
		ui.tick();
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		ui.mouse.x = mouseX;
		ui.mouse.y = mouseY;

		ui.renderBackground(graphics, partialTicks);
		ui.renderForeground(graphics, partialTicks);

		Widget hovered = null;
		for (Widget w : ui.allWidgets) {
			if (w.isMouseOver(mouseX, mouseY) && w.getHoverText() != null) {
				hovered = w;
				break;
			}
		}

		if (hovered != null && hovered.getHoverText() != null) {
			graphics.renderComponentTooltip(Minecraft.getInstance().font, hovered.getHoverText(), mouseX, mouseY);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		ui.mouse.x = mouseX;
		ui.mouse.y = mouseY;

		if (button == 0) ui.mouse.leftDown = true;
		if (button == 1) ui.mouse.rightDown = true;
		if (button == 2) ui.mouse.middleDown = true;

		ui.mouseClicked(mouseX, mouseY, button);
		return true;
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		ui.mouse.x = mouseX;
		ui.mouse.y = mouseY;

		if (button == 0) ui.mouse.leftDown = false;
		if (button == 1) ui.mouse.rightDown = false;
		if (button == 2) ui.mouse.middleDown = false;

		ui.mouseReleased(mouseX, mouseY, button);
		return true;
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
		ui.mouseScrolled(mouseX, mouseY, scroll);
		return true;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		ui.keyPressed(keyCode, scanCode, modifiers);
		return super.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		ui.keyReleased(keyCode, scanCode, modifiers);
		return super.keyReleased(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean charTyped(char codePoint, int modifiers) {
		ui.charTyped(codePoint, modifiers);
		return super.charTyped(codePoint, modifiers);
	}

	@Override
	public void onClose() {
		if (original != null) {
			Minecraft.getInstance().setScreen(original);
		} else {
			super.onClose();
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	public void handleAction(String action) {
		UIData.handleAction(action);
	}
}