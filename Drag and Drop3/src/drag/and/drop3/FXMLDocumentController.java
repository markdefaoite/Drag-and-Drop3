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
    private ArrayList<MyRectangle> playerList = new ArrayList<>();
    private ArrayList<MyRectangle> collectList = new ArrayList<>();
    private int index = 0;
    @FXML
    private AnchorPane GameArea;
    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField playerHeight;
    
    @FXML
    private TextField playerWidth;
    @FXML
    private ColorPicker playerColor;
    private MyRectangle playerPiece = null;
    @FXML
    private TextField collectHeight;
    @FXML
    private TextField collectWidth;
    @FXML
    private ColorPicker collectColor;
    @FXML
    private TextField gameTitle;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void addRectangle(ActionEvent event) {
        double height = Double.parseDouble(heightField.getText());
        double width = Double.parseDouble(widthField.getText());
        Color color = colorPicker.getValue();
        MyRectangle rec = makeRectangle(height, width, color);
        obstacleList.add(rec);
    }

    @FXML
    private void buildGame(ActionEvent event) 
    {
        String title = gameTitle.getText();
        Builder builder = new Builder();
        builder.build(title, obstacleList, playerPiece, collectList);
    }

    @FXML
    private void addPlayer(ActionEvent event) {
        double height = Double.parseDouble(playerHeight.getText());
        double width = Double.parseDouble(playerWidth.getText());
        Color color = playerColor.getValue();
        MyRectangle player = makeRectangle(height, width, color);
        //playerList.add(player);
        if(playerPiece != null){
            System.out.println("Player piece overwritten");
        }
        
        playerPiece = player;
    }
    
   private MyRectangle makeRectangle(double height, double width, Color color){
       
        MyRectangle rec = new MyRectangle(index);
        index++;
        
        
        rec.setHeight(height);
        rec.setWidth(width);
        
        rec.setFill(color);
        System.out.println(rec.getColor());
        
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
                playerHeight.setText(Double.toString(rec.getHeight()));
                widthField.setText(Double.toString(rec.getWidth()));
                playerWidth.setText(Double.toString(rec.getWidth()));
                collectHeight.setText(Double.toString(rec.getHeight()));
                collectWidth.setText(Double.toString(rec.getWidth()));
                System.out.println();
                colorPicker.setValue(Color.web(rec.getFill().toString()));
                playerColor.setValue(Color.web(rec.getFill().toString()));
                collectColor.setValue(Color.web(rec.getFill().toString()));
            }
        });

        
        rec.setOnMouseDragged((MouseEvent e) -> {
            Node n = (Node) e.getSource();
            double Xco = n.getTranslateX() + e.getX();
            double Yco = n.getTranslateY() + e.getY();
                    
            n.setTranslateX(Xco);
            n.setTranslateY(Yco);
            rec.setXandY(Xco, Yco);
            e.consume();
        });
        
        GameArea.getChildren().add(rec);
        
        return rec;
   }

    @FXML
    private void addCollectible(ActionEvent event) {
        double height = Double.parseDouble(collectHeight.getText());
        double width = Double.parseDouble(collectWidth.getText());
        Color color = collectColor.getValue();
        MyRectangle rec = makeRectangle(height, width, color);
        collectList.add(rec);
    }
    
}