package rocks.thiscoder.jd3sankey;

public class NodeToNodeLink {
    private final Node sourceNode;
    private final Node destinationNode;
    private final Double value;

    NodeToNodeLink(Node sourceNode, Node destinationNode, Double value) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.value = value;
    }

    Double getValue() {
        return value;
    }

    Node getDestinationNode() {
        return destinationNode;
    }

    Node getSourceNode() {
        return sourceNode;
    }
}
