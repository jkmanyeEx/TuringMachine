package xyz.devmeko.TuringMachine;

import xyz.devmeko.TuringMachine.Programs.BinaryAdditionProgram;
import xyz.devmeko.TuringMachine.Programs.ParityCheckProgram;

public class Main {

    public static void main(String[] args) throws Exception {
//        ParityCheckProgram parityCheckProgram = new ParityCheckProgram();
//        String input = "0101101010100";
//
//        System.out.println(parityCheckProgram.output(input));

        BinaryAdditionProgram binaryAdditionProgram = new BinaryAdditionProgram();
        String input = "1010111p100011";
        System.out.println(binaryAdditionProgram.output(input));
    }
}
