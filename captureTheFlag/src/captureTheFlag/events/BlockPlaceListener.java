package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import captureTheFlag.main.Main;

public class BlockPlaceListener implements Listener {
private Main plugin;
	
	public BlockPlaceListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		if(plugin.game.searchForPlayer(player)) {
			if(block.getType().equals(Material.BLUE_WOOL) || block.getType().equals(Material.RED_WOOL)) {
				player.getInventory().getItemInMainHand().setAmount(64);
			} else {
				event.setCancelled(true);
				player.sendMessage(Main.PREFIX+"Du kannst nur Wolle platzieren.");
			}
		} else if(!player.isOp()) {
			event.setCancelled(true);
			player.sendMessage(Main.PREFIX+"Du hast nicht die nötige Berechtigung um Blöcke zu platzieren.");
		} else if(block.getType().equals(Material.BEACON)) {
			event.setCancelled(true);
		}
	}
}
