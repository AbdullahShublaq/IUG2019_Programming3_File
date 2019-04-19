/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryFiles;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Abdullah SHublaq
 */
public class Main extends Application {

    @FXML
    private Label Path;
    @FXML
    private Label Status;

    private File file;
    private String FileParent;
    private String FileName;

    @FXML
    public void SelectTextFile() {
        System.out.println("Select");
        try {
            FileChooser chooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            chooser.getExtensionFilters().add(extFilter);
            file = chooser.showOpenDialog(null);
            
            System.out.println(file.getParent());
            FileParent = file.getParent();
            
            Path.setText(file.getPath());
            
            System.out.println(file.getName().length());
            FileName = file.getName().substring(0, file.getName().length() - 4);
                       
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @FXML
    public void ConvertToBinary() {
        System.out.println("Convert");
        if (file != null) {
            Status.setText("Success!!!");
            try {
                File file2 = new File(FileParent+"\\"+FileName+".dat");
                file2.createNewFile();
                FileOutputStream fout = new FileOutputStream(file2);
                DataOutputStream dout = new DataOutputStream(fout);
                
                
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                while (br.ready()) {
                    dout.writeChars(br.readLine() + "\n");
                }

                dout.close();
                fout.close();

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } else {
            Status.setText("Failed!!!");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
        loader.setController(this);
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Convert to Binary File");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
