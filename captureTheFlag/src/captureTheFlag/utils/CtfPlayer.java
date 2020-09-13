package captureTheFlag.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import captureTheFlag.commands.GetKitCommand;
import captureTheFlag.main.Main;

public class CtfPlayer {
	private Player player;
	private TeamColor teamColor;
	private boolean flag;
	private Main plugin;
	
	private HashMap<FlagPoint, Boolean> atFlagPoint = new HashMap<FlagPoint, Boolean>();
	
	public CtfPlayer(Main plugin, Player player, TeamColor teamColor) {
		this.player = player;
		this.teamColor = teamColor;
		this.plugin = plugin;
	}
	
	public TeamColor getTeamColor() {
		return teamColor;
	}
	
	public void setTeamColor(TeamColor newTeamColor) {
		teamColor = newTeamColor;
	}
	
	public Player getPlayer() {
		return(player);
	}
	
	public boolean hasFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean isAtFlagPoint(FlagPoint flagPoint) {
		return(atFlagPoint.get(flagPoint));
	}
	
	public void setAtFlagPoint(FlagPoint flagPoint, boolean value) {
		atFlagPoint.put(flagPoint, value);
	}
	
	public void getKit() {
		GetKitCommand getKitCommand = new GetKitCommand(plugin);
		getKitCommand.executeGetKitCommand(player);
	}
	
}
