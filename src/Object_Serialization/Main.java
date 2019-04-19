/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object_Serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author abdullah Shublaq
 */
public class Main extends Application {

    @FXML
    private TextField AccNO;
    @FXML
    private TextField AccName;
    @FXML
    private TextField AccBalance;
    @FXML
    private TextArea area;

    private List<Account> list = new ArrayList<>();//to write on file
    private List<Account> Newlist = new ArrayList<>();//to reaf from file
    
    private File file = new File("C:\\Users\\jit\\Desktop\\Accounts.dat");

    @FXML
    public void Add() throws FileNotFoundException, IOException {
        System.out.println("add");
//        System.out.println("NO : "+AccNO.getText()+" name : "+AccName.getText()+" balance : "+AccBalance.getText());

        Account acc = new Account(AccNO.getText(), AccName.getText(), AccBalance.getText());
        list.add(acc);

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
    }

    @FXML
    public void ShowAll() throws FileNotFoundException, IOException, ClassNotFoundException {
        System.out.println("show");
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            
            Newlist = (List<Account>) ois.readObject();
            area.setText("");
            for (Account acc : Newlist) {
//            System.out.println("AccNO : "+acc.getAccNo());
//            System.out.println("AccName : "+acc.getAccName());
//            System.out.println("AccBalance : "+acc.getAccBalance());
//            System.out.println("-----------------------");

                area.appendText("AccNO : " + acc.getAccNo() + "\nAccName : " + acc.getAccName()
                        + "\nAccBalance : " + acc.getAccBalance() + "\n-----------------------\n");
            }
            
            sortDesending();
            printNewListContent();
            
        }
    }

    //sort desending
    public void sortDesending() {
        Newlist.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (Double.parseDouble(o2.getAccBalance()) > Double.parseDouble(o1.getAccBalance())) {
                    return 1;
                } else if (Double.parseDouble(o2.getAccBalance()) < Double.parseDouble(o1.getAccBalance())) {
                    return -1;
                }
                return 0;
            }
        });
    }
    
    //print each object data on a separate line
    public void printNewListContent(){
        for (int i = 0; i < Newlist.size(); i++) {
            System.out.print(Newlist.get(i).getAccNo() + " \\ ");
            System.out.print(Newlist.get(i).getAccName()+ " \\ ");
            System.out.print(Newlist.get(i).getAccBalance()+ " \\ ");
            System.out.println("");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
        loader.setController(this);
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Write/Read serialized objects");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
