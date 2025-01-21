package com.example.evricar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.event.EventHandler;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {
    @FXML
    private Label prezzo;

    @FXML
    private ImageView image;
    @FXML
    private ImageView Xnero;
    @FXML
    private ImageView Xbianco;
    @FXML
    private ImageView Xrosso;
    @FXML
    private ImageView Xblu;
    @FXML
    private ImageView Xverde;
    @FXML
    private ImageView Xarancione;
    @FXML
    private ImageView Xgiallo;
    @FXML
    private ImageView imageUsato;
    @FXML
    private ImageView esci;

    @FXML
    private Image img;

    @FXML
    private Button nero;
    @FXML
    private Button bianco;
    @FXML
    private Button rosso;
    @FXML
    private Button blu;
    @FXML
    private Button verde;
    @FXML
    private Button giallo;
    @FXML
    private Button arancione;

    @FXML
    private RadioButton s_benzina;
    @FXML
    private RadioButton s_elettrica;
    @FXML
    private RadioButton s_gasolio;
    @FXML
    private RadioButton s_ibrida;
    @FXML
    private RadioButton s_ibridaPlugIn;

    @FXML
    private CheckBox interniPelle;
    @FXML
    private CheckBox vetriOscurati;
    @FXML
    private CheckBox telecPost;
    @FXML
    private CheckBox cambioAuto;
    @FXML
    private CheckBox sensParcheggio;
    @FXML
    private CheckBox extra5;
    @FXML
    private CheckBox extra4;
    @FXML
    private CheckBox extra3;
    @FXML
    private CheckBox extra2;
    @FXML
    private CheckBox extra1;

    @FXML
    private Text text_usato;

    @FXML
    private Pane pane_usato;
    @FXML
    private Pane popUp;

    @FXML
    private TextField marca_usato;
    @FXML
    private TextField modello_usato;
    @FXML
    private TextField anno_usato;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane anchorPane;

    private String currentPrice;

    @FXML
    private ChoiceBox<String> luogo;

    private int currentMotore;
    private float appo;

    public ObservableList<String> list = FXCollections.observableArrayList();

    private final String[] city = {"Verona", "Bologna", "Milano", "Roma", "Torino", "Napoli", "Bari"};
    private String [] divide;

    private int c = 0;

    @FXML
    private void goBack(ActionEvent actionEvent) throws IOException {
        DButils.changeScene(actionEvent, "structureShow.fxml", null, null, null);
    }

    @FXML
    private void askPrev(ActionEvent actionEvent) throws IOException, SQLException {
        LocalDate myDate = LocalDate.now();
        LocalDate modifiedDate = myDate.plusMonths(1);

        for(int i = 0; i < list.size(); i++){
            modifiedDate = modifiedDate.plusDays(10);
        }

        StaticClass.localDate = modifiedDate;
        StaticClass.date = modifiedDate.toString();
        System.out.println(modifiedDate);

        StaticClass.optional = list;
        StaticClass.telefono = tel.getText();
        StaticClass.mail = email.getText();
        StaticClass.prezzoFin = Float.parseFloat(currentPrice);
        StaticClass.city = luogo.getValue();

        if (StaticClass.secondHand) {
            StaticClass.marcaUsato = marca_usato.getText();
            StaticClass.usatoModello = modello_usato.getText();
            StaticClass.usatoAnno = anno_usato.getText();
        }

        if (StaticClass.id_name != null) {
            System.out.println("Going to preventivo  page");
            DButils.changeScene(actionEvent, "structurePreventivo.fxml", null, null, null);
        } else {
            DButils.changeScene(actionEvent, "structureSignIn.fxml", null, null, null);
            System.out.println("Non sei loggato ti devi loggare");
        }
    }

    @FXML
    private void setAccedi(ActionEvent actionEvent) {
        DButils.changeScene(actionEvent, "structureSignIn.fxml", null, null, null);
    }

    @FXML
    private void askUsato(ActionEvent actionEvent) {
        if (StaticClass.id_name != null) {
            pane_usato.setVisible(true);
            text_usato.setVisible(true);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            anchorPane.setPrefHeight(1250);
        } else {
            popUp.setVisible(true);
            popUp.setDisable(false);
        }
    }

    @FXML
    private void DragAndDrop(DragEvent dragEvent) throws FileNotFoundException {
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasImage() || dragboard.hasFiles()) {
            try {
                StaticClass.secondHand = true;
                imageUsato.setImage(new Image(new FileInputStream(dragboard.getFiles().get(0))));
            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            }
        } else {
            StaticClass.secondHand = false;
        }
    }

    @FXML
    private void ViewImageDrag(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        if (dragboard.hasImage() || dragboard.hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.COPY);
        }
        dragEvent.consume();
    }

    @FXML
    private void removeImage(ActionEvent actionEvent) {
        imageUsato.setImage(null);
    }

    private void NotVisibleColor() {
        Xnero.setVisible(false);
        Xbianco.setVisible(false);
        Xrosso.setVisible(false);
        Xblu.setVisible(false);
        Xarancione.setVisible(false);
        Xgiallo.setVisible(false);
        Xverde.setVisible(false);
    }

    private void Prezzo(float colore, float prezzoMotore, float optional) {
        currentPrice = String.valueOf(Float.parseFloat(currentPrice) + colore + prezzoMotore + optional);
        prezzo.setText(currentPrice);
    }

    private void Motore(int num) {
        switch (num) {
            case 1:
                appo = 0;
                break;
            case 2:
                appo = 1;
                break;
            case 3:
                appo = 5;
                break;
            case 4:
                appo = 3;
                break;
            case 5:
                appo = 2;
                break;
        }
        Prezzo(0, -1 * appo, 0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prezzo.setText(String.valueOf(StaticClass.prezzo));
        currentPrice = String.valueOf(StaticClass.prezzo);

        DButils.getExtraOptional(StaticClass.idAuto);

        if (StaticClass.lista.isEmpty()) {
            System.out.println("VUOTO");
        } else {
            divide = StaticClass.lista.split("£");
            for (int i = 0; i < divide.length; i++) {
                switch (i) {
                    case 0:
                        extra1.setText(divide[i] + " (1000 € )");
                        break;
                    case 1:
                        extra2.setText(divide[i] + " (1000 € )");
                        break;
                    case 2:
                        extra3.setText(divide[i] + " (1000 € )");
                        break;
                    case 3:
                        extra4.setText(divide[i] + " (1000 € )");
                        break;
                    case 4:
                        extra5.setText(divide[i] + " (1000 € )");
                        break;
                }
            }
        }

        if (extra1.getText().equals("CheckBox")) {
            extra1.setVisible(false);
        } else {
            extra1.setVisible(true);
        }
        if (extra2.getText().equals("CheckBox")) {
            extra2.setVisible(false);
        } else {
            extra2.setVisible(true);
        }
        if (extra3.getText().equals("CheckBox")) {
            extra3.setVisible(false);
        } else {
            extra3.setVisible(true);
        }
        if (extra4.getText().equals("CheckBox")) {
            extra4.setVisible(false);
        } else {
            extra4.setVisible(true);
        }
        if (extra5.getText().equals("CheckBox")) {
            extra5.setVisible(false);
        } else {
            extra5.setVisible(true);
        }

        nero.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.colore = "Nero";
                NotVisibleColor();
                Xnero.setVisible(true);
                if (c == 0) {
                    Prezzo(0, 0, 0);
                } else {
                    Prezzo(-1, 0, 0);
                }
            }
        });
        bianco.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.colore = "Bianco";
                NotVisibleColor();
                Xbianco.setVisible(true);
                if (c == 0) {
                    Prezzo(0, 0, 0);
                } else {
                    Prezzo(-1, 0, 0);
                    c = 0;
                }
            }
        });
        rosso.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.colore = "Rosso";
                NotVisibleColor();
                Xrosso.setVisible(true);
                if (c == 0) {
                    Prezzo(1, 0, 0);
                    c = 1;
                }else {
                    Prezzo(0, 0, 0);
                }

            }
        });

        blu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.colore = "Blu";
                NotVisibleColor();
                Xblu.setVisible(true);
                if (c == 0) {
                    Prezzo(1, 0, 0);
                    c = 1;
                }else {
                    Prezzo(0, 0, 0);
                }
            }
        });
        arancione.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.colore = "Arancione";
                NotVisibleColor();
                Xarancione.setVisible(true);
                if (c == 0) {
                    Prezzo(1, 0, 0);
                    c = 1;
                }else {
                    Prezzo(0, 0, 0);
                }
            }
        });
        giallo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.colore = "Giallo";
                NotVisibleColor();
                Xgiallo.setVisible(true);
                if (c == 0) {
                    Prezzo(1, 0, 0);
                    c = 1;
                }else {
                    Prezzo(0, 0, 0);
                }
            }
        });
        verde.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.colore = "Verde";
                NotVisibleColor();
                Xverde.setVisible(true);
                if (c == 0) {
                    Prezzo(1, 0, 0);
                    c = 1;
                }else {
                    Prezzo(0, 0, 0);
                }
            }
        });

        esci.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                popUp.setVisible(false);
                popUp.setDisable(true);
            }
        });


        ToggleGroup toggleGroup = new ToggleGroup();
        s_benzina.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.engine = "Benzina";
                s_benzina.setToggleGroup(toggleGroup);
                Motore(currentMotore);
                Prezzo(0, 0, 0);
                currentMotore = 1;
            }
        });

        s_gasolio.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.engine = "Gasolio";
                s_gasolio.setToggleGroup(toggleGroup);
                Motore(currentMotore);
                Prezzo(0, 1, 0);
                currentMotore = 2;

            }
        });

        s_elettrica.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.engine = "Elettrica";
                s_elettrica.setToggleGroup(toggleGroup);
                Motore(currentMotore);
                Prezzo(0, 5, 0);
                currentMotore = 3;

            }
        });
        s_ibrida.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.engine = "Ibrida";
                s_ibrida.setToggleGroup(toggleGroup);
                Motore(currentMotore);
                Prezzo(0, 3, 0);
                currentMotore = 4;

            }
        });
        s_ibridaPlugIn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StaticClass.engine = "IbridaPlugIn";
                s_ibridaPlugIn.setToggleGroup(toggleGroup);
                Motore(currentMotore);
                Prezzo(0, 2, 0);
                currentMotore = 5;
            }
        });

        luogo.getItems().setAll(city);
        System.out.println(luogo.getValue());

        switch (StaticClass.idAuto) {
            case 1:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Audi/AudiRS3.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 2:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Audi/AudiA5.jpeg");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 3:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Audi/AudiA1.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 4:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/LamborghiniSTO.jpeg");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                s_gasolio.setDisable(true);
                break;
            case 5:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/LamborghiniHuracan.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                s_gasolio.setDisable(true);
                break;
            case 6:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Lamborghini/LamborghiniAventador.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                s_gasolio.setDisable(true);
                break;
            case 7:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/MercedesAmgGt.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 8:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/MercedesGLC.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;

            case 9:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Mercedes/MercedesClasseA.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 10:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Mustang/MustangMachE.png");
                image.setImage(img);
                s_gasolio.setDisable(true);
                s_benzina.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 12:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Opel/OpelCorsa.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 11:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Mustang/MustangGT500.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 13:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Opel/OpelKarl.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 14:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Opel/OpelMokka.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 15:
                img = new Image("file:src/main/resources/com/example/evricar/Image/LandRover/RangeRoverFoto.jpeg/");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 16:
                img = new Image("file:src/main/resources/com/example/evricar/Image/LandRover/LandRoverDefender.png");
                image.setImage(img);
                s_gasolio.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 18:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Renault/RenaultAustral.png");
                image.setImage(img);
                s_benzina.setDisable(true);
                s_ibrida.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 17:
                img = new Image("file:src/main/resources/com/example/evricar/Image/LandRover/LandRoverDiscovery.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_gasolio.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 19:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Renault/RenaultClio.jpeg");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_elettrica.setDisable(true);
                break;
            case 20:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Tesla/TeslaModelS.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_benzina.setDisable(true);
                s_gasolio.setDisable(true);
                break;
            case 21:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Tesla/TeslaModel3.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_benzina.setDisable(true);
                s_gasolio.setDisable(true);
                break;
            case 22:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Tesla/TeslaModelY.jpeg");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_ibridaPlugIn.setDisable(true);
                s_benzina.setDisable(true);
                s_gasolio.setDisable(true);
                break;
            case 23:
                img = new Image("file:src/main/resources/com/example/evricar/Image/Renault/RenaultMeganeE-tech.png");
                image.setImage(img);
                s_ibrida.setDisable(true);
                s_benzina.setDisable(true);
                s_gasolio.setDisable(true);
                break;
        }
    }

    @FXML
    private void Pelle(ActionEvent event) {
        if (interniPelle.isSelected()) {
            list.add("Interni in pelle\n");
            Prezzo(0, 0, 1);
        } else {
            list.remove("Interni in pelle\n");
            Prezzo(0, 0, -1);
        }
    }

    @FXML
    private void Vetri(ActionEvent event) {
        if (vetriOscurati.isSelected()) {
            list.add("Vetri Oscurati\n");
            Prezzo(0, 0, 2);
        } else {
            list.remove("Vetri Oscurati\n");
            Prezzo(0, 0, -2);
        }
    }

    @FXML
    private void Telecamera(ActionEvent event) {
        if (telecPost.isSelected()) {
            list.add("Retrocamera\n");
            Prezzo(0, 0, 3);
        } else {
            list.remove("Retrocamera\n");
            Prezzo(0, 0, -3);
        }
    }

    @FXML
    private void Cambio(ActionEvent event) {
        if (cambioAuto.isSelected()) {
            list.add("Cambio Automatico\n");
            Prezzo(0, 0, 4);
        } else {
            list.remove("Cambio Automatico\n");
            Prezzo(0, 0, -4);
        }
    }

    @FXML
    private void Sensori(ActionEvent event) {
        if (sensParcheggio.isSelected()) {
            list.add("Sensori\n");
            Prezzo(0, 0, 5);
        } else {
            list.remove("Sensori\n");
            Prezzo(0, 0, -5);
        }
    }

    @FXML
    private void getChoice(MouseEvent mouseEvent) {
        System.out.println(luogo.getValue());
    }

    @FXML
    private void setPrice1(ActionEvent actionEvent) {
        if (extra1.isSelected()) {
            list.add(divide[0]);
            Prezzo(0, 0, 1);
        } else {
            list.remove(divide[0]);
            Prezzo(0, 0, -1);
        }
    }
    @FXML
    private void setPrice2(ActionEvent actionEvent) {
        if (extra2.isSelected()) {
            list.add(divide[1]);
            Prezzo(0, 0, 1);
        } else {
            list.remove(divide[1]);
            Prezzo(0, 0, -1);
        }
    }
    @FXML
    private void setPrice3(ActionEvent actionEvent) {
        if (extra3.isSelected()) {
            list.add(divide[2]);
            Prezzo(0, 0, 1);
        } else {
            list.remove(divide[2]);
            Prezzo(0, 0, -1);
        }
    }
    @FXML
    private void setPrice4(ActionEvent actionEvent) {
        if (extra4.isSelected()) {
            list.add(divide[3]);
            Prezzo(0, 0, 1);
        } else {
            list.remove(divide[3]);
            Prezzo(0, 0, -1);
        }
    }
    @FXML
    private void setPrice5(ActionEvent actionEvent) {
        if (extra5.isSelected()) {
            list.add(divide[4]);
            Prezzo(0, 0, 1);
        } else {
            list.remove(divide[4]);
            Prezzo(0, 0, -1);
        }
    }
}