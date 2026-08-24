package dev.latvian.mods.kubejs.ui.forge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.gui.ModListScreen;

import java.util.Map;
import java.util.function.Consumer;

public class ForgeActions {
	public static void register(Map<String, Consumer<Screen>> actions) {
		actions.put("forge:mod_list", screen -> {
			Minecraft.getInstance().setScreen(new ModListScreen(screen));
		});
	}
}