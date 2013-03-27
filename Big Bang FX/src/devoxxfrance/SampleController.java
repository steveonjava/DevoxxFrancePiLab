/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoxxfrance;

import devoxxfrance.model.Person;
import devoxxfrance.view.PersonCell;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

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
        
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://idamf-restdemo.herokuapp.com/app/persons.json");
        ObservableList<Person> persons = null;
        try {
            ResponseHandler<ObservableList<Person>> responseHandler = new ResponseHandler<ObservableList<Person>>() {

                @Override
                public ObservableList<Person> handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, List<Map<String, String>>> map =  mapper.readValue(response.getEntity().getContent(), Map.class);
                    List<Map<String, String>> array = map.get("personList");
                    Person p;
                    List<Person> personsAL = new ArrayList<Person>();
                    for (Map<String, String> pMap : array) {
                        p = new Person();
                        p.setId(pMap.get("id"));
                        p.setName(pMap.get("name"));
                        p.setLastName(pMap.get("lastName"));
                        p.setProfession(pMap.get("profession"));
                        p.setRealName(pMap.get("realName"));
                        p.setWebsiteUrl(pMap.get("url"));
                        p.setImageName(pMap.get("imageUri"));
                        p.setBio(pMap.get("bio"));
                        personsAL.add(p);
                    }
                    return FXCollections.observableArrayList(personsAL);
                }
            };
            persons = httpclient.execute(get, responseHandler);
            listView.setItems(persons);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
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
