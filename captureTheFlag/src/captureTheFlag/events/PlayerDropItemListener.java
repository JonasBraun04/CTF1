package captureTheFlag.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import captureTheFlag.main.Main;

public class PlayerDropItemListener implements Listener {
	
	public PlayerDropItemListener() {
	}
	
	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(!player.isOp()) {
			event.setCancelled(true);
			player.sendMessage(Main.PREFIX+"Du kannst deine Items nicht auf den Boden schmeiﬂen!");
		}
	}
	
}
