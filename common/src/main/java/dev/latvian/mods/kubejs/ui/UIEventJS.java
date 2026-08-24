package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.event.EventExit;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.ui.widget.UI;

import java.util.function.Consumer;

public class UIEventJS extends EventJS {
	public Consumer<UI> consumer;
	public int forcedScale = -1;

	public void replace(Consumer<UI> consumer) throws EventExit {
		this.consumer = consumer;
		cancel();
	}

	public void setForcedScale(int scale) {
		this.forcedScale = scale;
	}
}