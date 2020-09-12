package captureTheFlag.commands;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class RemoveFlagCommand {
	
	public RemoveFlagCommand() {
	}
	
	public static void executeRemoveFlagCommand(Location location, UUID uuid) {
		for(Entity e: location.getWorld().getChunkAt(location).getEntities()) {
			if(e.getUniqueId().equals(uuid)) {
				e.remove();
				return;
			}
		}
	}
}
