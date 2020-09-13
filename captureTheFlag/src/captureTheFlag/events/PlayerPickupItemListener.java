package captureTheFlag.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import captureTheFlag.main.Main;

public class PlayerPickupItemListener implements Listener {
	private Main plugin;
	
	public PlayerPickupItemListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerPickupItemEvent(PlayerPickupItemEvent event) {
		Player player = event.getPlayer();
		if(!player.isOp()) {
			event.setCancelled(true);
			player.sendMessage(plugin.PREFIX+"Du kannst keine Items aufheben!");
		}
	}
}
