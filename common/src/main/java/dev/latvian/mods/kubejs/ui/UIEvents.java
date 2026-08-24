package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface UIEvents {
	EventGroup GROUP = EventGroup.of("UIEvents");
	EventHandler MAIN_MENU = GROUP.client("mainMenu", () -> UIEventJS.class).hasResult();
	EventHandler REGISTER_ACTIONS = GROUP.startup("registerActions", () -> RegisterActionsEvent.class);
	EventHandler REGISTER_SCREENS = GROUP.startup("registerScreens", () -> RegisterScreensEvent.class);
}