package captureTheFlag.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import captureTheFlag.main.Main;

public class SetSpawnCommand {
	private Main plugin;
	
	public SetSpawnCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeSetSpawnCommand(CommandSender sender, Command command, String label, String[] args, String color, Player player) {
		FileConfiguration config = plugin.getConfig();
		config.set("CTF.spawn."+color+".World", player.getWorld().getName());
		config.set("CTF.spawn."+color+".X", player.getLocation().getX());
		config.set("CTF.spawn."+color+".Y", player.getLocation().getY());
		config.set("CTF.spawn."+color+".Z", player.getLocation().getZ());
		config.set("CTF.spawn."+color+".Yaw", player.getLocation().getYaw());
		config.set("CTF.spawn."+color+".Pitch", player.getLocation().getPitch());
		plugin.saveConfig();
	}
}
