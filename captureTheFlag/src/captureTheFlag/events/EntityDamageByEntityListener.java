package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import captureTheFlag.main.Main;

public class EntityDamageByEntityListener implements Listener {
	private Main plugin;
	
	public EntityDamageByEntityListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDamageEntityEvent(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player damager = (Player) event.getDamager();
			if(plugin.game.searchForPlayer(damager)) {
				if(!damager.getItemInHand().getType().equals(Material.NETHERITE_SWORD)) {
					event.setCancelled(true);
					damager.sendMessage(plugin.PREFIX+"Du kannst nur mit deinem "+plugin.PREFIX+"Sword angreifen.");
				} else {
					//damager.getItemInHand().setDurability((short) 2031);
				}
			} else {
				if(!damager.isOp()) {
					event.setCancelled(true);
					damager.sendMessage(plugin.PREFIX+"Du hast nicht die nötige berechtigung um Entitäten zu attackieren!");
				}
			}
		}
	}
}
