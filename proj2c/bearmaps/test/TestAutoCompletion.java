package bearmaps.test;

import bearmaps.proj2c.AugmentedStreetMapGraph;
import org.junit.Before;
import org.junit.Test;
import bearmaps.proj2c.AugmentedStreetMapGraph;

import java.util.List;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TestAutoCompletion {
    private static final String PATHS_FILE = "../library-sp19/data/proj2c_test_inputs/path_results.txt";
    private static final String RESULTS_FILE = "../library-sp19/data/proj2c_test_inputs/directions_results.txt";
    private static final int NUM_TESTS = 8;
    private static final String OSM_DB_PATH = "../library-sp19/data/proj2c_xml/berkeley-2019.osm.xml";
    private static AugmentedStreetMapGraph graph;


    @Before
    public void setUp() throws Exception {
        graph = new AugmentedStreetMapGraph(OSM_DB_PATH);
    }

    @Test
    public void testAutoCompletion() {
        List<String> names = graph.getLocationsByPrefix("mon");
        System.out.println(names);
    }

    @Test
    public void testGetLocation() {
        List myMap = graph.getLocations("Montclair Branch Oakland Public Library");
        System.out.println(myMap);
    }
}
