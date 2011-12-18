package com.gray101.mc.ultramap.steps;

import org.bukkit.map.MapCanvas;
import org.bukkit.map.MinecraftFont;

public class DrawText implements DrawStep {

	private int x;
	private int y;
	private String text;
	
	public DrawText(int x, int y, byte color, String text) {
		this.x = x;
		this.y = y;
		this.text = "\u00A7" + color + ";" + text;
	}
	
	@Override
	public void performStep(MapCanvas canvas) {
		canvas.drawText(x, y, MinecraftFont.Font, text);
	}

}
