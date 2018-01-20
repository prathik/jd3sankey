package rocks.thiscoder.jd3sankey;

public class Node implements Comparable {
    private final String id;
    private final String description;
    private final int index;

    Node(String id, String description, int index) {
        this.id = id;
        this.description = description;
        this.index = index;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }


    @Override
    public int compareTo(Object o) {
        Node node = (Node) o;
        if(this.getIndex() < node.getIndex()) {
            return -1;
        } else if(this.getIndex() > node.getIndex()) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", index=" + index +
                '}';
    }
}
