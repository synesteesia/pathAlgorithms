/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms;

import java.util.Scanner;

/**
 *
 * @author mikko
 */
public class Ui {

    public static void runNew(Scanner in) {

        Dijkstra dijkstra = new Dijkstra();
        AStar aStar = new AStar();
        JPS jps = new JPS();
        boolean run = true;

        while (run) {
            String[] grid = null;

            while (grid == null) {
                System.out.println("Write the absoulte path to the map file on the next line:");
                String fileName = in.nextLine();
                grid = Parser.readFile(fileName);
            }

            Graph graph = coordinates(in, grid);

            System.out.println("Running Dijkstra...");
            dijkstra.runDijkstra(graph);

            System.out.println("Running AStar...");
            aStar.runAStar(graph);

            System.out.println("Running JPS...");
            jps.runJPS(graph);

            System.out.println("would you like to run another test? y/n");
            String answer = in.nextLine();
            run = answer.toLowerCase().equals("y");
        }

    }

    private static Graph coordinates(Scanner in, String[] grid) {
        int startX = promptCoordinate(in, "Give X coordinate of start vertex", grid[0].length());
        int startY = promptCoordinate(in, "Give Y coordinate of start vertex", grid.length);
        int goalX = promptCoordinate(in, "Give X coordinate of goal vertex", grid[0].length());
        int goalY = promptCoordinate(in, "Give Y coordinate of goal vertex", grid.length);

        return Parser.parseGrid(grid, startX, startY, goalX, goalY);
    }

    private static int promptCoordinate(Scanner in, String prompt, int maxValue) {
        int coord = -1;
        while (coord == -1) {
            System.out.println(prompt);
            try {
                coord = Integer.parseInt(in.nextLine());
                if (coord > maxValue || coord < 0) {
                    System.out.println("Please provide an integer between 0 and " + maxValue + ", inclusive");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please give a valid integer");
            }
        }

        return coord;
    }
}
