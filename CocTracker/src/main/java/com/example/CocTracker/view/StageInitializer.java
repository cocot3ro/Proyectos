package com.example.CocTracker.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainFrameApplication.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = event.getStage();
            stage.setTitle("Clash of Clans Data Tracker");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
