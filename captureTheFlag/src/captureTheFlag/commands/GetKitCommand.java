package captureTheFlag.commands;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import captureTheFlag.main.Main;
import captureTheFlag.utils.ItemBuilder;
import captureTheFlag.utils.TeamColor;

public class GetKitCommand {
	private Main plugin;
	
	public GetKitCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeGetKitCommand(Player player) {
		Inventory inventory = player.getInventory();
		inventory.clear();
		inventory.setItem(0, new ItemBuilder(Material.NETHERITE_SWORD).setName(Main.PREFIX+"SWORD").setLore("An actual really powerful sword.", "Be careful!").setAmount(1).setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.ARROW_INFINITE, 10).addEnchantment(Enchantment.ARROW_DAMAGE, 10).addEnchantment(Enchantment.DAMAGE_ALL, 5).build());
		inventory.setItem(1, new ItemBuilder(Material.CROSSBOW).setName(Main.PREFIX+"X-BOW").setLore("The legend X-Bow of strengthness.", "Do never target the wrong person!").setAmount(1).setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.ARROW_INFINITE, 10).addEnchantment(Enchantment.MULTISHOT, 5).addEnchantment(Enchantment.QUICK_CHARGE, 5).addEnchantment(Enchantment.PIERCING, 5).build());
		inventory.setItem(2, new ItemBuilder(Material.SHEARS).setName(Main.PREFIX+"SHEARS").setLore("The very sharpest Shears ever grinded.", "Watch out don't to touch them!").setAmount(1).setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.ARROW_INFINITE, 10).addEnchantment(Enchantment.DAMAGE_ALL,10).addEnchantment(Enchantment.SILK_TOUCH, 5).addEnchantment(Enchantment.SOUL_SPEED,10).addEnchantment(Enchantment.DIG_SPEED, 10).build());
		inventory.setItem(7, new ItemBuilder(Material.COOKIE).setAmount(1).setName(Main.PREFIX+"ULTIMATE COOKIE").setLore("Defenetly the best thing you've every eaten.", "TRY IT!!").addEnchantment(Enchantment.ARROW_INFINITE, 10).addEnchantment(Enchantment.ARROW_DAMAGE, 10).addEnchantment(Enchantment.LUCK, 10).addEnchantment(Enchantment.DURABILITY, 10).build());
		inventory.setItem(8, new ItemBuilder(Material.ARROW).setAmount(1).setName(Main.PREFIX+"ARROW").setLore("The arrow of real infinity!", "Let your enemys die into a storm of arrows.").addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.ARROW_INFINITE, 10).addEnchantment(Enchantment.ARROW_FIRE, 5).addEnchantment(Enchantment.LOYALTY, 10).build());
		if(plugin.game.getCtfPlayer(player).getTeamColor().equals(TeamColor.BLUE)) {		
			inventory.setItem(3, new ItemBuilder(Material.BLUE_WOOL).setAmount(64).setName(Main.PREFIX+"WOOL").setLore("Show your enemys which color you're!").build());
			player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setName(Main.PREFIX+"BOOTS").setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).build());
			player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setName(Main.PREFIX+"LEGGINGS").setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).build());
			player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setName(Main.PREFIX+"CHESTPLATE").setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).build());
			player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setName(Main.PREFIX+"HELMET").setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).build());
		} else {
			inventory.setItem(3, new ItemBuilder(Material.RED_WOOL).setAmount(64).setName(Main.PREFIX+"WOOL").setLore("Show your enemys which color you're!").build());
			player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setName(Main.PREFIX+"BOOTS").setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).build());
			player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setName(Main.PREFIX+"LEGGINGS").setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).build());
			player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setName(Main.PREFIX+"CHESTPLATE").setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).build());
			player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setName(Main.PREFIX+"HELMET").setBreakable(false).addEnchantment(Enchantment.DURABILITY, 10).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10).build());
		}
	}
}
