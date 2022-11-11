package model;

public enum GameState {
    SETUP("Setup"),
    SELECT_FIELD("Select Field"),
    SELECT_UNIT("Select Unit"),
    SELECT_ACTION("Select Action"),
    SELECT_TARGETFIELD("Select Target Field"),
    SELECT_TARGETUNIT("Select Target Unit"),
    EXECUTION("Ready to Execute Command"),
    OVER("Game Over");
    private final String display;
    GameState(String display) {
        this.display = display;
    }
    @Override
    public String toString(){ return this.display; }
    
}
