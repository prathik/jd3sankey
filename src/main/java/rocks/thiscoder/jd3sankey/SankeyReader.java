package rocks.thiscoder.jd3sankey;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Allows users to read an existing sankey file.
 */
class SankeyReader {
    private final JsonObject rootJsonObj;
    private final List<Node> nodes = new ArrayList<>();
    private final List<NodeToNodeLink> links = new ArrayList<>();

    SankeyReader(String fileName) throws IOException {
        FileInputStream fisTargetFile = new FileInputStream(new File(fileName));
        String fileAsString = IOUtils.toString(fisTargetFile, "UTF-8");
        JsonParser jsonParser = new JsonParser();
        rootJsonObj = (JsonObject) jsonParser.parse(fileAsString);
        parseNodes();
        parseLinks();
    }

    private void parseNodes() {
        JsonArray jsonArray = rootJsonObj.getAsJsonArray("nodes");
        for (Object aJsonArray : jsonArray) {
            JsonObject node = (JsonObject) aJsonArray;
            nodes.add(new Node(node.get("name").getAsString(),
                    "Not supported",
                    Integer.valueOf(node.get("index").getAsString())));
        }
        Collections.sort(nodes);
    }

    private void parseLinks() {
        JsonArray jsonArray = rootJsonObj.getAsJsonArray("links");
        for (Object aJsonArray : jsonArray) {
            JsonObject node = (JsonObject) aJsonArray;
            Node sourceNode = nodes.get(node.get("source").getAsInt());
            Node destNode = nodes.get(node.get("target").getAsInt());
            links.add(new NodeToNodeLink(sourceNode, destNode,
                    node.get("value").getAsDouble()));
        }
    }

    List<NodeToNodeLink> getLinks() {
        return links;
    }

    Map<String, Node> getNodeMap() {
        Map<String, Node> result = new HashMap<>();
        for(Node n: nodes) {
            result.put(n.getId(), n);
        }
        return result;
    }
}
