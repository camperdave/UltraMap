package com.gray101.mc.ultramap;

/*
    This file is part of ultramap

    UltraMap is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UltraMap is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UltraMap.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Logger;

public class UltraMap extends JavaPlugin {

	//ClassListeners
	private final MapListener mapListener = new MapListener(this);
	//ClassListeners

	public Location playerLoc;


	public Logger log = Logger.getLogger("Minecraft");//Define your logger


	public void onDisable() {
		log.info("Disabled message here, shown in console on startup");
	}

	public void onEnable() {
		log.info("Enabling ultramap Plugin");

		PluginManager pm = this.getServer().getPluginManager();

		pm.registerEvent(Event.Type.MAP_INITIALIZE, this.mapListener, Event.Priority.Normal, this);

		this.addRendererForAllMaps();

	}

	private void addRendererForAllMaps() {
		// Add renderers to every existing map.
		short mapNum = 0;
		MapView attemptMap = this.getServer().getMap(mapNum);
		while(attemptMap != null) {
			this.addRendererForMap(attemptMap);
			mapNum++;
			attemptMap = this.getServer().getMap(mapNum);
		}
	}

	public void addRendererForMap(MapView map) {
		File mapConfig = new File(this.getDataFolder(), "map" + map.getId() + ".yml");
		this.log.info("Config file for map# " + map.getId() + " exists? " + mapConfig.exists());

		if(mapConfig.exists()) {
			map.addRenderer(new MapYAMLRenderer(mapConfig));
		}
	}

	public void removeRendererForMap(MapView map) {
		for (MapRenderer renderer : map.getRenderers()) {
			if(renderer.toString().contains("MapYAMLRenderer")) {
				map.removeRenderer(renderer);
			}
		}
	}

	private void removeRendererForAllMaps() {
		// Add renderers to every existing map.
		short mapNum = 0;
		MapView attemptMap = this.getServer().getMap(mapNum);
		while(attemptMap != null) {
			this.removeRendererForMap(attemptMap);
			mapNum++;
			attemptMap = this.getServer().getMap(mapNum);
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("ultramap")){ // If the player typed /basic then do the following...
			if (args.length > 0) {
				String subCmd = args[0];
				if(subCmd.equalsIgnoreCase("reload")) {
					if(sender.hasPermission("ultramap.reload")) {
						this.removeRendererForAllMaps();
						this.addRendererForAllMaps();
						return true;
					}
				}
			}
		} //If this has happened the function will break and return true. if this hasn't happened the a value of false will be returned.
		return false; 
	}

}

