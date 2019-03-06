/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drag.and.drop3;

import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.*;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author markd
 */
public class FXMLDocumentController implements Initializable {
    
    
    private ArrayList<MyRectangle> obstacleList = new ArrayList<>();
    private Label label;
    private int index = 0;
    @FXML
    private AnchorPane GameArea;
    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private ColorPicker colorPicker;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void addRectangle(ActionEvent event) {
        System.out.println("addRectangle called");
        MyRectangle rec = new MyRectangle(index);
        index++;
        System.out.println("makeSquare: " + index);
        
        rec.setHeight(Double.parseDouble(heightField.getText()));
        rec.setWidth(Double.parseDouble(widthField.getText()));
        
        rec.setFill(colorPicker.getValue());
        System.out.println(rec.getColor());
        System.out.println("2");
        rec.setOnMouseReleased((MouseEvent e) -> {
            Node n = (Node) e.getSource();
            Bounds b = n.getBoundsInParent();

            System.out.println("X: " + b.getMaxX() + "Y: " + b.getMaxY());

            e.consume();
        });
        
        rec.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0)
            {
                heightField.setText(Double.toString(rec.getHeight()));
                widthField.setText(Double.toString(rec.getWidth()));
                System.out.println();
                colorPicker.setValue(Color.web(rec.getFill().toString()));
                
            }
        });

        System.out.println("3");
        rec.setOnMouseDragged((MouseEvent e) -> {
            Node n = (Node) e.getSource();
            double Xco = n.getTranslateX() + e.getX();
            double Yco = n.getTranslateY() + e.getY();
                    
            n.setTranslateX(Xco);
            n.setTranslateY(Yco);
            rec.setXandY(Xco, Yco);
            e.consume();
        });
        System.out.println("4");
        
        System.out.println("5");
        GameArea.getChildren().add(rec);
        System.out.println("6");
        obstacleList.add(rec);
    }

    @FXML
    private void buildGame(ActionEvent event) 
    {
        Builder builder = new Builder();
        builder.build(obstacleList);
    }
    
   
    
}