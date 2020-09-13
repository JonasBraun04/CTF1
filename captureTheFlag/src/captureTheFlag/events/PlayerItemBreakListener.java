package captureTheFlag.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;

import captureTheFlag.main.Main;

public class PlayerItemBreakListener implements Listener {
	private Main plugin;
	
	public PlayerItemBreakListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerItemBreakEvent(PlayerItemBreakEvent event) {
		Player player = event.getPlayer();
		if(plugin.game.searchForPlayer(player)) {
			plugin.game.getCtfPlayer(player).getKit();
		}
	}

}
