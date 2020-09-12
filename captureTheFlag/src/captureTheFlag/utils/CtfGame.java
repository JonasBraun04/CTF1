package captureTheFlag.utils;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CtfGame {
	
	private ArrayList<CtfPlayer> players = new ArrayList<CtfPlayer>();
	private ArrayList<CtfPlayer> teamBlue = new ArrayList<CtfPlayer>();
	private ArrayList<CtfPlayer> teamRed = new ArrayList<CtfPlayer>();
	
	private ArrayList<FlagPoint> flagPoints = new ArrayList<FlagPoint>();
	
	public CtfGame() {
		
	}
	
	public void addPlayer(CtfPlayer player) {
		players.add(player);
	}
	
	public boolean searchForPlayer(Player player) {
		for(int i=0; i<players.size(); i++) {
			if(player == players.get(i).getPlayer()) {
				return(true);
			}
		}
		return(false);
	}
	
	public CtfPlayer getCtfPlayer(Player player) {
		for(int i=0; i<players.size(); i++) {
			if(player == players.get(i).getPlayer()) {
				return(players.get(i));
			}
		}
		return(null);
	}
	
	public ArrayList<FlagPoint> getFlagPoints() {
		return(flagPoints);
	}
	
	public void addFlagPoint(FlagPoint flagPoint) {
		flagPoints.add(flagPoint);
	}
	
	public void removeFlagPoint(FlagPoint flagPoint) {
		flagPoints.remove(flagPoint);
	}
	
	public void setUUID(int index, UUID uuid) {
		flagPoints.get(index).setUUID(uuid);
	}
	
	public void clearUUID(int index) {
		flagPoints.get(index).setUUID(null);
	}
}
