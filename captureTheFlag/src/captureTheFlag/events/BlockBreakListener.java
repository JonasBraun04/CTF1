package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

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
				ItemStack item = player.getInventory().getItemInMainHand();
				if(!item.getType().equals(Material.SHEARS)) {
					event.setCancelled(true);
					player.sendMessage(Main.PREFIX+"Benutzte deine "+Main.PREFIX+"Schere zum Wolle abzubauen.");
				} else {
					event.setDropItems(false);
				}
			} else {
				event.setCancelled(true);
				player.sendMessage(Main.PREFIX+"Du kannst nur Wolle abbauen.");
			}
		} else if(!player.isOp()) {
			event.setCancelled(true);
			player.sendMessage(Main.PREFIX+"Du hast nicht die nötige Berechtigung um Blöcke abzubauen.");
		}
	}
}
