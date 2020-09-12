package captureTheFlag.commands;

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
	
	public void executeSetSpawnCommand(CommandSender sender, Command command, String label, String[] args, TeamColor color, Player player) {
		FileConfiguration config = plugin.getConfig();
		config.set("CTF.spawn."+color.toString()+".World", player.getWorld().getName());
		config.set("CTF.spawn."+color.toString()+".X", player.getLocation().getX());
		config.set("CTF.spawn."+color.toString()+".Y", player.getLocation().getY());
		config.set("CTF.spawn."+color.toString()+".Z", player.getLocation().getZ());
		config.set("CTF.spawn."+color.toString()+".Yaw", player.getLocation().getYaw());
		config.set("CTF.spawn."+color.toString()+".Pitch", player.getLocation().getPitch());
		plugin.saveConfig();
	}
}
