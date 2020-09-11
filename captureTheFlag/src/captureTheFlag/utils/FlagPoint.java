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
}
