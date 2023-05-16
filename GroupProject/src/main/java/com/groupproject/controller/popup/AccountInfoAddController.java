package com.groupproject.controller.popup;

import com.groupproject.entity.generic.Account;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AccountInfoAddController implements Initializable {
    @FXML
    TextField RegistrationPageUsername = new TextField();
    @FXML
    PasswordField RegistrationPagePassword = new PasswordField();
    @FXML
    PasswordField RegistrationPageConfirmPassword = new PasswordField();
    @FXML
    TextField RegistrationPageFirstName = new TextField();
    @FXML
    TextField RegistrationPageLastName = new TextField();
    @FXML
    TextField RegistrationPagePhoneNumber = new TextField();
    @FXML
    TextField RegistrationPageAddress = new TextField();
    @FXML
    Label RegistrationPageMessage = new Label();

    @FXML
    Label requiredUsername = new Label();
    @FXML
    Label requiredPassword = new Label();
    @FXML
    Label requiredConfirmPassword = new Label();
    @FXML
    Label requiredFirstName = new Label();
    @FXML
    Label requiredLastName = new Label();
    @FXML
    Label requiredPhoneNumber = new Label();
    @FXML
    Label requiredAddress = new Label();
    @FXML
    ChoiceBox<String> choiceList;

    private String[] choices = {"Admin", "Customer"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RegistrationPageMessage.setText("");
        RegistrationPageMessage.setTextFill(Color.RED);

        choiceList.getItems().addAll(Arrays.asList(choices));
    }

    public void onCreateButtonClick(ActionEvent event) {
        String username = RegistrationPageUsername.getText();
        String password = RegistrationPagePassword.getText();
        String confirmPassword = RegistrationPageConfirmPassword.getText();
        String firstName = RegistrationPageFirstName.getText();
        String lastName = RegistrationPageLastName.getText();
        String phoneNumber = RegistrationPagePhoneNumber.getText();
        String address = RegistrationPageAddress.getText();

        boolean legit = true;
        boolean status = true;
        RegistrationPageMessage.setText("");

        //format
        status = ViewHandler.checkStringGeneral(username);
        requiredUsername.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringGeneral(password);
        requiredPassword.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringGeneral(confirmPassword);
        requiredConfirmPassword.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringCharacterOnly(firstName);
        requiredFirstName.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringCharacterOnly(lastName);
        requiredLastName.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringNumberOnly(phoneNumber);
        requiredPhoneNumber.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringGeneral(address);
        requiredAddress.setVisible(!status);
        legit &= status;

        if (!legit){
            RegistrationPageMessage.setText("Something gone wrong");
            return;
        }

        //extra err
        if(EntityHandler.accountIsExist(username)){
            RegistrationPageMessage.setText("Username already exists!");
            return;
        }

        if(password.compareTo(confirmPassword) != 0){
            RegistrationPageMessage.setText("Password doesn't match!");
            return;
        }

        //legit
        Account newAccount = Account.getNewAccount(username, password, firstName, lastName, phoneNumber, address);

        if (choiceList.getValue().equals(choices[0])){ //is admin
            newAccount.makeAdmin();
        }

        EntityHandler.addAccount(newAccount);

        closePopup(event);
    }

    public void closePopup(ActionEvent event) {
        ViewHandler.closePopup(event);
    }
}
