
package lab6;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author mac
 */
public class App extends Application {
    Scene WarehouseInventory ;    //Warehouse Inventory Scene
    Scene SearchScene ;  //Search Scene
     java.io.File file=new java.io.File("prr.txt");
            ObservableList<String> obItems = FXCollections.observableArrayList();
    ObservableList<String> obQuantity = FXCollections.observableArrayList();
    ObservableList<String> obCategory = FXCollections.observableArrayList();
    ObservableList<String> obExDate = FXCollections.observableArrayList();
    ObservableList<String> obItemFilter = FXCollections.observableArrayList();
  
    @Override
    public void start(Stage primaryStage) {
      
        //######## Task1: Observablelists

       ListView lvItems = new ListView();

        ListView lvQuantity = new ListView();

        ListView lvCategory = new ListView();

        ListView lvExDate = new ListView();

          ListView lvItemsByCategory = new ListView();
          
        lvItems.setItems(obItems);
        lvQuantity.setItems(obQuantity);
        lvCategory.setItems(obCategory);
        lvExDate.setItems(obExDate);
        lvItemsByCategory.setItems(obItemFilter);
        //The WarehouseInventory Scene ------------------------------------------------------------------------------------------

        Label ItemName  = new Label("Item Name: ");
        Label ItemQuantity  = new Label("Item Quantity: ");
        Label ItemCategory  = new Label("Item Category: ");
        Label ItemExDate  = new Label("Item Expiry Date: ");
   
        TextField txItemName = new TextField();
        TextField txQuantity = new TextField();
        ComboBox<String> Category = new ComboBox();
        Category.getItems().addAll("Fruits", "Vegetables","Meat","Drinks","Sereal", "Bread", "Supplies");
        Category.setValue("Fruits");
        
        
        ComboBox cboQType = new ComboBox();
        cboQType.getItems().addAll("Packs", "Boxes", "KG", "Grams");
        cboQType.setValue("Boxes");
        
        DatePicker ExDate = new DatePicker();
        ExDate.setValue(LocalDate.now());
        
        GridPane form = new GridPane();
        form.add(ItemName,0,0);
        form.add(txItemName,1,0);

        form.add(ItemQuantity,0,1);
        form.add(txQuantity,1,1);
        form.add(cboQType, 2, 1);

        form.add(ItemCategory,0,2);
        form.add(Category,1,2);

        form.add(ItemExDate,0,3);
        form.add(ExDate,1,3);

        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);

        Button Add = new Button("Add");
        Button Update = new Button("Update");
        Button Search = new Button("Search");
        Button Clear = new Button ("Clear");
Button ex = new Button ("ExportToFile");
        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(Add,Update,Clear,ex);

        Label lblmsg = new Label();
        lblmsg.setTextFill(Color.DARKRED);
        lblmsg.setMaxHeight(30);
        lblmsg.setMaxWidth(500);
        lblmsg.setMinHeight(30);
        lblmsg.setMinWidth(500);
        lblmsg.setFont(new Font(16.0));
        lblmsg.setAlignment(Pos.CENTER);
        
       
       
        HBox lvs = new HBox(10);
        lvs.setAlignment(Pos.CENTER);
        lvs.setMaxWidth(700);
        lvs.setMinWidth(700);
        lvs.setMaxHeight(300);
        lvs.setMinHeight(300);


        VBox VBlvName = new VBox(10);

        VBox VBlvMobile = new VBox(10);

        VBox VBlvClinic = new VBox(10);

        VBox VBlvTime = new VBox(10);

        VBlvName.getChildren().addAll(new Label("Item Name: "), new ScrollPane(lvItems));
        VBlvMobile.getChildren().addAll(new Label("Quantity: "), new ScrollPane(lvQuantity));
        VBlvClinic.getChildren().addAll(new Label("Category: "), new ScrollPane(lvCategory));
        VBlvTime.getChildren().addAll(new Label("Expiry Date: "), new ScrollPane(lvExDate));

        lvs.getChildren().addAll(VBlvName,VBlvMobile,VBlvClinic,VBlvTime);
        
        HBox btSearch = new HBox(20);
        btSearch.getChildren().add(Search);
        btSearch.setAlignment(Pos.BOTTOM_RIGHT);
        btSearch.setPadding(new Insets (20));
        
        VBox controls = new VBox(20);
        controls.setAlignment(Pos.CENTER);
        controls.getChildren().addAll(form,lblmsg, buttons, lvs, btSearch);
        
        WarehouseInventory = new Scene(controls, 800, 700);
        
