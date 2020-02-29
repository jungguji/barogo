package jgj.util.barogo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewerUtil {
    public static void showStage(Object object, String resourceDirectory, String cssDirectory) throws Exception {
        try {
            FXMLLoader another = new FXMLLoader(object.getClass().getResource(resourceDirectory));
            AnchorPane anotherPage = (AnchorPane) another.load();
            Scene anotherScene = new Scene(anotherPage);
            anotherScene.getStylesheets().add(object.getClass().getResource(cssDirectory).toString());
            
            Stage stage = new Stage();
            stage.setScene(anotherScene);
            stage.show();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void showStageNotCss(Object object, String resourceDirectory, Object controller) throws Exception {
        try{
            FXMLLoader another = new FXMLLoader(object.getClass().getResource(resourceDirectory));
            another.setController(controller);
            
            AnchorPane anotherPage = (AnchorPane) another.load();
            Scene anotherScene = new Scene(anotherPage);
            
            Stage stage = new  Stage();
            stage.setScene(anotherScene);
            stage.show();
        } catch(Exception e) {
            throw e;
        }
    }
}
