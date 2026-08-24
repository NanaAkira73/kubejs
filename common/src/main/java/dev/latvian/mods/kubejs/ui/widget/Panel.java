package dev.latvian.mods.kubejs.ui.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Panel extends Widget {
	public final List<Widget> children = new ArrayList<>();

	public Panel() {
	}

	public <W extends Widget> W widget(Supplier<W> supplier, Consumer<W> consumer) {
		W w = supplier.get();
		w.parent = this;
		consumer.accept(w);
		children.add(w);
		return w;
	}

	public Image image(Consumer<Image> consumer) {
		return widget(Image::new, consumer);
	}

	public Label label(Consumer<Label> consumer) {
		return widget(Label::new, consumer);
	}

	public Button button(Consumer<Button> consumer) {
		return widget(Button::new, consumer);
	}

	public Panel panel(Consumer<Panel> consumer) {
		return widget(Panel::new, consumer);
	}

	public Shader shader(Consumer<Shader> consumer) {
		return widget(Shader::new, consumer);
	}

	public Panorama panorama(Consumer<Panorama> consumer) {
		return widget(Panorama::new, consumer);
	}

	public void fillBackground(String texture, int w, int h) {
		image(i -> {
			i.texture = new ResourceLocation(texture);
			i.width = w;
			i.height = h;
			i.x = 0;
			i.y = 0;
		});
	}

	@Override
	public void renderBackground(GuiGraphics graphics, float partialTicks) {
		for (Widget child : children) {
			if (child.enabled && child.alpha > 0F) {
				child.renderBackground(graphics, partialTicks);
			}
		}
	}

	@Override
	public void renderForeground(GuiGraphics graphics, float partialTicks) {
		for (Widget child : children) {
			if (child.enabled && child.alpha > 0F) {
				child.renderForeground(graphics, partialTicks);
			}
		}
	}

	@Override
	public void tick() {
		for (Widget child : children) {
			child.tick();
		}
	}

	@Override
	public void collectWidgets(List<Widget> list) {
		list.add(this);
		for (Widget child : children) {
			child.collectWidgets(list);
		}
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int button) {
		for (int i = children.size() - 1; i >= 0; i--) {
			Widget child = children.get(i);
			if (child.enabled && child.isMouseOver(mouseX, mouseY)) {
				child.mouseClicked(mouseX, mouseY, button);
				return;
			}
		}
	}

	@Override
	public void mouseReleased(double mouseX, double mouseY, int button) {
		for (int i = children.size() - 1; i >= 0; i--) {
			Widget child = children.get(i);
			if (child.enabled && child.isMouseOver(mouseX, mouseY)) {
				child.mouseReleased(mouseX, mouseY, button);
				return;
			}
		}
	}

	@Override
	public void mouseScrolled(double mouseX, double mouseY, double scroll) {
		for (int i = children.size() - 1; i >= 0; i--) {
			Widget child = children.get(i);
			if (child.enabled && child.isMouseOver(mouseX, mouseY)) {
				child.mouseScrolled(mouseX, mouseY, scroll);
				return;
			}
		}
	}

	@Override
	public void keyPressed(int keyCode, int scanCode, int modifiers) {
		for (int i = children.size() - 1; i >= 0; i--) {
			Widget child = children.get(i);
			if (child.enabled) {
				child.keyPressed(keyCode, scanCode, modifiers);
			}
		}
	}

	@Override
	public void keyReleased(int keyCode, int scanCode, int modifiers) {
		for (int i = children.size() - 1; i >= 0; i--) {
			Widget child = children.get(i);
			if (child.enabled) {
				child.keyReleased(keyCode, scanCode, modifiers);
			}
		}
	}

	@Override
	public void charTyped(char codePoint, int modifiers) {
		for (int i = children.size() - 1; i >= 0; i--) {
			Widget child = children.get(i);
			if (child.enabled) {
				child.charTyped(codePoint, modifiers);
			}
		}
	}
}