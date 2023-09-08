import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

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
        startAdvanture();
    }
    
    private static void startAdvanture() {
        AdvantureNode current=nodes.get(0);
        String input;
        //Run through all nodes until reacing a null node
        //Print node text, and wait for player's input
        while(current!=null){
            System.out.print(current.text);
            scanner.nextLine();
            input=scanner.nextLine();
            System.out.println(input);
            AdvantureNode n = current.options.get(0);
            current=n;
        }
    }

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
    //Read name and birth year from the player
    private static void readInitInput() {
        System.out.print("What is your name?:  ");
        playerName = scanner.nextLine();
        System.out.println("Hi " + playerName + "!");
        System.out.print("On what year were you born?:  ");
        playerBirthYear = scanner.nextInt();
        System.out.println("You are " + calculateAge(playerBirthYear) + " years old");
    }
    //calculate and return player's age acording to a birth year
    private static int calculateAge(int birthYear) {
        LocalDateTime now = LocalDateTime.now();
        int age = now.getYear() - playerBirthYear;
        return age;
    }
    //Create the nodes with text
    private static void initNodes(){
        nodes=new ArrayList<AdvantureNode>();
        AdvantureNode node=new AdvantureNode("\nYou are standing in an abandoned university office. There are neither students\n"+
        "nor teachers around you. There's a table in front of you with various papers,\n"+
        "pens, a small puzzle toy, and a calculator.\n"+
        "A large window shows an empty office building; there are no Zombies in the empty\n"+
        "building (as far as you can tell). Behind you is a dark and mysterious door that\n"+
        "leads to a well-lit corridor with a fireproof ceiling and floor. You feel a\n"+
        "sense of Wi-Fi around you, the grinding of an LCD operated coffee machine can be\n"+
        "heard in the distance. You are not thirsty, but you rather have a craving for\n"+
        "justice.\n"+
        "What would you like to do?: ",null);
        nodes.add(node);
    }
}