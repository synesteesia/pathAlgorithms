/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.pathalgorithms.Graph;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mycompany.pathalgorithms.Parser;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 *
 * @author mikko
 */
public class ParserTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static final String[] TEST_MAP
            = {"WWWWWWW@@@@@@TTT....",
                "WWW@@@TTTTTTTTT.....",
                "@@@TTT.......@TTT@WW",
                ".....TTTTTTTTTTTTTTT",
                "WWWWWWWWWWWWWWWWWWWW",
                "@@@@@@@@@@@@@@@@@@@@",
                "WWWW@@@@@@@@@@...@@@",
                "....................",
                "WWWWWWWW.WWWWWW.WWWW",
                ".@@@@T@@@@@@@@@@@@@@"};

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void readExistingFileWorks() {
        String filePath = "./src/test/java/testmap.map";
        String[] grid = Parser.readFile(filePath);

        assertEquals(10, grid.length);
        assertEquals(20, grid[0].length());
        for (int i = 0; i < 10; i++) {
            assertEquals(TEST_MAP[i], grid[i]);
        }
    }

    @Test
    public void readFileInWrongFormatReturnsNull1() {
        String filePath = "./src/test/java/missingheight.map";
        String[] grid = Parser.readFile(filePath);

        assertNull(grid);
        assertEquals(Parser.ERROR_WRONG_FORMAT, outputStreamCaptor.toString().trim());
    }

    @Test
    public void readFileInWrongFormatReturnsNull2() {
        String filePath = "./src/test/java/missingmap.map";
        String[] grid = Parser.readFile(filePath);

        assertNull(grid);
        assertEquals(Parser.ERROR_WRONG_FORMAT, outputStreamCaptor.toString().trim());
    }

    @Test
    public void readFileInWrongFormatReturnsNull3() {
        String filePath = "./src/test/java/wrongheight.map";
        String[] grid = Parser.readFile(filePath);

        assertNull(grid);
        assertEquals(Parser.ERROR_WRONG_FORMAT, outputStreamCaptor.toString().trim());
    }

    @Test
    public void readNonExistentFileFails() {
        String filePath = "./src/test/java/missingfile.map";
        String[] grid = Parser.readFile(filePath);

        assertNull(grid);
        assertEquals(Parser.ERROR_NO_SUCH_FILE, outputStreamCaptor.toString().trim());
    }

    @Test
    public void parseGridReturnsGraphWithCorrectNumberOfVertices() {
        String filePath = "./src/test/java/testmap.map";
        String[] grid = Parser.readFile(filePath);
        Graph graph = Parser.parseGrid(grid, 0, 0, 0, 0);

        assertEquals(200, graph.getArrayGraph().length);
    }

    @Test
    public void parseGridReturnsGraphWithCorrectStartVertex() {
        String filePath = "./src/test/java/testmap.map";
        String[] grid = Parser.readFile(filePath);
        Graph graph = Parser.parseGrid(grid, 7, 2, 0, 0);

        assertEquals(47, graph.getStartVertex());
    }

    @Test
    public void parseGridReturnsGraphWithCorrectEndVertex() {
        String filePath = "./src/test/java/testmap.map";
        String[] grid = Parser.readFile(filePath);
        Graph graph = Parser.parseGrid(grid, 0, 0, 13, 7);

        assertEquals(153, graph.getEndVertex());
    }

    @Test
    public void parseGridParsesEmptyAdjacencyListForNonTraversableVertices() {
        String filePath = "./src/test/java/testmap.map";
        String[] grid = Parser.readFile(filePath);
        Graph graph = Parser.parseGrid(grid, 0, 0, 0, 0);

        assertEquals(0, graph.getArrayGraph()[0].size());
        assertEquals(0, graph.getArrayGraph()[44].size());
        assertEquals(0, graph.getArrayGraph()[199].size());
    }

    @Test
    public void parseGridParsesCorrectAdjacencyListForTraversableVertices1() {
        String filePath = "./src/test/java/testmap.map";
        String[] grid = Parser.readFile(filePath);
        Graph graph = Parser.parseGrid(grid, 0, 0, 0, 0);

        assertEquals(2, graph.getArrayGraph()[19].size());
        assertTrue(graph.getArrayGraph()[19].contains(18));
        assertTrue(graph.getArrayGraph()[19].contains(39));
    }

    @Test
    public void parseGridParsesCorrectAdjacencyListForTraversableVertices2() {
        String filePath = "./src/test/java/testmap.map";
        String[] grid = Parser.readFile(filePath);
        Graph graph = Parser.parseGrid(grid, 0, 0, 0, 0);

        assertEquals(4, graph.getArrayGraph()[155].size());
        assertTrue(graph.getArrayGraph()[155].contains(156));
        assertTrue(graph.getArrayGraph()[155].contains(154));
        assertTrue(graph.getArrayGraph()[155].contains(135));
        assertTrue(graph.getArrayGraph()[155].contains(175));
    }
}
