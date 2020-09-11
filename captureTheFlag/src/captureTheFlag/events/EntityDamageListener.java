package captureTheFlag.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event) {
		if(event.getEntity().getType().equals(EntityType.ENDER_CRYSTAL))
			event.setCancelled(true);
	}
}
