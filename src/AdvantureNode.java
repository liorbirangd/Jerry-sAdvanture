import java.util.ArrayList;

public class AdvantureNode {
    public String text;
    public ArrayList<AdvantureNode> options;

    //Default constructor for an AdvantureNode
    public AdvantureNode(String text, ArrayList<AdvantureNode> nextNodes) {
        this.text = text;
        options = new ArrayList<>();
        //if recieve no null
        if (nextNodes != null)
            for (AdvantureNode advantureNode : nextNodes) {
                options.add(advantureNode);
            }
        if (options.isEmpty())
            options.add(null);
        System.out.println("Options: "+ options.size());
    }
}
