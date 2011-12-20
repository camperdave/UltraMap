package com.gray101.mc.ultramap.steps;

import org.bukkit.map.MapCanvas;

public class DrawPixel extends DrawStep {

	private int x;
	private int y;
	private byte color;
	
	public DrawPixel(int x, int y, byte color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	@Override
	public void performStep(MapCanvas canvas) {
		canvas.setPixel(x, y, color);
	}

}
