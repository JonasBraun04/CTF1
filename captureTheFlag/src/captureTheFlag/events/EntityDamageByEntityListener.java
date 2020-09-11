package captureTheFlag.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {
	
	@EventHandler
	public void onEntityDamageEntityEvent(EntityDamageByEntityEvent event, Entity damager, Entity damagee) {
		
	}
}
