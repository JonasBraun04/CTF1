package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import captureTheFlag.commands.GetKitCommand;
import captureTheFlag.commands.SpawnCommand;
import captureTheFlag.main.Main;
import captureTheFlag.utils.ItemBuilder;

public class PlayerDeathListener implements Listener {
	private Main plugin;
	private SpawnCommand spawnCommand;
	private GetKitCommand getKitCommand;
	
	public PlayerDeathListener(Main plugin) {
		this.plugin = plugin;
		spawnCommand = new SpawnCommand(this.plugin);
		getKitCommand = new GetKitCommand(this.plugin);
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		Player player = (Player) event.getEntity();
		event.setKeepInventory(true);
		event.setDeathMessage(null);
		if(plugin.game.searchForPlayer(player)) {
			player.teleport(plugin.game.getSpawnPoint(plugin.game.getCtfPlayer(player).getTeamColor()));
			getKitCommand.executeGetKitCommand(player);
		} else {
			spawnCommand.executeSpawnCommand(player);
			player.getInventory().clear();
			player.getInventory().setItem(0, new ItemBuilder(Material.BEACON).setName(Main.PREFIX+"select Team").setLore("Klicke um dein Team auszuwählen.").addEnchantment(Enchantment.ARROW_INFINITE, 10).build());
		}
	}
}
