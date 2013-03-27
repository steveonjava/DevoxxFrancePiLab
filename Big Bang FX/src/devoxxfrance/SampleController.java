/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoxxfrance;

import devoxxfrance.model.Person;
import devoxxfrance.view.PersonCell;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author mike
 */
public class SampleController implements Initializable {
    
    @FXML
    private Label nameLabel;
    
    @FXML
    private Label realNameLabel;
    
    @FXML
    private Label professionLabel;
    
    @FXML
    private TextArea bioTextArea;
    
    @FXML
    private ListView<Person> listView;
    
    @FXML
    private ImageView imageView;
    
/*    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*try {
            URL personUrl = new URL("http://idamf-restdemo.herokuapp.com/app/persons.json");
            HttpURLConnection conn = (HttpURLConnection) personUrl.openConnection();
            
            System.out.println(conn.getContent());
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        
        Person a = new Person();
        a.setName("Leonard");
        a.setLastName("Hofstadter");
        a.setProfession("Experimental physicist");
        a.setRealName("Johnny Galecki");
        a.setBio("Leonard Leakey Hofstadter, Ph.D., is a fictional character on the CBS television series The Big Bang Theory, portrayed by actor Johnny Galecki. Leonard is an experimental physicist originally from New Jersey who shares an apartment with colleague and friend Dr. Sheldon Cooper (Jim Parsons). Leonard and Sheldon are named after actor/producer Sheldon Leonard, and Nobel Prize Laureates Robert Hofstadter and Leon Cooper. Leonard has been described as the straight man of the series. Penny (Kaley Cuoco) is Leonard's next-door neighbor and main love interest, and the teasing of romance between the two of them is a major force driving the series. For his portrayal, Galecki was nominated for a Primetime Emmy Award and a Golden Globe Award.");
        a.setImageName("Leonard_Hofstadter.jpg");
        a.setWebsiteUrl("http://en.wikipedia.org/wiki/Johnny_Galecki");
        
        Person b = new Person();
        b.setName("Howard");
        b.setLastName("Wollowitz");
        b.setProfession("Aerospace Engineer");
        b.setRealName("Simon Helberg");
        b.setBio("Howard Joel Wolowitz, M.Eng is a fictional character on the CBS television series The Big Bang Theory, portrayed by actor Simon Helberg. Among the main male characters in the show, Howard is distinguished for lacking a doctoral degree, for still living with his mother, and for believing himself to be a \"ladies' man\". Simon Helberg's character is named after a computer programmer known by the show's co-creator Bill Prady.");
        b.setImageName("Howard_Wolowitz.jpg");
        b.setWebsiteUrl(null);
        
        
        ObservableList<Person> persons = FXCollections.observableArrayList(a, b);
        listView.setItems(persons);
        
        listView.setCellFactory(new Callback<ListView<Person>, ListCell<Person>>() {
          public ListCell<Person> call(ListView<Person> param) {
            return new PersonCell();
          }
        }); // setCellFactory

        listView.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<Person>() {
          public void changed(ObservableValue<? extends Person> observable,
              Person oldValue, Person newValue) {
              
              nameLabel.setText(newValue.getLastName());
              realNameLabel.setText(newValue.getRealName());
              professionLabel.setText(newValue.getProfession());
              bioTextArea.setText(newValue.getBio());
              
              
              
              Image image = new Image("devoxxfrance/" + newValue.getImageName());
              imageView.setImage(image);
          }
        });
    }    
}
