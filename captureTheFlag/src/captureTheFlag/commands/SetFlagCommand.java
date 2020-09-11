package captureTheFlag.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import captureTheFlag.main.Main;
import captureTheFlag.utils.TeamColor;

public class SetFlagCommand {
	private Main plugin;
	
	public SetFlagCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeSetFlagCommand(CommandSender sender, Command command, String label, String[] args, String color, Player player) {
		FileConfiguration config = plugin.getConfig();
		if(config.getString("CTF.flag."+color+".World") != null) {
			World world = Bukkit.getWorld(config.getString("CTF.flag."+color+".World"));
			double x = config.getDouble("CTF.flag."+color+".X");
			double y = config.getDouble("CTF.flag."+color+".Y");
			double z = config.getDouble("CTF.flag."+color+".Z");
			UUID id = UUID.fromString(config.getString("CTF.flag."+color+".ID"));
			Chunk chunk = world.getChunkAt(new Location(world, x, y, z));
			for(Entity e: chunk.getEntities()) {
				if(e.getUniqueId().equals(id))
					e.remove();
			}
		}
		Location location = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY()+1, player.getLocation().getZ());
		Entity flag = player.getWorld().spawnEntity(location, EntityType.ENDER_CRYSTAL);
		if(color.equals("blue"))
			flag.setCustomName("�9Flag");
		else
			flag.setCustomName("�cFlag");
		flag.setCustomNameVisible(true);
		flag.setGlowing(true);
			
		config.set("CTF.flag."+color+".World", location.getWorld().getName());
		config.set("CTF.flag."+color+".X", location.getX());
		config.set("CTF.flag."+color+".Y", location.getY());
		config.set("CTF.flag."+color+".Z", location.getZ());
		config.set("CTF.flag."+color+".ID", flag.getUniqueId().toString());
		plugin.saveConfig();
	}
}