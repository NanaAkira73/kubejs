package dev.latvian.mods.kubejs.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.LanguageSelectScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.SafetyScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;

import java.util.Map;
import java.util.function.Consumer;

public class VanillaActions {
	public static void register(Map<String, Consumer<Screen>> actions) {
		actions.put("minecraft:singleplayer", screen -> {
			Minecraft.getInstance().setScreen(new SelectWorldScreen(screen));
		});

		actions.put("minecraft:multiplayer", screen -> {
			Minecraft.getInstance().setScreen(new SafetyScreen(new JoinMultiplayerScreen(screen)));
		});

		actions.put("minecraft:options", screen -> {
			Minecraft.getInstance().setScreen(new OptionsScreen(screen, Minecraft.getInstance().options));
		});

		actions.put("minecraft:language", screen -> {
			Minecraft.getInstance().setScreen(new LanguageSelectScreen(screen, Minecraft.getInstance().options, Minecraft.getInstance().getLanguageManager()));
		});

		actions.put("minecraft:quit", screen -> {
			Minecraft.getInstance().stop();
		});

		actions.put("minecraft:accessibility", screen -> {
			Minecraft.getInstance().setScreen(new AccessibilityOptionsScreen(screen, Minecraft.getInstance().options));
		});

		actions.put("kubejsui:toggle_shaders", screen -> {
			if (screen instanceof ScreenKubeJSUI uiScreen) {
				uiScreen.hasShader = !uiScreen.hasShader;
			}
		});
	}
}