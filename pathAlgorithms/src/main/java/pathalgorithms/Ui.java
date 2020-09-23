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

        System.out.println("Place the map file in project root and write the filename on next line:");
        String fileName = in.nextLine();
        String[] grid = Parser.readFile(fileName); // alota alusta jos heittaa erroria

        Graph graphh = coordinates(in, grid);

        Dijkstra dijkstraa = new Dijkstra();
        dijkstraa.runDijkstra(graphh.getArrayGraph());

        double[] distances = new double[6]; //???
        AStar aStarr = new AStar();
        aStarr.runAStar(graphh.getArrayGraph(), distances);

        System.out.println("would you like to run another test? y/n");
        String answer = in.nextLine();
        if (answer.contains("y")) {
            runNew(in);
        }
        
        return;

    }

    public static Graph coordinates(Scanner in, String[] grid) {
        System.out.println("Give X coordinate of start vertex"); //check etta ei oo miinus tai out of bound kaikkiin
        int startX = Integer.parseInt(in.nextLine());
        System.out.println("Give Y coordinate of start vertex");
        int startY = Integer.parseInt(in.nextLine());
        System.out.println("Give X coordinate of goal vertex");
        int goalX = Integer.parseInt(in.nextLine());
        System.out.println("Give Y coordinate of goal vertex");
        int goalY = Integer.parseInt(in.nextLine());

        return Parser.parseGrid(grid, startX, startY, goalX, goalY);
    }


    
}
