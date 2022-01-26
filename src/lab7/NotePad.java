/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Mohamed Hamdy
 */
public class NotePad {

    public NotePad() {
        Stage st = new Stage ();
        MenuBar bar = new MenuBar ();
        
        Menu file = new Menu ("File");
        Menu edit = new Menu ("Edit");
        Menu help = new Menu ("Help");
        
        MenuItem New = new MenuItem("New");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem Exit = new MenuItem("Exit");
        
        MenuItem undo = new MenuItem("Undo");
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");
        MenuItem Delete = new MenuItem("Delete");
        MenuItem selectall = new MenuItem("SelectAll");
        
        MenuItem aboutnotepad = new MenuItem("AboutNotepad");

        
        file.getItems().addAll(New,open,save,Exit);
        edit.getItems().addAll(undo,paste,copy,cut,Delete,selectall);
        help.getItems().addAll(aboutnotepad);
        
        bar.getMenus().addAll(file,edit,help);
       
        TextArea txt = new TextArea();
        txt.setMaxWidth(700);
        txt.setMaxHeight(600);
        
        cut.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txt.cut();
         });
        
         copy.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txt.copy();
         });
         
         paste.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txt.paste();
         });
         
         Delete.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
             IndexRange selection=txt.getSelection();
             int start=selection.getStart();
             int end=selection.getEnd();
             txt.deleteText(start,end);
         });
         
        undo.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txt.undo();
         });
        
        selectall.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            txt.selectAll();
         });
        
        Exit.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            Platform.exit();
        });
        
        save.setOnAction(ActionEvent-> {
            FileChooser fc = new FileChooser();
        File savefile =fc.showSaveDialog(null);
        try {
            FileWriter fw = new FileWriter(savefile);
            fw.write(txt.getText());
            fw.close();
        } catch(Exception ex ){
            System.out.println(ex.getMessage());
        }
        });
        
        
       open.setOnAction((ae) -> {
            FileChooser fc = new FileChooser();
            File openFile = fc.showOpenDialog(st);
            if(openFile != null){
                FileInputStream fis = null;
                int size;
                byte[] byto;
                try{
                fis = new FileInputStream(openFile);
                size = fis.available();
                byto = new byte[size];
                fis.read(byto);
                txt.setText(new String(byto));
                }catch(IOException ex){
                    System.out.println(ex.getMessage());
                }finally{
                    try{
                        fis.close();
                    }catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
       
       aboutnotepad.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            Dialog<String> dialog = new Dialog<String>();
            dialog.setTitle("Dialog");
            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
            dialog.setContentText("Hello Iam NOTEPAD");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
            
        });
        
        
        BorderPane pane = new BorderPane() ;
        pane.setTop(bar);
        pane.setCenter(txt);
        Scene sc = new Scene(pane);
        
        st.setScene(sc);
        st.setWidth(700);
        st.setHeight(600);
        st.show();
    }
    
}
