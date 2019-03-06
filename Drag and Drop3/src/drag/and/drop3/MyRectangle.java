/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drag.and.drop3;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Mark
 */
public class MyRectangle extends Rectangle {

    int id = 0;
    double Xcoord, Ycoord;

    public MyRectangle(int id) {
        this.id = id;
        Xcoord = 0;
        Ycoord = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setXandY(double x, double y){
        Xcoord = x;
        Ycoord = y;
    }
    
    public double getXcoord(){
        return Xcoord;
    }
    
    public double getYcoord(){
        return Ycoord;
    }
    
    public int getIntId() {
        return id;
    }
    
    public String getColor(){
        String fill = this.getFill().toString();
        char[] chars = fill.toCharArray();
        String out ="#";
        for(int x = 2; x <chars.length;x++){
            out += chars[x];
        }
        
        return out;
        
    }
}