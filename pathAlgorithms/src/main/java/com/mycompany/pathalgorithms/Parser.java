/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pathalgorithms;

/**
 *
 * @author mikko
 */
import java.io.*;

public class Parser {

    public static char[][] readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            int height = 0, width = 0;
            while (!(line = br.readLine()).startsWith("map")) {
                if (line.startsWith("height")) {
                    height = Integer.parseInt(line.split(" ")[1]);
                } else if (line.startsWith("width")) {
                    width = Integer.parseInt(line.split(" ")[1]);
                }
            }

            char[][] grid = new char[height][width];
            int j = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);

                for (int i = 0; i < line.length(); i++) {
                    grid[i][j] = line.charAt(i);
                    i++;
                }
                j++;
            }

            return grid;

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());

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
    public Graph parseGrid(char[][] grid, int startX, int startY, int endX, int endY) {
        int cols = grid[0].length;
        Graph graph = new Graph(grid.length * cols, startX + startY * cols, endX + endY * cols);

        int current;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                current = j + i * cols;
                if (grid[i][j] == '.') {
                    if (j < cols - 1 && grid[i][j + 1] == '.') {
                        graph.addAdjacent(j + 1 + i * cols, current);
                    }
                    if (i < grid.length - 1 && grid[i + 1][j] == '.') {
                        graph.addAdjacent(j + cols + i * cols, current);
                    }

                } else {
                    graph.addEmptyAdjacency(current);
                }
            }
        }

        return graph;
    }

}
