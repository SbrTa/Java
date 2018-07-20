package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController {
    public static Stage window;
    public void loginButtonClicked() throws Exception
    {
        Parent userProfile = FXMLLoader.load(getClass().getResource("userProfile.fxml"));
        Scene userProfileScene = new Scene(userProfile);
        //window = new Stage();
        window = Main.home;
        window.setScene(userProfileScene);
        window.show();
    }
}
