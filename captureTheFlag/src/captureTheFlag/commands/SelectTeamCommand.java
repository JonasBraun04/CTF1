package captureTheFlag.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import captureTheFlag.main.Main;
import captureTheFlag.utils.ItemBuilder;
import captureTheFlag.utils.TeamColor;

public class SelectTeamCommand {
	private static Inventory selectTeamInventory = Bukkit.createInventory(null, 1*9, Main.PREFIX+"select Team");
	
	static {
		selectTeamInventory.setItem(0*9+4, new ItemBuilder(Material.BEACON).setName(Main.PREFIX+"select Team").setLore("Wähle Team blau oder Team rot aus.").build());
		selectTeamInventory.setItem(0*9+2, new ItemBuilder(Material.BLUE_WOOL).setName(TeamColor.BLUE.getColorCode()+"Team blue").setLore("Klicke um Team blau beizutreten.").build());
		selectTeamInventory.setItem(0*9+6, new ItemBuilder(Material.RED_WOOL).setName(TeamColor.RED.getColorCode()+"Team red").setLore("Klicke um Team rot beizutreten.").build());
	}
	
	public SelectTeamCommand() {
	}
	
	public void executeSelectTeamCommand(Player player) {
		player.openInventory(selectTeamInventory);
	}
}
