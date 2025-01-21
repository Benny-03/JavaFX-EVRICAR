package com.example.evricar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;


public class ShowController implements Initializable {
    @FXML
    private Hyperlink myEvricar;

    @FXML
    private ImageView imgAuto;
    @FXML
    private ImageView imgCollage;
    @FXML
    private Image img1;
    @FXML
    private Image img2;

    @FXML
    private Label marca;
    @FXML
    private Label modello;
    @FXML
    private Label prezzo;
    @FXML
    private Label altezza;
    @FXML
    private Label lunghezza;
    @FXML
    private Label larghezza;
    @FXML
    private Label peso;
    @FXML
    private Label volume;

    @FXML
    private TextArea desc;



    @FXML
    private void back (ActionEvent event) {
        DButils.changeScene(event,"structureCatalog.fxml",null, StaticClass.id_name,null);
    }

    @FXML
    private void conf (ActionEvent event) {
        DButils.getId(event, StaticClass.id_name);
        DButils.changeScene(event,"structureConfigure.fxml",null, StaticClass.id_name,null);
    }

    @FXML
    private void log(ActionEvent event) {
        DButils.changeScene(event,"structureSignIn.fxml",null,null,null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (StaticClass.id_name!=null) {
            myEvricar.setText(StaticClass.id_name);
        }else {
            myEvricar.setText("My Evricar");
        }

        modello.setText(StaticClass.modello);
        marca.setText(StaticClass.marca);
        desc.setText(StaticClass.desc);
        lunghezza.setText(String.valueOf(StaticClass.lun));
        altezza.setText(String.valueOf(StaticClass.alt));
        larghezza.setText(String.valueOf(StaticClass.lar));
        peso.setText(String.valueOf(StaticClass.peso));
        volume.setText(String.valueOf(StaticClass.volume));
        prezzo.setText(String.valueOf(StaticClass.prezzo));

        switch (StaticClass.idAuto) {
            case 1:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Audi/AudiRS3.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Audi/Rs3Collage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 2:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Audi/AudiA5.jpeg");
                img2= new Image("file::src/main/resources/com/example/evricar/Image/Audi/A5Collage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 3:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Audi/AudiA1.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Audi/A1Collage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 4:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/LamborghiniSTO.jpeg");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/stoCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 5:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/LamborghiniHuracan.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/HuracanCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 6:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/LamborghiniAventador.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/AventadorCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 7:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/MercedesAmgGt.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/AmgGtCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 8:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/MercedesGLC.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/GlcCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;

            case 9:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/MercedesClasseA.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/Acollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 10:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Mustang/MustangMachE.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Mustang/MachECollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;

            case 12:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Opel/OpelCorsa.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Opel/CorsaCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;

            case 11:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Mustang/MustangGT500.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Mustang/Gt500Collage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 13:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Opel/OpelKarl.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Opel/KarlsCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 14:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Opel/OpelMokka.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Opel/MokkaCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 15:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/LandRover/LandRoverRangeRover.jpeg/");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/LandRover/RangeRoverCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 16:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/LandRover/LandRoverDefender.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/LandRover/DefenderCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 18:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Renault/RenaultAustral.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Renault/AustralCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 17:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/LandRover/LandRoverDiscovery.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/LandRover/DiscoveryCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 19:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Renault/RenaultClio.jpeg");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Renault/ClioCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 20:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Tesla/TeslaModelS.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Tesla/SCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 21:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Tesla/TeslaModel3.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Tesla/3Collage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 22:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Tesla/TeslaModelY.jpeg");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Tesla/YCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
            case 23:
                img1= new Image("file:src/main/resources/com/example/evricar/Image/Renault/RenaultMeganeE-tech.png");
                img2= new Image("file:src/main/resources/com/example/evricar/Image/Renault/MeganCollage.JPEG");
                imgAuto.setImage(img1);
                imgCollage.setImage(img2);
                break;
        }
    }
}
