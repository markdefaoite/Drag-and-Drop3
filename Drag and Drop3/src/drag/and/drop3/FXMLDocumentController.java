/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drag.and.drop3;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.*;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author markd
 */
public class FXMLDocumentController implements Initializable {
    
     private ArrayList<Rectangle> list = new ArrayList<>();
    private Label label;
    private AnchorPane gameArea;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addRectangle(ActionEvent event) {
        double height, width;
        height = 20;//Double.parseDouble(heightField.getText());
        width = 20;//Double.parseDouble(widthField.getText());
        
        Rectangle rec = new Rectangle();
        
        rec.setHeight(height);
        rec.setWidth(width);
        
        rec.setFill(DODGERBLUE);
        rec.setOnMouseReleased((MouseEvent e) -> {
            Node n = (Node) e.getSource();
            Bounds b = n.getBoundsInParent();

            //System.out.println("X: " + b.getMaxX() + "Y: " + b.getMaxY());

            e.consume();
        });
        rec.setOnMouseDragged((MouseEvent e) -> {
            Node n = (Node) e.getSource();
            n.setTranslateX(n.getTranslateX() + e.getX());
            n.setTranslateY(n.getTranslateY() + e.getY());
            
            e.consume();
        });
        list.add(rec);
        gameArea.getChildren().add(rec);
    }

    @FXML
    private void buildGame(ActionEvent event) {
    }
    
   
    
}
