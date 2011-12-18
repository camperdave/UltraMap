package com.gray101.mc.ultramap;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.map.MapView;



public class MapListener extends ServerListener {

	private UltraMap plugin;

	public MapListener(UltraMap plugin) {
		this.plugin = plugin;
	}

	public void onMapInitialize(MapInitializeEvent event)
	{
		MapView map = event.getMap();
		plugin.addRendererForMap(map);
	}
}