        //The Search Scene -----------------------------------------------------------------------------------------

        VBox searchControl = new VBox();
        Label lbltf = new Label("Enter the Item name: ") ;

        TextField tfSearch = new TextField();
        Button SearchItem = new Button("Search");
        Label msg = new Label();
        msg.setTextFill(Color.DARKRED);
        msg.setMaxHeight(100);
        msg.setMaxWidth(700);
        msg.setMinHeight(100);
        msg.setMinWidth(700);
        msg.setFont(new Font(20.0));
        
        Button Back = new Button("Back");

        HBox  paneSearch = new HBox(lbltf, tfSearch, SearchItem);
        paneSearch.setAlignment(Pos.TOP_LEFT);
        paneSearch.setSpacing(10);

        searchControl.getChildren().addAll(paneSearch, msg);
        searchControl.setAlignment(Pos.TOP_LEFT);
        searchControl.setSpacing(10);


        HBox filter = new HBox(10);
        filter.setAlignment(Pos.TOP_LEFT);
        ComboBox<String> Itemfilter = new ComboBox();
        Itemfilter.getItems().addAll("Fruits", "Vegetables","Meat","Drinks","Sereal", "Bread", "Supplies");
        Itemfilter.setValue("Fruits");
      
        lvItemsByCategory.setPrefWidth(750);

        filter.getChildren().addAll(new Label("Filter Items by Category: "), Itemfilter);
        VBox cboFilter = new VBox(20);
        cboFilter.getChildren().add(lvItemsByCategory);


        VBox root2 = new VBox(10);
        root2.setPadding(new Insets(20));
        HBox back = new HBox(10);
        back.setAlignment(Pos.BOTTOM_RIGHT);
        back.getChildren().add(Back);
        root2.getChildren().addAll(searchControl,filter, cboFilter,back);
        SearchScene=new Scene(root2,800,700);
        //Actions of Scene WarehouseInventory ------------------------------------------------------------------------------------------
        //###### Task 2.1: Add an ActionEvent on the Add button:
        
        
        Add.setOnAction(e->{
            
           String iin=txItemName.getText();
        String qq=txQuantity.getText();
        
           if (iin.isEmpty()||qq.isEmpty()) {        
          lblmsg.setText("Enter the item name and quantity");
          return;
    }
           if (!qq.matches("\\d+")) {        
          lblmsg.setText("The quantity must be digits only");
          return;
    }
                
           if (obItems.contains(iin)) {        
          lblmsg.setText("The item is already inserted");
          return;
    }
           ///
           obItems.add(iin);
        obQuantity.add(qq+" "+cboQType.getValue());      
        String cc=Category.getValue();
        obCategory.add(cc);
        
        LocalDate exdate=ExDate.getValue();
        obExDate.add(exdate.toString());
         
      lblmsg.setText("Item Added");
        });
    

        //##### Task 2.3: Add an ActionEvent on the Update button:
        Update.setOnAction(e-> {
            
       String ti= txItemName.getText();
       lblmsg.setText("Item " + ti + " was updated");
         obItems.contains(ti);
        int ind= obItems.indexOf(ti);
         String uQ=txQuantity.getText();
         if (ind == -1) {
        lblmsg.setText("not found");
        return;
    }
           if (!uQ.matches("\\d+")) {        
          lblmsg.setText("The quantity must be digits only");
          return;
    }
      
       String uC=Category.getValue();
       LocalDate uEx =ExDate.getValue();
        obQuantity.set(ind,uQ+" "+cboQType.getValue());
        obCategory.set(ind,uC);
        obExDate.set(ind,uEx.toString());
      
                
        });
        
        ex.setOnAction(e->{
    
     try {
                FileWriter fw = new FileWriter("in.txt");
                    String s = "Item Name: "+txItemName.getText()+"\nQuantity: "+txQuantity.getText()+"\nCategory: "+
                         Category.getValue()+"\nExpiry Date: "+ ExDate.getValue()+"\n\n";
                    fw.write(s);
                   
                fw.close();
            } catch (IOException a) {  
            }
    
        });
    

        //##### Task 2.4: Add an ActionEvent on the “Search” button:
        Search.setOnAction(e-> {
    primaryStage.setScene(SearchScene);
        });
        //##### Task 2.5: Add an ActionEvent on the “Clear” button:
        Clear.setOnAction(e-> {
    txItemName.clear();
    txQuantity.clear();
    ExDate.setValue(LocalDate.now());
    lblmsg.setText("");
});

