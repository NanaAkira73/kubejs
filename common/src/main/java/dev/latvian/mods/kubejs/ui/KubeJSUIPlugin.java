package dev.latvian.mods.kubejs.ui;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ClassFilter;

public class KubeJSUIPlugin extends KubeJSPlugin {
	@Override
	public void init() {
		UIData.init();
	}

	@Override
	public void registerEvents() {
		UIEvents.GROUP.register();
	}

	@Override
	public void registerClasses(ScriptType type, ClassFilter filter) {
		filter.allow("dev.latvian.mods.kubejs.ui");
		filter.allow("dev.latvian.mods.kubejs.ui.widget");
	}

	@Override
	public void registerBindings(BindingsEvent event) {
		event.add("UIEvents", UIEvents.class);
	}
}