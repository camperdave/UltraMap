package com.gray101.mc.ultramap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import com.gray101.mc.ultramap.steps.DrawImage;
import com.gray101.mc.ultramap.steps.DrawPixel;
import com.gray101.mc.ultramap.steps.DrawStep;
import com.gray101.mc.ultramap.steps.DrawText;

public class MapYAMLRenderer extends MapRenderer {

	FileConfiguration config;
	List<DrawStep> drawSteps;
	
	public Logger log = Logger.getLogger("Minecraft");//Define your logger
	
	public MapYAMLRenderer(File configFile) {
		config = YamlConfiguration.loadConfiguration(configFile);
		
		drawSteps = new ArrayList<DrawStep>();
		
		Set<String> steps = config.getKeys(false);

		for(String s : steps) {
			this.log.info("Step: " + s);
			ConfigurationSection step = config.getConfigurationSection(s);
			String method = step.getString("method");
			if(method.equalsIgnoreCase("drawPixel")) {
				int x = step.getInt("x");
				int y = step.getInt("y");
				byte color = (byte) step.getInt("color");
				drawSteps.add(new DrawPixel(x, y, color));
			}
			else if(method.equalsIgnoreCase("drawText")) {
				int x = step.getInt("x");
				int y = step.getInt("y");
				byte color = (byte) step.getInt("color");
				String text = step.getString("text");
				drawSteps.add(new DrawText(x, y, color, text));
			}
			else if(method.equalsIgnoreCase("drawImage")) {
				int x = step.getInt("x");
				int y = step.getInt("y");
				String path = configFile.getParent() + File.separator + step.getString("path");
				try {
					drawSteps.add(new DrawImage(x, y, path));
				}
				catch (IOException e) {
					this.log.info("ERROR: Couldn't load image \"" + path + "\"");
				}
			}
		}
	}
	
	@Override
	public void render(MapView mapView, MapCanvas canvas, Player player) {
		for(DrawStep ds : drawSteps) {
			ds.performStep(canvas);
		}
	}

}
