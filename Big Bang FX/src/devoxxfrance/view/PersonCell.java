/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package devoxxfrance.view;

import devoxxfrance.model.Person;
import javafx.scene.control.ListCell;

/**
 *
 * @author mike
 */
public class PersonCell extends ListCell<Person> {

    @Override
    protected void updateItem(Person person, boolean empty) {
        super.updateItem(person, empty);
        if (person != null) {
            this.setText(person.getName() + " " + person.getLastName());
        }
        
    }
    
}
