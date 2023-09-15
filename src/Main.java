import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    private static Scanner scanner;
    private static String playerName;
    private static int playerBirthYear;
    private static ArrayList<AdvantureNode> nodes;

    private static boolean isQuit = false;

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
        while (!isQuit && current != null) {
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
            case "door":
            case "open":
            case"o":
                actionCode = InputCode.OpenDoor;
                break;
            case "go north":
            case "north":
            case"n":
                actionCode = InputCode.GoNorth;
                break;
            case "go east":
            case "east":
            case"e":
                actionCode = InputCode.GoEast;
                break;
            case "go west":
            case "west":
            case"w":
                actionCode = InputCode.GoWest;
                break;
            case "go south":
            case "south":
            case"s":
                actionCode = InputCode.GoSouth;
                break;
            case "take item":
            case "take":
            case"t":
                actionCode = InputCode.TakeItem;
                break;
            case "drop item":
            case "drop":
            case"d":
                actionCode = InputCode.DropItem;
                break;
            case "use item":
            case "use":
            case"u":
                actionCode = InputCode.UseItem;
                break;
            case "quit":
            case "exit":
            case "q":
                actionCode = InputCode.Quit;
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
            case Quit:
                return "";
        }
        return null;
    }

    // After the player performs an action, transition to the next state
    private static void takeAction(InputCode actionCode) {
        if (actionCode == InputCode.Quit) {
            quitGame();
            return;
        }
        NextNode next = current.tryTransition(actionCode);
        current = next.node;
        System.out.println(next.message);
    }

    private static void quitGame() {
        System.out.println("That is unfortunante, thank you for playing");
        isQuit = true;
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

    // Searches the node list for a node with the given id
    private static AdvantureNode getNodeById(int id) {
        Optional<AdvantureNode> optional = nodes.stream().filter(n -> n.getStateId() == id).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    // Create the nodes with text
    private static void initNodes() {
        nodes = new ArrayList<AdvantureNode>();
        AdvantureNode node = new AdvantureNode(
                "ou are standing in an abandoned university office. There are neither" +
                        "\nstudents nor teachers around you. There’s a table in front of you with" +
                        "\nvarious papers, pens, a small puzzle toy, and a calculator." +
                        "\nA large window shows an empty office building; there are no Zombies in the" +
                        "\nempty building (as far as you can tell). Behind you is a dark and mysterious" +
                        "\ndoor that leads to a well-lit corridor with a fireproof ceiling and floor. You feel" +
                        "\na sense of Wi-Fi around you, the grinding of an LCD operated coffee" +
                        "\nmachine can be heard in the distance. You are not thirsty, but you rather" +
                        "\nhave a craving for justice." +
                        "\nWhat would you like to do?: ",
                0);
        nodes.add(node);
        node = new AdvantureNode(
                "\nYou are in a long hallway. There’s a man wearing glasses at the end of it, he" +
                        "\nlooks harmless. West is a wall, east is the man, to the north is nothing but" +
                        "\nempty offices, a desperate sight. The carpeting in the hallway feels soft, you" +
                        "\nhear the clicking of a mouse in the distance. Your office is south (behind" +
                        "\nyou)." +
                        "\nWhat would you like to do?: ",
                1);
        nodes.add(node);
        node = new AdvantureNode(
                "\nYou are in a long hallway. There’s a man wearing glasses at the end of it, he" +
                        "\nlooks harmless. West is a wall, east is the man, to the north is nothing but" +
                        "\nempty offices, a desperate sight. The carpeting in the hallway feels soft, you" +
                        "\nhear the clicking of a mouse in the distance. Your office is south (behind" +
                        "\nyou)." +
                        "\nWhat would you like to do?: ",
                1);
        nodes.add(node);
        node = new AdvantureNode(
                "\nou take the calculator from your desk. It’s a Casio FX-85gt Plus. The" +
                        "\ndisplay shows the number 0.1134. You turn it upside down; now the Casio" +
                        "\ngreets you with a friendly “hello”, nice. You hold the calculator in your hand." +
                        "\nnWhat would you like to do?: ",
                2);
        nodes.add(node);
        node = new AdvantureNode(
                "\nThe man greets you and starts endlessly talking to you about his children" +
                        "\nand his holiday to Benidorm. You die of boredom." +
                        "\nnWhat would you like to do?: ",
                3);
        nodes.add(node);
    }

    // Create the transitions between the nodes
    private static void initTransitions() {
        ArrayList<Transition> n0 = new ArrayList<>();
        n0.add(new Transition(getNodeById(1), InputCode.OpenDoor));
        n0.add(new Transition(getNodeById(2), InputCode.TakeItem));
        n0.add(new Transition(getNodeById(3), InputCode.GoEast));
        getNodeById(0).initTransitions(n0);

    }

}