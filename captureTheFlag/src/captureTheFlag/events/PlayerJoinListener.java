package captureTheFlag.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import captureTheFlag.commands.SpawnCommand;
import captureTheFlag.main.Main;
import captureTheFlag.utils.ItemBuilder;

public class PlayerJoinListener implements Listener {
	private Main plugin;
	private SpawnCommand spawnCommand;
	
	public PlayerJoinListener(Main plugin) {
		this.plugin = plugin;
		spawnCommand = new SpawnCommand(this.plugin);
	}
	
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		spawnCommand.executeSpawnCommand(player);
		event.setJoinMessage(Main.PREFIX+player.getName()+" ist "+Main.PREFIX+" beigetreten.");
		player.sendMessage(Main.PREFIX+"Herzlich Willkommen bei "+Main.PREFIX);
		
		player.getInventory().clear();
		player.getInventory().setItem(0, new ItemBuilder(Material.BEACON).setName(Main.PREFIX+"select Team").setLore("Klicke um dein Team auszuwählen.").addEnchantment(Enchantment.ARROW_INFINITE, 10).build());
	}
	
}
