package model;

public enum GameState {
    SETUP("Setup"),
    SELECT_FIELD("Select Field"),
    SELECT_UNIT("Select Unit"),
    SELECT_ACTION("Select Action"),
    MOVE_SELECT_TARGETFIELD("Select Target Field"),
    ATTACK_SELECT_TARGETFIELD("Select Target Field"),
    SELECT_TARGETUNIT("Select Target Unit"),
    EXECUTION("Ready to Execute Command"),
    ERR_NOT_ENOUGH_RESOURCES("Not enough resources!"),
    ERR_TARGET_IS_TOO_FAR("Target is unreachable!"),
    ERR_CANNOT_BUILD("Worker cannot build!"),
    OVER("Game Over");
    private final String message;

    GameState(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}
