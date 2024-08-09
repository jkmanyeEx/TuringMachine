package xyz.devmeko.TuringMachine.Machine;

public interface Program {

    Integer startPos = null;
    Integer memoryAlloc = null;
    Machine machine = null;

    String output(String input) throws Exception;
}
