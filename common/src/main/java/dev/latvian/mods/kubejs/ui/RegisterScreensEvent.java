package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.event.EventJS;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RegisterScreensEvent extends EventJS {
	public final Map<String, Consumer<net.minecraft.client.gui.screens.Screen>> screens = new HashMap<>();

	public void register(String id, Consumer<net.minecraft.client.gui.screens.Screen> screenFactory) {
		screens.put(id, screenFactory);
	}
}