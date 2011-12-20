package com.gray101.mc.ultramap.steps;

import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;

public abstract class DrawStep {

	public abstract void performStep(MapCanvas canvas);
	
	public static byte minecraftColorFromString(String s) {
		if(s.equalsIgnoreCase("transparent"))
			return MapPalette.TRANSPARENT;
		else if(s.equalsIgnoreCase("lightGreen") || s.equalsIgnoreCase("light_green"))
			return MapPalette.LIGHT_GREEN;
		else if(s.equalsIgnoreCase("darkGreen") || s.equalsIgnoreCase("dark_green"))
			return MapPalette.DARK_GREEN;
		else if(s.equalsIgnoreCase("lightBrown") || s.equalsIgnoreCase("light_brown"))
			return MapPalette.LIGHT_BROWN;
		else if(s.equalsIgnoreCase("brown"))
			return MapPalette.BROWN;
		else if(s.equalsIgnoreCase("darkBrown") || s.equalsIgnoreCase("dark_brown"))
			return MapPalette.DARK_BROWN;
		else if(s.equalsIgnoreCase("lightGray") || s.equalsIgnoreCase("light_gray"))
			return MapPalette.LIGHT_GRAY;
		else if(s.equalsIgnoreCase("gray1") || s.equalsIgnoreCase("gray_1"))
			return MapPalette.GRAY_1;
		else if(s.equalsIgnoreCase("gray2") || s.equalsIgnoreCase("gray_2"))
			return MapPalette.GRAY_2;
		else if(s.equalsIgnoreCase("darkGray") || s.equalsIgnoreCase("dark_gray"))
			return MapPalette.DARK_GRAY;
		else if(s.equalsIgnoreCase("red"))
			return MapPalette.RED;
		else if(s.equalsIgnoreCase("paleBlue") || s.equalsIgnoreCase("pale_blue"))
			return MapPalette.PALE_BLUE;
		else if(s.equalsIgnoreCase("blue"))
			return MapPalette.BLUE;
		else if(s.equalsIgnoreCase("white"))
			return MapPalette.WHITE;
		return 0;
	}
	
}
