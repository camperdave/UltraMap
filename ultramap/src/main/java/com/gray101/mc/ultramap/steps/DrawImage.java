package com.gray101.mc.ultramap.steps;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bukkit.map.MapCanvas;

public class DrawImage implements DrawStep {

	private int x;
	private int y;
	private Image img;
	
	public DrawImage(int x, int y, String path) throws IOException {
		this.x = x;
		this.y = y;
		this.img = ImageIO.read(new File(path));
	}
	
	@Override
	public void performStep(MapCanvas canvas) {
		canvas.drawImage(x, y, img);
	}

}
