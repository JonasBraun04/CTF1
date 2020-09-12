package captureTheFlag.commands;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import captureTheFlag.main.Main;
import captureTheFlag.utils.FlagPoint;

public class SpawnFlagCommand {
	private Main plugin;
	
	public SpawnFlagCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeSpawnFlagCommand(int index) {
		FlagPoint flagPoint = plugin.game.getFlagPoints().get(index-1);
		Entity flag = flagPoint.getLocation().getWorld().spawnEntity(flagPoint.getLocation(), EntityType.ENDER_CRYSTAL);
		flag.setCustomName(flagPoint.getTeamColor().getColorCode()+" Flag");
		flag.setCustomNameVisible(true);
		flag.setGlowing(true);
		plugin.game.setUUID(index-1, flag.getUniqueId());
	}
}
