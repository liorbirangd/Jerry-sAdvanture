import java.util.ArrayList;
import java.util.List;

public class AdvantureNode {

    private int stateId;
    public int getStateId() {
        return stateId;
    }

    private String text;
    public String getText() {
        return text;
    }

    private ArrayList<Transition> transitions;

    public ArrayList<Transition> getTransitions() {
        return new ArrayList<Transition>(transitions);
    }
    // Default constructor for an AdvantureNode
    public AdvantureNode(String text, int id) {
        stateId = id;
        this.text = text;
        transitions = new ArrayList<>();
    }
    public void initTransitions(List<Transition> trans){
        for(Transition transition :trans){
            transitions.add(new Transition(transition));
        }
    }

    // Check if the action fits one of the transitions of this state, and return the
    // next state and a message.
    public NextNode tryTransition(InputCode actionCode) {
        for (Transition transition : transitions) {
            if (transition.isTransitionPossible(actionCode)) {
                return new NextNode(transition.getNextNode(), getMessage(actionCode));
            }
        }
        return new NextNode(this, getMessage(InputCode.Invalid));
    }

    // Convert action code to a printable message
    private String getMessage(InputCode actionCode) {
        switch (actionCode) {
            case DropItem:
                return "You drop item";
            case GoEast:
                return "You go east";
            case GoNorth:
                return "You go north";
            case GoSouth:
                return "You got south";
            case GoWest:
                return "You go west";
            case Invalid:
                return "Unfortunatelly you are unable to use this action here";
            case OpenDoor:
                return "You opent the door";
            case TakeItem:
                return "You take item";
            case UseItem:
                return "You use item";
        }
        return null;
    }
}
