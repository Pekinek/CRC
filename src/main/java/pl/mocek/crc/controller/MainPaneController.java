package pl.mocek.crc.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.mocek.crc.arduino.AtmegaSender;
import pl.mocek.crc.data.KeysStatus;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable {


    private KeysStatus keys;
    @FXML
    private TextArea scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        keys = new KeysStatus();
        configureForward();
        configureBack();
        configureLeft();
        configureRight();

        AtmegaSender sender = new AtmegaSender(keys);
        Thread t = new Thread(sender);
        t.setDaemon(true);
        t.start();

    }

    private void configureForward() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.W) {
                keys.setSlowForward();
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if (key.getCode() == KeyCode.W) {
                keys.releaseSlowForward();
            }
        });
    }

    private void configureBack() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.S) {
                keys.setBack();
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if (key.getCode() == KeyCode.S) {
                keys.releaseBack();
            }
        });
    }

    private void configureLeft() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.A) {
                keys.setLeft();
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if (key.getCode() == KeyCode.A) {
                keys.releaseLeft();
            }
        });
    }

    private void configureRight() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.D) {
                keys.setRight();
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            if (key.getCode() == KeyCode.D) {
                keys.releaseRight();
            }
        });
    }

}
