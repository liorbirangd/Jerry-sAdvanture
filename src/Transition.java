public class Transition {
    private AdvantureNode nextNode;

    private InputCode requiredAction;

    public Transition(AdvantureNode node, InputCode actionCode) {
        nextNode = node;
        requiredAction = actionCode;
    }

    public Transition(Transition t) {
        nextNode = t.nextNode;
        requiredAction = t.requiredAction;
    }

    public boolean isTransitionPossible(InputCode actionCode) {
        return requiredAction == actionCode;
    }

    public InputCode getRequiredAction() {
        return requiredAction;
    }

    public AdvantureNode getNextNode() {
        return nextNode;
    }
}
