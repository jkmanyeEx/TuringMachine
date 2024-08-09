package xyz.devmeko.TuringMachine.Programs;

import xyz.devmeko.TuringMachine.Machine.Action;
import xyz.devmeko.TuringMachine.Machine.Machine;
import xyz.devmeko.TuringMachine.Machine.Program;

import java.util.List;

public class ParityCheckProgram_OLD implements Program {

    Integer startPos = 1;
    Integer memoryAlloc = 16;
    Machine machine = null;

    @Override
    public String output(String input) throws Exception {
        List<Action> actionList = List.of(
                new Action("q0", '0', '0', 1, "q0"),
                new Action("q0", '1', '1', 1, "q0"),
                new Action("q0", ' ', ' ', -1, "q1"),
                new Action("q1", '0', 'e', 1, "q2"),
                new Action("q1", '1', 'e', 1, "q3"),
                new Action("q2", ' ', '0', -1, "q4"),
                new Action("q3", ' ', '1', -1, "q4"),
                new Action("q4", '0', '0', -1, "q4"),
                new Action("q4", '1', '1', -1, "q4"),
                new Action("q4", 'e', 'e', -1, "q4"),
                new Action("q4", ' ', ' ', 1, "q5"),
                new Action("q5", '0', '0', 1, "q5"),
                new Action("q5", '1', 'r', 1, "q6"),
                new Action("q5", 'e', 'e', 1, "qf"),
                new Action("q6", '0', '0', 1, "q6"),
                new Action("q6", '1', '1', 1, "q6"),
                new Action("q6", 'e', 'e', 1, "q7"),
                new Action("q7", '1', '0', -1, "q8"),
                new Action("q7", '0', '1', -1, "q8"),
                new Action("q8", '0', '0', -1, "q8"),
                new Action("q8", '1', '1', -1, "q8"),
                new Action("q8", 'e', 'e', -1, "q8"),
                new Action("q8", 'r', 'r', 1, "q5")
            );

        machine = new Machine(startPos,
                actionList,
                input,
                memoryAlloc);

        String result = machine.run();
        int i = Integer.parseInt(result.substring(result.length() - 1));

        if (i == 1) return "Invalid!";
        else if (i == 0) return "Valid!";
        else return "Error!";
    }
}
