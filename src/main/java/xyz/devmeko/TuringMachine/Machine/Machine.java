package xyz.devmeko.TuringMachine.Machine;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Machine {

    private Character[] tape;
    private Integer headPos;
    private String state = "q0";
    private List<Action> actionList;

    public Machine(Integer startPos, List<Action> actionList, String input, Integer memoryAlloc) throws Exception {
        this.headPos = startPos;
        this.actionList = actionList;

        tape = new Character[memoryAlloc];

        if (memoryAlloc < startPos + input.length()) {
            throw new Exception("Memory allocation size is too small!");
        }

        Integer inputIndex;

        for (inputIndex = 0; inputIndex < startPos; inputIndex++) {
            tape[inputIndex] = ' ';
        }

        for(Character c : input.toCharArray()) {
            tape[inputIndex] = c;
            inputIndex++;
        }

        for (; inputIndex < memoryAlloc; inputIndex++) {
            tape[inputIndex] = ' ';
        }
    }

    public String run() throws Exception {
        while (! state.equalsIgnoreCase("qf")) {
            Action curAction = actionList.stream().
                    filter(action -> action.getFirstState().equalsIgnoreCase(state) && action.getFindChar().equals(tape[headPos]))
                    .findFirst()
                    .orElseThrow(() -> new Exception("No such action found for: (" + state + ", " + tape[headPos] + ") !"));

            System.out.print(curAction.toString() + ": ");

            tape[headPos] = curAction.getReplace();
            headPos += curAction.getMoveDirection();
            state = curAction.getNextState();

            System.out.println(tapeToStrRuntime());
        }

        return tapeToStr();
    }

    private String tapeToStrRuntime() {
        StringBuilder output = new StringBuilder();

        Integer index = 0;

        for (Character c : tape) {
            if (index.equals(headPos)) {
                output.append("(");
                output.append(c);
                output.append(")");
            } else output.append(c);

            index++;
        }

        return output.toString().replace(" ", "^");
    }

    private String tapeToStr() {
        StringBuilder output = new StringBuilder();

        for (Character c : tape) {
            output.append(c);
        }

        return output.toString().replace(" ", "");
    }
}
