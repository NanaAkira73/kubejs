package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.event.EventJS;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RegisterActionsEvent extends EventJS {
	public final Map<String, Consumer<net.minecraft.client.gui.screens.Screen>> actions = new HashMap<>();

	public void register(String id, Consumer<net.minecraft.client.gui.screens.Screen> action) {
		actions.put(id, action);
	}
}