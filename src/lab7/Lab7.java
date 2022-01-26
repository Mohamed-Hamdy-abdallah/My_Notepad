/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Mohamed Hamdy
 */
public class Lab7 extends Application {

    /**
     * @param args the command line arguments
     */
    @Override
    public void start(Stage stage) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         NotePad NT = new NotePad(); 
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }

    
    
}
