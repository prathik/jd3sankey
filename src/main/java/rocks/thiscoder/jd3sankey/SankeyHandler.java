package rocks.thiscoder.jd3sankey;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class SankeyHandler {
    private final Map<String, Node> nodeMap = new HashMap<>();
    private final List<NodeToNodeLink> nodeToNodeLinks = new ArrayList<>();
    private int nextNodeId = 0;

    void addNode(String name) {
        nodeMap.put(name, new Node(name, "Not supported", nextNodeId));
        nextNodeId++;
    }

    Node getNode(String name) {
        return nodeMap.get(name);
    }

    void addConnection(String sourceNode, String targetNode, Double value) {
        if(getNode(sourceNode) == null || getNode(targetNode) == null) {
            throw new IllegalArgumentException("Invalid node");
        }

        nodeToNodeLinks.add(new NodeToNodeLink(getNode(sourceNode),
                getNode(targetNode),
                value));
    }

    void addConnection(String sourceNode, String targetNode, int value) {
        addConnection(sourceNode, targetNode, Double.valueOf(value));
    }

    String writeJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray nodesList = new JsonArray();

        List<Node> jNodesList = new ArrayList<>(nodeMap.values().size());
        jNodesList.addAll(nodeMap.values());

        Collections.sort(jNodesList);

        for(Node node: jNodesList) {
            JsonObject jsonNode = new JsonObject();
            jsonNode.addProperty("id", node.getIndex());
            jsonNode.addProperty("name", node.getId());
            nodesList.add(jsonNode);
        }
        jsonObject.add("nodes", nodesList);
        JsonArray links = new JsonArray();
        for(NodeToNodeLink node: nodeToNodeLinks) {
            JsonObject jsonNode = new JsonObject();
            jsonNode.addProperty("source", node.getSourceNode().getIndex());
            jsonNode.addProperty("target", node.getDestinationNode().getIndex());
            jsonNode.addProperty("value", node.getValue());
            links.add(jsonNode);
        }
        jsonObject.add("links", links);
        return jsonObject.toString();
    }

    static boolean isAddNodeCommand(String command) {
        return command.contains("add");
    }

    static void commands() {
        System.out.println("Commands");
        System.out.println("add <node-name> | example: add income");
        System.out.println("link <source-node> <target-node> <value> | example link income savings 100000");
        System.out.println("write | writes the data file and quit");
        System.out.println("help | lists the commands");
        System.out.println("q | plain quit");
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        SankeyHandler handler = new SankeyHandler();
        Boolean run = true;
        commands();
        while(run) {
            Scanner reader = new Scanner(System.in);

            System.out.println("Enter a command and press enter");
            String command = reader.next();
            if(command.equals("q")) {
                run = false;
            } else if(isAddNodeCommand(command)) {
                String nodeName = reader.next();
                handler.addNode(nodeName);
            } else if(isAddLinkCommand(command)) {
                String source = reader.next();
                String dest = reader.next();
                String value = reader.next();
                handler.addConnection(source, dest, Double.valueOf(value));
            } else if(isWriteCommand(command)) {
                handler.writeJson();
                IndexWriter indexWriter = new IndexWriter();
                indexWriter.writeIndex();
                indexWriter.writeJson(handler.writeJson());
            } else if(isHelpCommand(command)) {
                commands();
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private static boolean isHelpCommand(String command) {
        return command.equals("help");
    }

    private static boolean isWriteCommand(String command) {
        return command.equals("write");
    }

    private static boolean isAddLinkCommand(String command) {
        return command.contains("link");
    }
}
