package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.client.gui.screens.Screen;

/**
 * Core event handler for KubeJS UI system.
 * Platform-specific code (Forge/Fabric) should call screenOpening()
 * when a screen is about to open.
 */
public class KubeJSUIEventHandler {
	public static Screen screenOpening(Screen screen) {
		if (screen == null) {
			return null;
		}

		// Don't intercept our own screens
		if (screen instanceof ScreenKubeJSUI) {
			return screen;
		}

		String screenId = UIData.getScreenId(screen);

		if (screenId == null || screenId.isEmpty()) {
			return screen;
		}

		// Check if there's a UI event for this screen
		UIEventJS event = new UIEventJS();

		// Post to appropriate event based on screen ID
		if (screenId.equals("main_menu") && UIEvents.MAIN_MENU.hasListeners()) {
			UIEvents.MAIN_MENU.post(ScriptType.CLIENT, event);
		}

		// If the event was cancelled (replace called), create our screen
		if (event.consumer != null) {
			ScreenKubeJSUI uiScreen = new ScreenKubeJSUI(screenId, screen, event.consumer, event.forcedScale);
			return uiScreen;
		}

		return screen;
	}
}