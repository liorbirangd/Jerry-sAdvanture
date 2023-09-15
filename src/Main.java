import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Scanner scanner;
    private static String playerName;
    private static int playerBirthYear;
    private static ArrayList<AdvantureNode> nodes;

    public static void main(String[] args) throws Exception {
        scanner = new Scanner(System.in);
        printTitle();
        readInitInput();
        initNodes();
        initTransitions();
        startAdvanture();
    }

    private static AdvantureNode current;

    // The main game loop
    private static void startAdvanture() {
        current = nodes.get(0);
        InputCode inputCode = null;
        // Run through all nodes until reacing a null node
        // Print node text, and wait for player's input
        while (current != null) {
            System.out.print(current.getText());
            inputCode = getInput();
            while (inputCode == InputCode.Invalid) {
                System.out.println(getInputString(InputCode.Invalid));
                inputCode = getInput();
            }
            System.out.println(getInputString(inputCode));
            takeAction(inputCode);
        }
    }

    // Recieve input from the player and check for validity
    private static InputCode getInput() {
        String input = scanner.nextLine();
        InputCode actionCode = InputCode.Invalid;
        switch (input.toLowerCase()) {
            case "open the door":
            case "open door":
                actionCode = InputCode.OpenDoor;
                break;
            case "go north":
            case "north":
                actionCode = InputCode.GoNorth;
                break;
            case "go east":
            case "east":
                actionCode = InputCode.GoEast;
                break;
            case "go west":
            case "west":
                actionCode = InputCode.GoWest;
                break;
            case "go south":
            case "south":
                actionCode = InputCode.GoSouth;
                break;
            case "take item":
                actionCode = InputCode.TakeItem;
                break;
            case "drop item":
                actionCode = InputCode.DropItem;
                break;
            case "use item":
            case "use":
                actionCode = InputCode.UseItem;
                break;
        }
        return actionCode;
    }

    // Convert input code to a string to print for the player
    private static String getInputString(InputCode actionCode) {
        switch (actionCode) {
            case DropItem:
                return ">>Drop item";
            case GoEast:
                return ">>Go east";
            case GoNorth:
                return ">>Go north";
            case GoSouth:
                return ">>Got south";
            case GoWest:
                return ">>Go west";
            case Invalid:
                return "The input is invalid, \npleas try again:";
            case OpenDoor:
                return ">>Open the door";
            case TakeItem:
                return ">>Take item";
            case UseItem:
                return ">>Use item";
        }
        return null;
    }

    // After the player performs an action, transition to the next state
    private static void takeAction(InputCode actionCode) {
        NextNode next = current.tryTransition(actionCode);
        current = next.node;
        System.out.println(next.message);
    }

    // #region GmaeStart
    // #endregion
    // Print the ASCII art fot the game title
    private static void printTitle() {
        System.out.println("\r\n" +
                "        _               " +
                "       /_/____________ _( )_____ / | ____/ / _____ ____ / /___ __________\r\n" + //
                "  __  / / _ \\/ ___/ ___/ / / /// ___/ / /| |/ __ / | / / _ \\/ __ \\/ __/ / / / ___/ _ \\\r\n" + //
                " / /_/ / __/ / / / / /_/ / (__ ) / ___ / /_/ /| |/ / __/ / / / /_/ /_/ / / / __/\r\n" + //
                "\\____/\\___/_/ /_/ \\__, / /____/ /_/ |_\\__,_/ |___/\\___/_/ /_/\\__/\\__,_/_/ \\___/\r\n" + //
                "\n" +
                "by: Lior Biran");
    }

    // Read name and birth year from the player
    private static void readInitInput() {
        System.out.print("What is your name?:  ");
        playerName = scanner.nextLine();
        System.out.println("Hi " + playerName + "!");
        System.out.print("On what year were you born?:  ");
        playerBirthYear = scanner.nextInt();
        System.out.println("You are " + calculateAge(playerBirthYear) + " years old");
        scanner.nextLine();
    }

    // calculate and return player's age acording to a birth year
    private static int calculateAge(int birthYear) {
        LocalDateTime now = LocalDateTime.now();
        int age = now.getYear() - playerBirthYear;
        return age;
    }

    // Create the nodes with text
    private static void initNodes() {
        nodes = new ArrayList<AdvantureNode>();
        AdvantureNode node = new AdvantureNode(
                "\nYou are standing in an abandoned university office. There are neither students\n" +
                        "nor teachers around you. There's a table in front of you with various papers,\n" +
                        "pens, a small puzzle toy, and a calculator.\n" +
                        "A large window shows an empty office building; there are no Zombies in the empty\n" +
                        "building (as far as you can tell). Behind you is a dark and mysterious door that\n" +
                        "leads to a well-lit corridor with a fireproof ceiling and floor. You feel a\n" +
                        "sense of Wi-Fi around you, the grinding of an LCD operated coffee machine can be\n" +
                        "heard in the distance. You are not thirsty, but you rather have a craving for\n" +
                        "justice.\n" +
                        "What would you like to do?: ",
                0);
        nodes.add(node);
        node = new AdvantureNode(
                "\nThis is the second Node" +
                        "\nWhat would you like to do?: ",
                1);
        nodes.add(node);
    }

    private static void initTransitions() {
        Transition t = new Transition(getNodeById(1), InputCode.OpenDoor);
        getNodeById(0).initTransitions(Arrays.asList(t));
    }

    private static AdvantureNode getNodeById(int id) {
        Optional<AdvantureNode> optional = nodes.stream().filter(n -> n.getStateId() == id).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}