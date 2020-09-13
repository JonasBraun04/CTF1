package captureTheFlag.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import captureTheFlag.main.Main;
import captureTheFlag.utils.TeamColor;

public class SetSpawnPointCommand {
	private Main plugin;
	
	public SetSpawnPointCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeSetSpawnPointCommand(CommandSender sender, Command command, String label, String[] args, TeamColor color, Player player) {
		Location location = player.getLocation();
		plugin.game.setSpawnPoint(color, location);
		
		FileConfiguration config = plugin.getConfig();
		config.set("CTF.spawn."+color.toString()+".World", location.getWorld().getName());
		config.set("CTF.spawn."+color.toString()+".X", location.getX());
		config.set("CTF.spawn."+color.toString()+".Y", location.getY());
		config.set("CTF.spawn."+color.toString()+".Z", location.getZ());
		config.set("CTF.spawn."+color.toString()+".Yaw", location.getYaw());
		config.set("CTF.spawn."+color.toString()+".Pitch", location.getPitch());
		plugin.saveConfig();
	}
}
