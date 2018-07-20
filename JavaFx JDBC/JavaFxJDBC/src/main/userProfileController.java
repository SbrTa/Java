package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class userProfileController {

    public void homeButtonClicked()throws Exception{
        Parent home = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage window;
        Scene homeScene = new Scene(home);
        //window = new Stage();
        window = homeController.window;
        window.setScene(homeScene);
        window.show();
    }

}
