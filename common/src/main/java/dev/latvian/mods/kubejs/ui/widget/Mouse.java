package dev.latvian.mods.kubejs.ui.widget;

public class Mouse {
	public double x;
	public double y;
	public boolean leftDown;
	public boolean rightDown;
	public boolean middleDown;

	public Mouse() {
		this.x = 0;
		this.y = 0;
		this.leftDown = false;
		this.rightDown = false;
		this.middleDown = false;
	}
}