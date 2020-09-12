package captureTheFlag.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import captureTheFlag.main.Main;
import captureTheFlag.utils.FlagPoint;

public class RemoveFlagPointCommand {
	private Main plugin;
	
	public RemoveFlagPointCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeRemoveFlagPointCommand(CommandSender sender, int index) {
		FlagPoint flagPoint = plugin.game.getFlagPoints().get(index-1);
		plugin.game.removeFlagPoint(flagPoint);
		FileConfiguration config = plugin.getConfig();
		config.set("CTF.flag."+index, "");
		config.set("CTF.flag.amount", config.getInt("CTF.flag.amount")-1);
		plugin.saveConfig();
		
		if(flagPoint.hasFlag()) {
			RemoveFlagCommand.executeRemoveFlagCommand(flagPoint.getLocation(), flagPoint.getUUID());
		}
	}
}
