package jgj.util.barogo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewerUtil {
    public static void showStage(Object object, String resourceDirectory, String cssDirectory, Object controller) throws Exception {
        try {
            FXMLLoader another = getFXMLLoader(object, resourceDirectory, controller);
            
            AnchorPane anotherPage = another.load();
            Scene anotherScene = new Scene(anotherPage);
            
            if (StringUtil.isNotEmpty(cssDirectory)) {
                anotherScene.getStylesheets().add(object.getClass().getResource(cssDirectory).toString());
            }
            
            showStage(anotherScene);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static FXMLLoader getFXMLLoader(Object obj, String resourceDirectory, Object controller) {
        FXMLLoader loader = new FXMLLoader(obj.getClass().getResource(resourceDirectory));
        loader.setController(controller);
        return loader;
    }
    
    public static void showStage(Scene scene) {
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
