package xyz.devmeko.TuringMachine.Machine;

public class Action {

    private String firstState;
    private Character findChar;
    private Character replace;
    private Integer moveDirection;
    private String nextState;

    public Action(String firstState, Character findChar, Character replace, Integer moveDirection, String nextState) {
        this.firstState = firstState;
        this.findChar = findChar;
        this.replace = replace;
        this.moveDirection = moveDirection;
        this.nextState = nextState;
    }

    public String getFirstState() {
        return firstState;
    }

    public Character getFindChar() {
        return findChar;
    }

    public Character getReplace() {
        return replace;
    }

    public Integer getMoveDirection() {
        return moveDirection;
    }

    public String getNextState() {
        return nextState;
    }

    @Override
    public String toString() {
        return "Action(" +
                firstState +
                ", " + findChar +
                ", " + replace +
                ", " + moveDirection +
                ", " + nextState +
                ')';
    }
}
