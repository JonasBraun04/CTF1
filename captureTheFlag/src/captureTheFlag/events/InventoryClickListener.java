package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import captureTheFlag.commands.JoinTeamCommand;
import captureTheFlag.commands.SelectTeamCommand;
import captureTheFlag.main.Main;
import captureTheFlag.utils.TeamColor;

public class InventoryClickListener implements Listener {
	private Main plugin;
	private SelectTeamCommand selectTeamCommand;
	private JoinTeamCommand joinTeamCommand;
	
	public InventoryClickListener(Main plugin) {
		this.plugin = plugin;
		selectTeamCommand = new SelectTeamCommand();
		joinTeamCommand = new JoinTeamCommand(this.plugin);
	}
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		if(event.getView().getTitle().equals(Main.PREFIX+"select Team")) {
			try {
				TeamColor color;
				if(item.getType().equals(Material.BLUE_WOOL)) {
					color = TeamColor.BLUE;
				} else if(item.getType().equals(Material.RED_WOOL)) {
					color = TeamColor.RED;
				} else {
					return;
				}
				player.getOpenInventory().close();
				joinTeamCommand.executeJoinTeamCommand(player, color);
			} finally {
				event.setCancelled(true);
			}
		} else if(item.getType()==Material.BEACON) {
			selectTeamCommand.executeSelectTeamCommand(player);
		}
	}
}
