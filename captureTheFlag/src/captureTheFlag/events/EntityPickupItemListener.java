package captureTheFlag.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import captureTheFlag.main.Main;

public class EntityPickupItemListener implements Listener {
	
	public EntityPickupItemListener() {
	}
	
	@EventHandler
	public void onEntityPickupItemEvent(EntityPickupItemEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if(!player.isOp()) {
				event.setCancelled(true);
				player.sendMessage(Main.PREFIX+"Du kannst keine Items aufheben!");
			}
		}
	}
}
