package captureTheFlag.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class CtfPlayer {
	private Player player;
	private TeamColor teamColor;
	private boolean flag;
	
	private HashMap<FlagPoint, Boolean> atFlagPoint = new HashMap<FlagPoint, Boolean>();
	
	public CtfPlayer(Player player, TeamColor teamColor) {
		this.player = player;
		this.teamColor = teamColor;
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
}
