/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms;

/**
 *
 * @author mikko
 */
import java.io.*;

public class Parser {

    public static final String ERROR_NO_SUCH_FILE = "File not found";
    public static final String ERROR_WRONG_FORMAT = "File has incorrect format";
    public static final String ERROR_READING_FILE = "Error reading file: ";

    public static String[] readFile(String fileName) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line = br.readLine();
            int height = 0;
            while (line != null && !line.startsWith("map")) {
                if (line.startsWith("height")) {
                    height = Integer.parseInt(line.split(" ")[1]);
                }
                line = br.readLine();
            }

            if (height == 0 || line == null) {
                System.out.println(ERROR_WRONG_FORMAT);
                return null;
            }

            String[] grid = new String[height];
            int j = 0;

            while ((line = br.readLine()) != null) {
                if (j >= height) {
                    System.out.println(ERROR_WRONG_FORMAT);
                    return null;
                }

                grid[j] = line;
                j++;
            }

            return grid;

        } catch (FileNotFoundException e) {
            System.out.println(ERROR_NO_SUCH_FILE);
        } catch (IOException e) {
            System.out.println(ERROR_READING_FILE + e.getMessage());
        }

        return null;
    }

    /**
     * Parses grid given as a character matrix into a {@link Graph}. Each cell
     * with is computed a unique identifier based on its row and column. Cells
     * with the character '.' are interpreted as traversable vertices, which
     * have undirected edges going to all adjacent traversable vertices. The
     * start and target vertices are saved to the object returned.
     *
     * @param grid A character matrix.
     * @param startX X coordinate of the starting vertex.
     * @param startY Y coordinate of the starting vertex.
     * @param endX X coordinate of the target vertex.
     * @param endY Y coordinate of the target vertex.
     * @return A {@link Graph} containing an adjacency list of the graph and the
     * IDs of the start and end vertices.
     */
    public static Graph parseGrid(String[] grid, int startX, int startY, int endX, int endY) {
        int cols = grid[0].length();
        Graph graph = new Graph(grid, grid.length * cols, startX + startY * cols, endX + endY * cols);

        int current;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < cols; j++) {
                current = j + i * cols;
                if (".".equals(String.valueOf(grid[i].charAt(j)))) {
                    if (j < cols - 1 && ".".equals(String.valueOf(grid[i].charAt(j + 1)))) {
                        graph.addAdjacent(j + 1 + i * cols, current);
                    }
                    if (i < grid.length - 1 && ".".equals(String.valueOf(grid[i + 1].charAt(j)))) {
                        graph.addAdjacent(j + (i + 1) * cols, current);
                    }

                } else {
                    graph.addEmptyAdjacency(current);
                }
            }
        }

        return graph;
    }

}
