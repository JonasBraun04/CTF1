package captureTheFlag.commands;

import org.bukkit.entity.Entity;

import captureTheFlag.utils.FlagPoint;

public class RemoveFlagCommand {
	
	public RemoveFlagCommand() {
	}
	
	public static void executeRemoveFlagCommand(FlagPoint flagPoint) {
		try {
			for(Entity e: flagPoint.getLocation().getWorld().getChunkAt(flagPoint.getLocation()).getEntities()) {
				if(e.getUniqueId().equals(flagPoint.getUUID())) {
					e.remove();
				return;
				}
			}
		} finally {
			flagPoint.clearUUID();
		}
	}
}
