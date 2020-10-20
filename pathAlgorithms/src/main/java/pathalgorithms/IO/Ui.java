/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathalgorithms.IO;

import pathalgorithms.dataStructures.Graph;
import pathalgorithms.pathFinders.JPS;
import pathalgorithms.pathFinders.Dijkstra;
import pathalgorithms.pathFinders.AStar;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

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

            ArrayList<String> mapFiles = new ArrayList<>();
            System.out.println("Would you like to process single map or a set of maps? single/set");
            String firstAnswer = in.nextLine();
            if (firstAnswer.equals("set")) {
                System.out.println("Write the absoulte path to the folder containing maps on the next line:");
                String folderName = in.nextLine();
                mapFiles = mapFiles(folderName);

            } else if (firstAnswer.equals("single")) {
                System.out.println("Write the absoulte path to the map file on the next line:");
                mapFiles.add(in.nextLine());
            }

            for (String fileName : mapFiles) {
                String[] grid = Parser.readFile(fileName);
                if (grid == null) {
                    continue;
                }
                Graph graph = parseGraph(in, mapFiles.size() == 1, grid);

                System.out.println("Running Dijkstra...");
                dijkstra.runDijkstra(graph);

                System.out.println("Running AStar...");
                aStar.runAStar(graph);

                System.out.println("Running JPS...");
                jps.runJPS(graph);
            }

            System.out.println("would you like to run another test? y/n");
            String answer = in.nextLine();
            run = answer.toLowerCase().equals("y");
        }

    }

    private static Graph parseGraph(Scanner in, boolean prompt, String[] grid) {
        if (prompt) {
            int startX = promptCoordinate(in, "Give X coordinate of start vertex", grid[0].length());
            int startY = promptCoordinate(in, "Give Y coordinate of start vertex", grid.length);
            int goalX = promptCoordinate(in, "Give X coordinate of goal vertex", grid[0].length());
            int goalY = promptCoordinate(in, "Give Y coordinate of goal vertex", grid.length);

            return Parser.parseGrid(grid, startX, startY, goalX, goalY);
        }

        return Parser.parseGrid(grid, -1, 0, 0, 0);
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

    private static ArrayList mapFiles(String folderName) {
        File folder = new File(folderName);
        ArrayList<String> mapFiles = new ArrayList<>();

        for (File file : folder.listFiles()) {
            if (file.getName().endsWith(".map")) {
                mapFiles.add(file.getAbsolutePath());
            }

        }
        return mapFiles;
    }

}
