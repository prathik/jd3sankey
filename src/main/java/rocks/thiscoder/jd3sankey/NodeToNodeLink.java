package rocks.thiscoder.jd3sankey;

class NodeToNodeLink {
    private final Node source;
    private final Node target;
    private final Double value;

    NodeToNodeLink(Node sourceNode, Node destinationNode, Double value) {
        this.source = sourceNode;
        this.target = destinationNode;
        this.value = value;
    }

    Double getValue() {
        return value;
    }

    public Node getTarget() {
        return target;
    }

    public Node getSource() {
        return source;
    }
}
