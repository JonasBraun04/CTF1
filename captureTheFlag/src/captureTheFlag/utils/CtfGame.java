package captureTheFlag.utils;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CtfGame {
	
	private ArrayList<CtfPlayer> players = new ArrayList<CtfPlayer>();
	
	private ArrayList<FlagPoint> flagPoints = new ArrayList<FlagPoint>();
	
	private Location spawnPointBlue;
	private Location spawnPointRed;
	private Location spawn;
	
	public CtfGame() {
		
	}
	
	
	
	public void addPlayer(CtfPlayer player) {
		players.add(player);
	}
	
	public void clearPlayer() {
		players.clear();
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
	
	public ArrayList<CtfPlayer> getCtfPlayers() {
		return(players);
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
	
	public void setSpawnPoint(TeamColor color, Location location) {
		switch(color) {
		case BLUE:
			spawnPointBlue = location;
		case RED:
			spawnPointRed = location;
		}
	}
	
	public Location getSpawnPoint(TeamColor color) {
		switch(color) {
		case BLUE:
			return(spawnPointBlue);
		case RED:
			return(spawnPointRed);
		default:
			return(null);
		}
	}
	
	public void setSpawn(Location location) {
		spawn = location;
	}
	
	public Location getSpawn() {
		return(spawn);
	}
}
