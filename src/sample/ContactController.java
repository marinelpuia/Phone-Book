package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.datamodel.Contact;

public class ContactController {

    // Declare the textField from contactDialog.fxml
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField notesField;

    // Collect all data from contactDialog.fxml and then
    // update the constructor in Contact.java
    public Contact getNewContact() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String notes = notesField.getText();

        if (firstNameField.getText() == null || firstNameField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No fields filled");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("You did not complete the First Name field but rest assured " +
                    "you can edit it later, just use the edit option in the menu.");
            alert.showAndWait();
            firstNameField.setText("Null");
            firstName = firstNameField.getText();
        }

        if (lastNameField.getText() == null || lastNameField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No fields filled");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("You did not complete the Last Name field but rest assured " +
                    "you can edit it later, just use the edit option in the menu.");
            alert.showAndWait();
            lastNameField.setText("Null");
            lastName = lastNameField.getText();
        }

        if (phoneNumberField.getText() == null || phoneNumberField.getText().trim().isEmpty()) {
            // your code here
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No fields filled");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("You did not complete the Phone Number field but rest assured " +
                    "you can edit it later, just use the edit option in the menu.");
            alert.showAndWait();
            phoneNumberField.setText("Null");
            phoneNumber = phoneNumberField.getText();
        }

        if (notesField.getText() == null || notesField.getText().trim().isEmpty()) {
            // your code here
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No fields filled");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(150);
            alert.setContentText("You did not complete the Notes field but rest assured " +
                    "you can edit it later, just use the edit option in the menu.");
            alert.showAndWait();
            notesField.setText("Null");
            notes = notesField.getText();
        }


        // Create new object with the data collected above
        Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
        return newContact;
    }

    // Method to edit the contact

    public void editContact(Contact contact) {
        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneNumberField.setText(contact.getPhoneNumber());
        notesField.setText(contact.getNotes());
    }

    // Method to update the contact

    public void updateContact(Contact contact) {
        contact.setFirstName(firstNameField.getText());
        contact.setLastName(lastNameField.getText());
        contact.setPhoneNumber(phoneNumberField.getText());
        contact.setNotes(notesField.getText());
    }

}
