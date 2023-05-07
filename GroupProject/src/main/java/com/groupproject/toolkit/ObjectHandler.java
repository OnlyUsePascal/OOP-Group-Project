package com.groupproject.toolkit;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;

public class ObjectHandler {
    //=========== CHECK ============
    public static boolean checkStringCharacterOnly(String str){
        // System.out.println(str);
        return str.matches("[a-zA-Z ]+");
    }

    public static boolean checkStringNumberOnly(String str){
        // System.out.println(str);
        return str.matches("[0-9]+");
    }

    public static boolean checkStringGeneral(String str){
        // System.out.println(str);
        return !str.matches(".*\\|.*") && str.length() != 0;
    }

    //=========== GET ============
    public static AnchorPane getAnchorPane(String url){
        FXMLLoader fxmlLoader = new FXMLLoader(ObjectHandler.class.getResource(url));
        try{
            AnchorPane result = (AnchorPane) fxmlLoader.load();
            return result;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    public static FXMLLoader getFXMLLoader(String url){
        FXMLLoader fxmlLoader = new FXMLLoader(ObjectHandler.class.getResource(url));
        try {
            fxmlLoader.load();
            return fxmlLoader;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    public static File getFile(String url){
        System.out.println(url);
        File file = new File(url);
        if (file.exists()) {
            System.out.println("File found");
            return file;
        }
        System.out.println("File not found:");
        return null;
    }

    public static Image getImage(String url){
        try {
            Image img = new Image(ObjectHandler.class.getResourceAsStream(PathHandler.getMediaImage(url)));
            return img;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    public static double getDoubleRound(double num){
        return Math.round(num * 100.0) / 100.0;
    }

    //======= SET ==========
    static public void setAnchorPane(AnchorPane frame, Node node){
        // if (true) {
        if (frame.getChildren().size() == 0) {
            frame.getChildren().add(node);
        } else {
            frame.getChildren().set(0, node);
        }
    }

    static public void setAnchorPane(AnchorPane frame, String url){
        FXMLLoader fxmlLoader = new FXMLLoader(ObjectHandler.class.getResource(url));
        try {
            AnchorPane node = (AnchorPane) fxmlLoader.load();
            setAnchorPane(frame, node);
        } catch (Exception err){
            err.printStackTrace();
        }
    }

    static public void setScene(Scene scene, String url){
        FXMLLoader loader = new FXMLLoader(ObjectHandler.class.getResource(url));
        try {
            scene.setRoot(loader.load());
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    static public void setScrollLock(ScrollPane scrollPane){
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
    }
}