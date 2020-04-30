/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bramdon
 */
public class main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent raiz=FXMLLoader.load(getClass().getResource("emisor.fxml"));
        Scene escena=new Scene(raiz);
        primaryStage.setScene(escena);
        primaryStage.show();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
