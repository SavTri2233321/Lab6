package com.example.lab61;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.geometry.Pos.BOTTOM_CENTER;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scenePrincipale = new Scene(new Group());
        stage.setHeight(700);
        stage.setWidth(700);
        stage.setFullScreen(true);

        //Image
        Image grenouille = new Image("file:oiseau.jpg");
        Image vache = new Image("file:chien.jpg");
        Image crocodile = new Image("file:serpent.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(grenouille);
        ColorAdjust colorAdjust = new ColorAdjust();
        imageView.setEffect(colorAdjust);

        //Barre d'information
        Label labelInfo = new Label("Initialisation de l'image");
        labelInfo.setTranslateX(5);

        //Par rapport au menu
        Menu fichier = new Menu("Fichier");
        Menu actions = new Menu("Actions");
        Menu chargerImage = new Menu("Charger Image");
        MenuItem image1= new MenuItem("grenouille");
        MenuItem image2= new MenuItem("vache");
        MenuItem image3= new MenuItem("crocodile");
        MenuItem reinitialiser = new MenuItem("reinitialiser");
        chargerImage.getItems().addAll(image1,image2,image3);
        fichier.getItems().add(chargerImage);
        actions.getItems().add(reinitialiser);
        MenuBar menuBar = new MenuBar(fichier,actions);

        //Par rapport au contexteMenu
        Menu actions1 = new Menu("Actions");
        Menu chargerImage1 = new Menu("Charger Image");
        MenuItem image11= new MenuItem("grenouille");
        MenuItem image21= new MenuItem("vache");
        MenuItem image31= new MenuItem("crocodile");
        MenuItem reinitialiser1 = new MenuItem("reinitialiser");
        actions1.getItems().add(reinitialiser1);
        chargerImage1.getItems().setAll(image11,image21,image31);
        ContextMenu contextMenu = new ContextMenu(chargerImage1,actions1);

        //Charger les images
        image1.setOnAction(actionEvent -> {
            imageView.setImage(grenouille);
            labelInfo.setText("Initialisation de l'image");
        });
        image2.setOnAction(actionEvent -> {
            imageView.setImage(vache);
            labelInfo.setText("Initialisation de l'image");
        });
        image3.setOnAction(actionEvent -> {
            imageView.setImage(crocodile);
            labelInfo.setText("Initialisation de l'image");
        });

        //Charger les images ContexteMenu
        image11.setOnAction(actionEvent -> {
            imageView.setImage(grenouille);
            labelInfo.setText("Initialisation de l'image");
        });
        image21.setOnAction(actionEvent -> {
            imageView.setImage(vache);
            labelInfo.setText("Initialisation de l'image");
        });
        image31.setOnAction(actionEvent -> {
            imageView.setImage(crocodile);
            labelInfo.setText("Initialisation de l'image");
        });


        //Slider Luminosite
        Slider sliderLumino = new Slider(-1,1,0);
        sliderLumino.setTooltip(new Tooltip("Rend l'image plus claire ou plus sombre"));
        sliderLumino.valueProperty().addListener((observable,oldValue,newValue)-> {
            colorAdjust.setBrightness((Double) newValue);
            imageView.setEffect(colorAdjust);
            labelInfo.setText("Changement de luminosité");
        });

        Slider sliderContraste = new Slider(-1,1,0);
        sliderContraste.setTooltip(new Tooltip("Diminue ou augmente la différence entre les couleurs"));
        sliderContraste.valueProperty().addListener((observable,oldValue,newValue)-> {
            colorAdjust.setContrast((Double) newValue);
            imageView.setEffect(colorAdjust);
            labelInfo.setText("Changement de contraste");
        });

        Slider sliderTeinte = new Slider(-1,1,0);
        sliderTeinte.setTooltip(new Tooltip("Change la teinte (couleur) de l'image"));
        sliderTeinte.valueProperty().addListener((observable,oldValue,newValue)-> {
            colorAdjust.setHue((Double) newValue);
            imageView.setEffect(colorAdjust);
            labelInfo.setText("Changement de teinte");
        });

        Slider sliderSaturation = new Slider(-1,1,0);
        sliderSaturation.setTooltip(new Tooltip("Diminue ou augmente l'intensité des couleurs"));
        sliderSaturation.valueProperty().addListener((observable,oldValue,newValue)-> {
            colorAdjust.setSaturation((Double) newValue);
            imageView.setEffect(colorAdjust);
            labelInfo.setText("Changement de saturation");
        });

        //Reinitialiser
        reinitialiser.setOnAction(actionEvent -> {
            sliderLumino.setValue(0);
            sliderContraste.setValue(0);
            sliderTeinte.setValue(0);
            sliderSaturation.setValue(0);
            imageView.setEffect(colorAdjust);
            labelInfo.setText("Reinitialisation de l'image");
        });

        //Reinitialiser Contexte Menu
        reinitialiser1.setOnAction(actionEvent -> {
            sliderLumino.setValue(0);
            sliderContraste.setValue(0);
            sliderTeinte.setValue(0);
            sliderSaturation.setValue(0);
            imageView.setEffect(colorAdjust);
            labelInfo.setText("Reinitialisation de l'image");
        });

        //Placement des sliders
        VBox vBox = new VBox(new Label("Luminosité"),sliderLumino,new Label("Contraste"),sliderContraste,new Label("Teinte"),sliderTeinte,new Label("Saturation"),sliderSaturation);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setSpacing(10);
        vBox.setTranslateX(-50);

        //Initialisation de la scene
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setRight(vBox);
        root.setCenter(imageView);
        root.setBottom(labelInfo);
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(backgroundFill));
        root.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(root, contextMenuEvent.getSceneX(),contextMenuEvent.getScreenY()));
        scenePrincipale.setRoot(root);
        stage.setScene(scenePrincipale);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}