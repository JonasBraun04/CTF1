package captureTheFlag.commands;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import captureTheFlag.main.Main;

public class SetSpawnCommand {
	private Main plugin;
	
	public SetSpawnCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeSetSpawnCommand(Player player) {
		Location location = player.getLocation();
		plugin.game.setSpawn(location);
		
		FileConfiguration config = plugin.getConfig();
		config.set("CTF.spawn.lobby.World", location.getWorld().getName());
		config.set("CTF.spawn.lobby.X", location.getX());
		config.set("CTF.spawn.lobby.Y", location.getY());
		config.set("CTF.spawn.lobby.Z", location.getZ());
		config.set("CTF.spawn.lobby.Yaw", location.getYaw());
		config.set("CTF.spawn.lobby.Pitch", location.getPitch());
		plugin.saveConfig();
	}
	
}
