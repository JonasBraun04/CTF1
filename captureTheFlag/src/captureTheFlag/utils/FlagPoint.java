package captureTheFlag.utils;

import java.util.UUID;

import org.bukkit.Location;

public class FlagPoint {
	
	private TeamColor team;
	private int index;
	private Location location;
	private UUID flag;
	
	public FlagPoint(TeamColor team, Location location) {
		this.team = team;
		this.location = location;
	}
	
	public Location getLocation() {
		return(location);
	}
	
	public TeamColor getTeamColor() {
		return(team);
	}
	
	public void setUUID(UUID uuid) {
		flag = uuid;
	}
	
	public UUID getUUID() {
		return(flag);
	}
}
