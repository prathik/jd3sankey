package rocks.thiscoder.jd3sankey;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.*;

public class SankeyHandler {
    private Map<String, Node> nodeMap = new HashMap<>();
    private List<NodeToNodeLink> nodeToNodeLinks = new ArrayList<>();
    private int nextNodeId = 0;

    private void addNode(String name) {
        nodeMap.put(name, new Node(name, "Not supported", nextNodeId));
        nextNodeId++;
    }

    private Node getNode(String name) {
        return nodeMap.get(name);
    }

    private void addConnection(String sourceNode, String targetNode, Double value) {
        if(getNode(sourceNode) == null || getNode(targetNode) == null) {
            throw new IllegalArgumentException("Invalid node");
        }

        nodeToNodeLinks.add(new NodeToNodeLink(getNode(sourceNode),
                getNode(targetNode),
                value));
    }

    private String writeJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray nodesList = new JsonArray();

        List<Node> jNodesList = new ArrayList<>(nodeMap.values().size());
        jNodesList.addAll(nodeMap.values());

        Collections.sort(jNodesList);

        Gson gson = new Gson();

        for(Node node: jNodesList) {
            JsonObject jsonNode = new JsonObject();
            jsonNode.addProperty("index", node.getIndex());
            jsonNode.addProperty("name", node.getId());
            nodesList.add(jsonNode);
        }
        jsonObject.add("nodes", nodesList);
        JsonArray links = new JsonArray();
        for(NodeToNodeLink node: nodeToNodeLinks) {
            JsonObject jsonNode = new JsonObject();
            jsonNode.addProperty("source", node.getSource().getIndex());
            jsonNode.addProperty("target", node.getTarget().getIndex());
            jsonNode.addProperty("value", node.getValue());
            links.add(jsonNode);
        }
        jsonObject.add("links", links);
        return jsonObject.toString();
    }

    private void setNodeMap(Map<String, Node> nodeMap) {
        this.nodeMap = nodeMap;
    }

    private void setNodeToNodeLinks(List<NodeToNodeLink> nodeToNodeLinks) {
        this.nodeToNodeLinks = nodeToNodeLinks;
    }

    private static void commands() {
        System.out.println("Commands");
        System.out.println("add <node-name> | example: add income");
        System.out.println("link <source-node> <target-node> <value> | example link income savings 100000");
        System.out.println("write | writes the data file and quit");
        System.out.println("read | reads from the local energy.json");
        System.out.println("print | prints current system state");
        System.out.println("help | lists the commands");
        System.out.println("quit | plain quit");
    }

    public static void main(String[] args) {
        SankeyHandler handler = new SankeyHandler();
        Boolean run = true;
        commands();
        while(run) {
            Scanner reader = new Scanner(System.in);

            System.out.println("Enter a command and press enter");
            String command = reader.next();
            if(command.equals("quit")) {
                run = false;
            } else if(isAddNodeCommand(command)) {
                String nodeName = reader.next();
                handler.addNode(nodeName);
            } else if(isAddLinkCommand(command)) {
                String source = reader.next();
                String dest = reader.next();
                String value = reader.next();
                try {
                    handler.addConnection(source, dest, Double.valueOf(value));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if(isWriteCommand(command)) {
                handler.writeJson();
                IndexWriter indexWriter = new IndexWriter();
                try {
                    indexWriter.writeIndex();
                    indexWriter.writeJson(handler.writeJson());
                } catch (Exception e) {
                    System.out.println("Unable to write file.");
                }
            } else if(isReadCommand(command)) {
                try {
                    SankeyReader sankeyReader = new SankeyReader("energy.json");
                    handler.setNodeMap(sankeyReader.getNodeMap());
                    handler.setNodeToNodeLinks(sankeyReader.getLinks());
                } catch (IOException e) {
                    System.out.println("Unable to read file.");
                }
            }else if(isPrintCommand(command)) {
                System.out.println(handler.writeJson());
            } else if(isHelpCommand(command)) {
                commands();
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private static boolean isReadCommand(String command) {
        return command.equals("read");
    }

    private static boolean isPrintCommand(String command) {
        return command.equals("print");
    }

    private static boolean isAddNodeCommand(String command) {
        return command.contains("add");
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
