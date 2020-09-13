package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

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
				ItemStack item = damager.getInventory().getItemInMainHand();
				if(!item.getType().equals(Material.NETHERITE_SWORD)) {
					event.setCancelled(true);
					damager.sendMessage(Main.PREFIX+"Du kannst nur mit deinem "+Main.PREFIX+"Sword angreifen.");
				}
			} else {
				if(!damager.isOp()) {
					event.setCancelled(true);
					damager.sendMessage(Main.PREFIX+"Du hast nicht die nötige berechtigung um Entitäten zu attackieren!");
				}
			}
		}
	}
}
