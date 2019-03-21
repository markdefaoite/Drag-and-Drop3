/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drag.and.drop3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author markd
 */
public class Builder {

    void build(String title, ArrayList<MyRectangle> obstaclesList, MyRectangle myGamePiece, ArrayList<MyRectangle> collectList) {
        if(title == null)
            title = "HTML Game";
        String fileName = title + ".html";
        System.out.println("build() called");
        System.out.println(obstaclesList.size());
        int subIdentifierCounter = 0;
        char identifier = 'A', subIdentifier = 'A';
        BufferedReader reader = null;
        FileWriter writer = null;

        try {
            
            reader = new BufferedReader(new FileReader("index.html"));
            writer = new FileWriter(fileName);
            String line = reader.readLine();
            String componentIntialize = "", componentDeclare = "", componentUpdate = "";
            if(myGamePiece != null)
                componentIntialize += "myGamePiece = new component("+ myGamePiece.getWidth()+", "+ myGamePiece.getHeight()+", \""+myGamePiece.getColor()+"\", "
                        +myGamePiece.getXcoord()+", "+myGamePiece.getYcoord()+", 0, 0); \n";
            
            for (int i = 0; i < obstaclesList.size(); i++) {
                componentDeclare += " var Obstacle" + identifier + subIdentifier + "; \n";
                System.out.println("B:3");
                componentIntialize += "obstacles.push(Obstacle" + identifier + subIdentifier + " = new component(" + obstaclesList.get(i).getWidth() + ", " + obstaclesList.get(i).getHeight() + ",\""+obstaclesList.get(i).getColor() + "\", "
                        + obstaclesList.get(i).Xcoord + ", " + obstaclesList.get(i).Ycoord + "));" + "\n";
                
                componentUpdate += "Obstacle" + identifier + subIdentifier + ".update(); \n";
                
                subIdentifierCounter++;
                if (subIdentifierCounter > 25) {
                    subIdentifierCounter = 0;
                    subIdentifier = 'A';
                    identifier++;
                } else {
                    subIdentifier++;
                }
            }
            for (int i = 0; i < collectList.size(); i++) {
                componentDeclare += " var Collectible" + identifier + subIdentifier + "; \n";
                
                componentIntialize += "collectibles.push(Obstacle" + identifier + subIdentifier + " = new component(" + collectList.get(i).getWidth() + ", " + collectList.get(i).getHeight() + ",\""+collectList.get(i).getColor() + "\", "
                        + collectList.get(i).Xcoord + ", " + collectList.get(i).Ycoord + "));" + "\n";
                
                componentUpdate += "Collectible" + identifier + subIdentifier + ".update(); \n";
                
                subIdentifierCounter++;
                if (subIdentifierCounter > 25) {
                    subIdentifierCounter = 0;
                    subIdentifier = 'A';
                    identifier++;
                } else {
                    subIdentifier++;
                }
            }
            System.out.println("B:4");
            if (line == null) {
                System.out.println("NULL file");
            } else {
                System.out.println("B:5");
                while (line != null) {
                    if (line.equals("<!-- title -->")) {
                        writer.append(title + "\n");
                    }
                    if (line.equals("<!-- component declare -->")) {
                        System.out.println(line + "\n" + componentDeclare);
                        writer.append(componentDeclare + "\n");

                    } else if (line.equals("<!-- component intialize --> ")) {
                        System.out.println(line + "\n" + componentIntialize);

                        writer.append(componentIntialize + "\n");

                        
                    } else if (line.equals("<!-- component update -->")) {
                        System.out.println(line + "\n" + componentUpdate);
                        writer.append(componentUpdate + "\n");
                    } else {
                        writer.append(line + "\n");
                    }
                    
                    line = reader.readLine();
                }
            }
            writer.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Builder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
