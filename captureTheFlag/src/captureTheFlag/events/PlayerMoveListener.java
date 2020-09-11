package captureTheFlag.events;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import captureTheFlag.main.Main;

public class PlayerMoveListener implements Listener {
	private Main plugin;
	private boolean oldBlue = false;
	private boolean oldRed = false;
	
	public PlayerMoveListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		//TODO Player ingame check
		handleEvent("blue", player, event, plugin.blueFlag, oldBlue);
		handleEvent("red", player, event, plugin.redFlag, oldRed);
	}
	
	public void handleEvent(String color, Player player, PlayerMoveEvent event, boolean flagState, boolean old) {
		FileConfiguration config = plugin.getConfig();
		double X = config.getDouble("CTF.flag."+color+".X");
		double Y = config.getDouble("CTF.flag."+color+".Y");
		double Z = config.getDouble("CTF.flag."+color+".Z");
		double x = X-event.getTo().getX();
		double y = Y-event.getTo().getY();
		double z = Z-event.getTo().getZ();
		if(x<0)
			x=-x;
		if(y<0)
			y=-y;
		if(z<0)
			z=-z;
		if(x<1 && y<2 && z<1) {
			World world = Bukkit.getWorld(config.getString("CTF.flag."+color+".World"));
			Location location = new Location(world, X, Y, Z);
			Chunk chunk = world.getChunkAt(location);
			UUID id = UUID.fromString(config.getString("CTF.flag."+color+".ID"));
			if(flagState==true && old == false) {
				setFlagState(false, color);
				setOldState(true, color);
				for(Entity e: chunk.getEntities()) {
				if(e.getUniqueId().equals(id))
					e.remove();
				}
				Entity flagCatched = player.getWorld().spawnEntity(event.getTo(), EntityType.ENDER_CRYSTAL);
				flagCatched.setGlowing(true);
				player.addPassenger(flagCatched);
			} else if(flagState==false && old == false) {
				setFlagState(true, color);
				setOldState(true, color);
				player.getPassenger().remove();
				Entity flag = world.spawnEntity(location, EntityType.ENDER_CRYSTAL);
				if(color.equals("blue"))
					flag.setCustomName("§9Flag");
				else
					flag.setCustomName("§cFlag");
				flag.setCustomNameVisible(true);
				flag.setGlowing(true);
				id = flag.getUniqueId();
				config.set("CTF.flag."+color+".ID", id.toString());
				plugin.saveConfig();
			}
		} else {
			setOldState(false, color);
		}
	}
	
	public void setFlagState(boolean value, String color) {
		if(color.equals("blue")) {
			plugin.blueFlag = value;
		} else {
			plugin.redFlag = value;
		}
	}
	
	public void setOldState(boolean value, String color) {
		if(color.equals("blue")) {
			oldBlue = value;
		} else {
			oldRed = value;
		}
	}

}
