package captureTheFlag.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import captureTheFlag.commands.RemoveFlagCommand;
import captureTheFlag.commands.SpawnFlagCommand;
import captureTheFlag.main.Main;
import captureTheFlag.utils.CtfPlayer;
import captureTheFlag.utils.FlagPoint;

public class PlayerMoveListener implements Listener {
	private Main plugin;
	
	public PlayerMoveListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(plugin.game.searchForPlayer(player)) {
			for(int i = 0; i<plugin.game.getFlagPoints().size(); i++) {
				handleEvent(plugin.game.getFlagPoints().get(i), plugin.game.getCtfPlayer(player), event);
			}
		}
	}
	
	public void handleEvent(FlagPoint flagPoint, CtfPlayer player, PlayerMoveEvent event) {
		FileConfiguration config = plugin.getConfig();
		double x = flagPoint.getLocation().getX()-event.getTo().getX();
		double y = flagPoint.getLocation().getY()-event.getTo().getY();
		double z = flagPoint.getLocation().getZ()-event.getTo().getZ();
		if(x<0)
			x=-x;
		if(y<0)
			y=-y;
		if(z<0)
			z=-z;
		if(x<1 && y<2 && z<1) {
			//Player take flag
			if(flagPoint.hasFlag() && !player.hasFlag() && !player.isAtFlagPoint(flagPoint)) {
				player.setFlag(true);
				player.setAtFlagPoint(flagPoint, true);
				RemoveFlagCommand.executeRemoveFlagCommand(flagPoint.getLocation(), flagPoint.getUUID());
				flagPoint.setUUID(null);
				Entity flag = player.getPlayer().getWorld().spawnEntity(event.getTo(), EntityType.ENDER_CRYSTAL);
				flag.setGlowing(true);
				player.getPlayer().addPassenger(flag);
			//Player give flag
			} else if(!flagPoint.hasFlag() && player.hasFlag() && !player.isAtFlagPoint(flagPoint)) {
				player.setFlag(false);
				player.setAtFlagPoint(flagPoint, true);
				SpawnFlagCommand.executeSpawnFlagCommand(plugin, flagPoint);
				player.getPlayer().getPassenger().remove();
			}
		} else {
			player.setAtFlagPoint(flagPoint, false);
		}
	}

}
