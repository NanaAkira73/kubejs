package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.PauseScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class UIData {
	private static final Map<String, Consumer<Screen>> ACTIONS = new HashMap<>();
	private static boolean initialized = false;

	public static void init() {
		if (initialized) {
			return;
		}
		initialized = true;

		// Register vanilla actions
		VanillaActions.register(ACTIONS);

		// Register platform-specific actions
		try {
			Class<?> forgeActions = Class.forName("dev.latvian.mods.kubejs.ui.forge.ForgeActions");
			java.lang.reflect.Method register = forgeActions.getMethod("register", Map.class);
			register.invoke(null, ACTIONS);
		} catch (Exception ignored) {
		}

		// Allow scripts to register custom actions
		if (UIEvents.REGISTER_ACTIONS.hasListeners()) {
			var event = new RegisterActionsEvent();
			UIEvents.REGISTER_ACTIONS.post(ScriptType.STARTUP, event);
			ACTIONS.putAll(event.actions);
		}
	}

	public static void handleAction(String action) {
		init();

		Consumer<Screen> handler = ACTIONS.get(action);
		if (handler != null) {
			Minecraft mc = Minecraft.getInstance();
			handler.accept(mc.screen);
		} else if (action.startsWith("https://") || action.startsWith("http://")) {
			// Open URL
			net.minecraft.Util.getPlatform().openUri(action);
		}
	}

	public static void registerAction(String id, Consumer<Screen> action) {
		ACTIONS.put(id, action);
	}

	public static String getScreenId(Screen screen) {
		if (screen instanceof TitleScreen) {
			return "main_menu";
		}
		if (screen instanceof PauseScreen) {
			return "pause_menu";
		}
		// Return the class name as a fallback
		return screen.getClass().getSimpleName();
	}
}