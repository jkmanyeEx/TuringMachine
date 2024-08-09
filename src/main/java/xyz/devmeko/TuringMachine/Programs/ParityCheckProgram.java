package xyz.devmeko.TuringMachine.Programs;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xyz.devmeko.TuringMachine.Machine.Action;
import xyz.devmeko.TuringMachine.Machine.Machine;
import xyz.devmeko.TuringMachine.Machine.Program;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ParityCheckProgram implements Program {

    Integer startPos = 1;
    Integer memoryAlloc = 16;
    Machine machine = null;

    @Override
    public String output(String input) throws Exception {
        List<Action> actionList = new ArrayList<>();

        String filePath = "./src/main/java/xyz/devmeko/TuringMachine/Programs/ParityCheck.jff";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filePath);

        Node root = document.getDocumentElement();

        NodeList automatonNodes = root.getChildNodes();
        for (int i = 0; i < automatonNodes.getLength(); i++) {
            Node automatonNode = automatonNodes.item(i);

            if (automatonNode.getNodeName().equals("automaton")) {
                NodeList automatonChildren = automatonNode.getChildNodes();
                for (int j = 0; j < automatonChildren.getLength(); j++) {
                    Node automationChild = automatonChildren.item(j);

                    if (automationChild.getNodeName().equals("transition")) {
                        String firstState = null;
                        Character findChar = null;
                        Character replace = null;
                        Integer moveDirection = null;
                        String nextState = null;

                        NodeList transitionChildren = automationChild.getChildNodes();
                        for (int k = 0; k < transitionChildren.getLength(); k++) {
                            Node transitionChild = transitionChildren.item(k);
                            if (transitionChild.getNodeType() == Node.ELEMENT_NODE) {
                                switch (transitionChild.getNodeName()) {
                                    case "from":
                                        firstState = "q" + transitionChild.getTextContent();
                                    case "to":
                                        nextState = "q" + transitionChild.getTextContent();
                                    case "read":
                                        if (transitionChild.getTextContent().equals("")) findChar = ' ';
                                        else findChar = transitionChild.getTextContent().charAt(0);
                                    case "write":
                                        if (transitionChild.getTextContent().equals("")) replace = ' ';
                                        else replace = transitionChild.getTextContent().charAt(0);
                                    case "move":
                                        if (transitionChild.getTextContent().equals("L")) moveDirection = -1;
                                        else if (transitionChild.getTextContent().equals("R")) moveDirection = 1;
                                        else if (transitionChild.getTextContent().equals("S")) moveDirection = 0;
                                }
                            }
                        }

                        actionList.add(new Action(firstState, findChar, replace, moveDirection, nextState));
                    }
                }
            }
        }

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
