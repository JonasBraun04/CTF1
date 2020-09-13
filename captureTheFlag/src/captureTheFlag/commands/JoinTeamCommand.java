package captureTheFlag.commands;

import org.bukkit.entity.Player;

import captureTheFlag.main.Main;
import captureTheFlag.utils.CtfPlayer;
import captureTheFlag.utils.TeamColor;

public class JoinTeamCommand {
	
	private Main plugin;
	
	public JoinTeamCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public void executeJoinTeamCommand(Player player, TeamColor color) {
		if(!plugin.game.searchForPlayer(player)) {
			plugin.game.addPlayer(new CtfPlayer(plugin, player, color));
			player.sendMessage(Main.PREFIX + "Du bist erfolgreich Team "+color.getColorCode()+" beigetreten.");
		} else {
			player.sendMessage(Main.PREFIX + "Du bist bereits Team "+plugin.game.getCtfPlayer(player).getTeamColor().getColorCode()+" beigetreten.");
		}
	}
}
