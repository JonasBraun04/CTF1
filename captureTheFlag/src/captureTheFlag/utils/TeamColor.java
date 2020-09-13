package captureTheFlag.utils;
	

public enum TeamColor {
	BLUE("§9Blau§r", "blue", "§9"),
	RED("§cRot§r", "red", "§c");
	
	private String displayColor;
	private String debugColor;
	private String colorCode;
	
	private TeamColor(String displayColor, String debugColor, String colorCode) {
		this.displayColor = displayColor;
		this.debugColor = debugColor;
		this.colorCode = colorCode;
	}
	
	public String getDisplayColor() {
		return(displayColor);
	}
	
	public String getDebugColor() {
		return(debugColor);
	}
	
	
	public String getColorCode() {
		return(colorCode);
	}
}
