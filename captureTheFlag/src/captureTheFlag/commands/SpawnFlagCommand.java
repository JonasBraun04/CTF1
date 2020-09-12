package captureTheFlag.commands;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import captureTheFlag.main.Main;
import captureTheFlag.utils.FlagPoint;

public class SpawnFlagCommand {
	
	public SpawnFlagCommand() {
	}
	
	public static void executeSpawnFlagCommand(Main plugin, FlagPoint flagPoint) {
		Entity flag = flagPoint.getLocation().getWorld().spawnEntity(flagPoint.getLocation(), EntityType.ENDER_CRYSTAL);
		flag.setCustomName(flagPoint.getTeamColor().getColorCode()+" Flag");
		flag.setCustomNameVisible(true);
		flag.setGlowing(true);
		flagPoint.setUUID(flag.getUniqueId());
	}
}
