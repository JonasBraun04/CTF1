package captureTheFlag.commands;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import captureTheFlag.main.Main;
import captureTheFlag.utils.ItemBuilder;

public class SpawnCommand {
	private Main plugin;
	
	public SpawnCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeSpawnCommand(Player player) {
		player.teleport(plugin.game.getSpawn());
		player.getInventory().clear();
		player.getInventory().setItem(0, new ItemBuilder(Material.BEACON).setName(Main.PREFIX+"select Team").setLore("Klicke um dein Team auszuwählen.").addEnchantment(Enchantment.ARROW_INFINITE, 10).build());
	}
}
