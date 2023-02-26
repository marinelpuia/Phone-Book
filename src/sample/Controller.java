package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import sample.datamodel.Contact;
import sample.datamodel.ContactData;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class Controller {
    private final LocalDate localDate = LocalDate.now();
    private ContactData data;
    @FXML
    private BorderPane mainPanel;
    @FXML
    private TableView<Contact> contactsTable;
    @FXML
    private Label copyRightLabel;
    @FXML
    private WebView webView;

    public void initialize() {

        // initialize the object data from ContactData class
        data = new ContactData();
        data.loadContacts();

        // initialize the TableView<Contact> contactsTable
        contactsTable.setItems(data.getContacts());
        copyRightLabel.setText("© 2019 - " + localDate.getYear() + " - Copyright - Marinel");
    }

    /*
         Method to show the dialog from contactDialog.fxml
         Save data entered by user in the dialog in contact.java
         by calling different methods
    */

    @FXML
    public void showAddContactDialog() {
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactdialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog pane");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ContactController contactController = fxmlLoader.getController();
            Contact newContact = contactController.getNewContact();
            data.addContact(newContact);
            data.saveContacts();
        }
    }

    // Method to edit the contacts and update the existing contacts

    @FXML
    public void showEditContactDialog() {

        Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contact not selected");
            alert.setHeaderText(null);
            alert.setContentText("Select the contact you want to edit");
            alert.showAndWait();
            return;

        }

        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Edit Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactdialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog pane");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ContactController contactController = fxmlLoader.getController();
        contactController.editContact(selectedContact);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            contactController.updateContact(selectedContact);
            data.saveContacts();
        }

    }

    /*
         Method to delete the contact from tableView
    */
    public void deleteContact() {

        Contact selectedContact = contactsTable.getSelectionModel().getSelectedItem();
        if (selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert, you have not selected any contacts.");
            alert.setHeaderText(null);
            alert.setContentText("Select the contact you want to delete!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm the deletion action.");
        alert.setHeaderText(null);
        alert.setWidth(300);
        alert.setHeight(100);
        alert.setContentText("Are you sure you want to delete ( " + selectedContact.getFirstName()
                + " ) ( " + selectedContact.getLastName() + " ) from the list?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            data.deleteContact(selectedContact);
            data.saveContacts();
        }
    }

    // Method to show the autor blog.fxml
    @FXML
    public void showMyBlog() {
        new ShowTheBlog().showBlog();
    }

    public void exitTheProgram() {
        Platform.exit();
    }

}
