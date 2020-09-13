package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import captureTheFlag.main.Main;

public class PlayerItemConsumeListener implements Listener {
	private Main plugin;
	
	public PlayerItemConsumeListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		if(plugin.game.searchForPlayer(player)) {
			event.setCancelled(true);
			if(!event.getItem().getType().equals(Material.COOKIE)) {
				player.sendMessage(plugin.PREFIX+"Du kannst nur UlTIMATE COOKIES essen!");
			} else {
				player.setFoodLevel(20);
			}
		} else if(!player.isOp()) {
			event.setCancelled(true);
			player.sendMessage(plugin.PREFIX+"Du kannst keine Nahrung zu dir nehmen.");
		}
	}
	
}
