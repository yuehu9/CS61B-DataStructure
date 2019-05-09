package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.lab9.MyTrieSet;
import bearmaps.lab9.TrieSet61B;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.PointSet;
//import bearmaps.lab9.MyTrieSet;
//import bearmaps.lab9.TrieSet61B;
import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ___Yue Hu_____
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private List<Point> usefulPoints = new ArrayList<>();
    private HashMap<Point, Long> pointToNode = new HashMap<>();
    private TrieSet61B nameTrie = new MyTrieSet();
    private HashMap<String, String> cleanedToName = new HashMap<>();
    private HashMap<String, List<Node>> nameToNode = new HashMap<>();

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();
        for (Node node : nodes) {
            if (neighbors(node.id()) != null) {
                Point p = new Point(node.lon(), node.lat());
                usefulPoints.add(p);
                pointToNode.put(p, node.id());
            }
        }

        for (Node node : nodes) {
            if (node.name() != null) {
                String cleanedName = cleanString(node.name());
                cleanedToName.put(cleanedName, node.name());
                nameTrie.add(cleanedName);

                putNode(cleanedName, node, nameToNode);
            }

        }



    }

    private void putNode(String cleanedName, Node n, HashMap<String, List<Node>> map) {
        List<Node> nodes = map.get(cleanedName);
        if (nodes == null) { nodes = new ArrayList<>();}
        nodes.add(n);
        map.put(cleanedName, nodes);
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        PointSet kd = new KDTree(usefulPoints);
        Point nearest = kd.nearest(lon, lat);
        return pointToNode.get(nearest);
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     *
     * potential bug: if several name corresponds to the same cleaned name, only show one.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        String cleanedPre = cleanString(prefix);
        List<String> cleanedByPrefix = nameTrie.keysWithPrefix(cleanedPre);
        List<String> nameByPrefix = new ArrayList<>();
        for (String s : cleanedByPrefix) {
            nameByPrefix.add(cleanedToName.get(s));
        }
        return nameByPrefix;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> locations = new ArrayList<>();
        List<Node> nodes = nameToNode.get(cleanString(locationName));
        for (Node n : nodes) {
            Map<String, Object> mymap = new HashMap<>();
            mymap.put("lat", n.lat());
            mymap.put("lon", n.lon());
            mymap.put("name", n.name());
            mymap.put("id", n.id());
            locations.add(mymap);
        }

        return locations;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
