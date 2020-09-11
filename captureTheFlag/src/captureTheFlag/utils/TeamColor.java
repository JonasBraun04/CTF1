package captureTheFlag.utils;
	

public enum TeamColor {
	BLUE("§9Blau§r"),
	RED("§cRot§r");
	
	private String color;
	
	private TeamColor(String color) {
		this.color = color;
	}
	
	public String getColorCode() {
		return(color);
	}
}
