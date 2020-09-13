package captureTheFlag.events;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import captureTheFlag.main.Main;

public class EntityDamageListener implements Listener {
	private Main plugin;
	
	public EntityDamageListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if(event.getEntity().getType().equals(EntityType.ENDER_CRYSTAL))
			event.setCancelled(true);
		
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if(plugin.game.searchForPlayer(player)) {
				if(player.getHealth()<event.getDamage()) {
					player.teleport(plugin.game.getSpawnPoint(plugin.game.getCtfPlayer(player).getTeamColor()));
					event.setCancelled(true);
					player.setHealth(20);
					player.setFoodLevel(20);
					player.sendMessage(plugin.PREFIX+"Du bist gestorben!");
				}
			}
		}
	}
}