        //Actions of Scene Search---------------------------------------------------------------
        //##### Task 3.1: Add an ActionEvent on the SearchItem button:
        SearchItem.setOnAction(e -> {
            String tft=tfSearch.getText().trim();
          
              if (tft.isEmpty()) {        
          msg.setText("Enter the item name to search the information");
          return;
    }
              if(obItems.isEmpty()){
        msg.setText("No Items in the list.");
    }else{
        boolean found = false;
        for (String s : obItems) {
            if (s.contains(tft)) {
                
                int in = obItems.indexOf(s);
                String q = obQuantity.get(in);
                String exdd = obExDate.get(in);

                msg.setText("The quantity of " + s + " is: " + q + " Expire at: " + exdd);
                found = true;
                break;
            }
        }
        if (!found) {
            
            msg.setText("The item not found.");
        }
    
    }
              
        });

        //Task 3.2: Add an ActionEvent on the Itemfilter combobox:
       Itemfilter.setOnAction(e-> {
String sc=Itemfilter.getValue();

if (sc!=null){  /////////////*
    obItemFilter.clear();
    
    for(int i=0; i< obItems.size(); i++){
        if(obCategory.get(i).equals(sc)){
            String oit ="Item Name: "+obItems.get(i)+"  Quantity: "
                    +obQuantity.get(i)+"  Category: "+obCategory.get(i)+"  Expiry Date: "+obExDate.get(i);
            obItemFilter.add(oit);
        }
    }
    lvItemsByCategory.setItems(obItemFilter);
}
       });

        //Task 3.3: Add an ActionEvent on the Back button:
        Back.setOnAction(e-> {
        primaryStage.setScene(WarehouseInventory);
        });


  


        //###### Task 2.2: Add Listeners on the lvItems, lvQuantity, lvCategory and lvExDate ListViews:
        //###### You only need to uncomment the following part
    
        
        lvItems.getSelectionModel().selectedItemProperty().addListener(e-> {
          
            lvItems.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = lvItems.getSelectionModel().getSelectedIndex();
            //System.out.println(index);
            lvQuantity.getSelectionModel().select(index);
            lvCategory.getSelectionModel().select(index);
            lvExDate.getSelectionModel().select(index);
            
            txItemName.setText(obItems.get(index));
            
            String[] Q = obQuantity.get(index).split(" ");
            String quant = Q[0];
            txQuantity.setText(quant);
            
            Category.setValue(obCategory.get(index));
            ExDate.setValue(LocalDate.parse(obExDate.get(index)));
           

        });

        lvQuantity.getSelectionModel().selectedItemProperty().addListener(e-> {
            lvQuantity.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = lvQuantity.getSelectionModel().getSelectedIndex();
            lvItems.getSelectionModel().select(index);
            lvCategory.getSelectionModel().select(index);
            lvExDate.getSelectionModel().select(index);

            txItemName.setText(obItems.get(index));
            
            String[] Q = obQuantity.get(index).split(" ");
            String quant = Q[0];
            txQuantity.setText(quant);
            Category.setValue(obCategory.get(index));
            ExDate.setValue(LocalDate.parse(obExDate.get(index)));

        });

        lvCategory.getSelectionModel().selectedItemProperty().addListener(e-> {
            lvCategory.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = lvCategory.getSelectionModel().getSelectedIndex();
            //System.out.println(index);
            lvItems.getSelectionModel().select(index);
            lvQuantity.getSelectionModel().select(index);
            lvExDate.getSelectionModel().select(index);

            txItemName.setText(obItems.get(index));
            String[] Q = obQuantity.get(index).split(" ");
            String quant = Q[0];
            txQuantity.setText(quant);
            Category.setValue(obCategory.get(index));
            ExDate.setValue(LocalDate.parse(obExDate.get(index)));


        });


        lvExDate.getSelectionModel().selectedItemProperty().addListener(e-> {
            lvExDate.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = lvExDate.getSelectionModel().getSelectedIndex();
            
            lvItems.getSelectionModel().select(index);
            lvQuantity.getSelectionModel().select(index);
            lvCategory.getSelectionModel().select(index);

            txItemName.setText(obItems.get(index));
            String[] Q = obQuantity.get(index).split(" ");
            String quant = Q[0];
            txQuantity.setText(quant);
            Category.setValue(obCategory.get(index));
            ExDate.setValue(LocalDate.parse(obExDate.get(index)));



        });
        
      
        primaryStage.setTitle("Warehouse Inventory");
        primaryStage.setScene(WarehouseInventory);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
  


}

