package captureTheFlag.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import captureTheFlag.main.Main;
import captureTheFlag.utils.FlagPoint;
import captureTheFlag.utils.TeamColor;

public class SetFlagPointCommand {
	private Main plugin;
	
	public SetFlagPointCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeSetFlagPointCommand(CommandSender sender, Command command, String label, String[] args, TeamColor color, Player player) {
		Location location = player.getLocation();
		plugin.game.addFlagPoint(new FlagPoint(color, location, plugin.game.getFlagPoints().size()));
		
		FileConfiguration config = plugin.getConfig();
		config.set("CTF.flag.amount", plugin.game.getFlagPoints().size());
		config.set("CTF.flag."+Integer.toString(plugin.game.getFlagPoints().size())+".Team", color.toString());
		config.set("CTF.flag."+Integer.toString(plugin.game.getFlagPoints().size())+".World", location.getWorld().getName());
		config.set("CTF.flag."+Integer.toString(plugin.game.getFlagPoints().size())+".X", location.getX());
		config.set("CTF.flag."+Integer.toString(plugin.game.getFlagPoints().size())+".Y", location.getY()+1);
		config.set("CTF.flag."+Integer.toString(plugin.game.getFlagPoints().size())+".Z", location.getZ());
		plugin.saveConfig();
	}
}
