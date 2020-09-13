package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import captureTheFlag.main.Main;

public class BlockBreakListener implements Listener {
	private Main plugin;
	
	public BlockBreakListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		if(plugin.game.searchForPlayer(player)) {
			if(block.getType().equals(Material.BLUE_WOOL) || block.getType().equals(Material.RED_WOOL)) {
				if(!player.getItemInHand().getType().equals(Material.SHEARS)) {
					event.setCancelled(true);
					player.sendMessage(plugin.PREFIX+"Benutzte deine "+plugin.PREFIX+"Schere zum Wolle abzubauen.");
				} else {
					event.setDropItems(false);
					//player.getItemInHand().setDurability((short) 238);
				}
			} else {
				event.setCancelled(true);
				player.sendMessage(plugin.PREFIX+"Du kannst nur Wolle abbauen.");
			}
		} else if(!player.isOp()) {
			event.setCancelled(true);
			player.sendMessage(plugin.PREFIX+"Du hast nicht die nötige Berechtigung um Blöcke abzubauen.");
		}
	}
}
