package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.ui.widget.UI;

import java.util.function.Consumer;

public class UIEventJS extends EventJS {
	public Consumer<UI> consumer;
	public int forcedScale = -1;

	@Override
	public boolean canCancel() {
		return true;
	}

	public void replace(Consumer<UI> consumer) {
		this.consumer = consumer;
		cancel();
	}

	public void setForcedScale(int scale) {
		this.forcedScale = scale;
	}
}