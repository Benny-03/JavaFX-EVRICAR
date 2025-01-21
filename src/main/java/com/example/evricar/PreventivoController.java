package com.example.evricar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PreventivoController implements Initializable {

    @FXML
    private Label nome;
    @FXML
    private Label modello;
    @FXML
    private Label marca;
    @FXML
    private Label col;
    @FXML
    private Label opt;
    @FXML
    private Label mot;
    @FXML
    private Label data_rit;
    @FXML
    private Label citta;
    @FXML
    private Label usato_mod;
    @FXML
    private Label marca_used;
    @FXML
    private Label anno_used;
    @FXML
    private Label telef;
    @FXML
    private Label mail;
    @FXML
    private Label prezzo;

    @FXML
    private StringBuilder builder = new StringBuilder();

    @FXML
    private Pane popUp_senzaUsato;
    @FXML
    private Pane popUp_conUsato;

    @FXML
    private Label sconto;
    @FXML
    private Label prezzo_scontato;


    @FXML
    private void goBack(ActionEvent event) {
        DButils.changeScene(event,"structureConfigure.fxml",null,null,null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Random rand = new Random();
        int mese = rand.nextInt(1, 12);
        int s = rand.nextInt(10, 50);
        int auto1 = rand.nextInt(1, 23);
        int auto2 = rand.nextInt(1, 23);
        int auto3 = rand.nextInt(1, 23);
        int auto4 = rand.nextInt(1, 23);
        int auto5 = rand.nextInt(1, 23);

        /*int mese = 7;
        int s = 30;
        int auto1 = 15;*/

        if(mese == StaticClass.localDate.getMonthValue()){
            if(StaticClass.idAuto == auto1 || StaticClass.idAuto == auto2 || StaticClass.idAuto == auto3 || StaticClass.idAuto == auto4 || StaticClass.idAuto == auto5){
                StaticClass.sconto = String.valueOf(s);
                StaticClass.prezzoSconto = String.valueOf(Math.round(StaticClass.prezzoFin - ((StaticClass.prezzoFin*s)/100))) + ".000";
            }
        }else{
            StaticClass.sconto = "";
            StaticClass.prezzoSconto = "";
        }

        nome.setText(StaticClass.id_name);
        modello.setText(StaticClass.modello);
        prezzo.setText(String.valueOf(StaticClass.prezzoFin));
        marca.setText(StaticClass.marca);
        col.setText(StaticClass.colore);
        opt.setText(String.valueOf(StaticClass.optional));
        mot.setText(StaticClass.engine);
        data_rit.setText(StaticClass.date);
        citta.setText(StaticClass.city);
        usato_mod.setText(StaticClass.usatoModello);
        marca_used.setText(StaticClass.marcaUsato);
        anno_used.setText(StaticClass.usatoAnno);
        telef.setText(StaticClass.telefono);
        mail.setText(StaticClass.mail);
        sconto.setText(StaticClass.sconto);
        prezzo_scontato.setText(StaticClass.prezzoSconto);
    }

    @FXML
    private void AccettaPrev(ActionEvent event) throws SQLException, IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Prev.txt"));
        bufferedWriter.write(String.valueOf(StaticClass.id_name));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.modello));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.prezzoFin));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");


        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.marca));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.colore));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.optional));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.engine));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.date));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.city));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.telefono));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.mail));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        if (StaticClass.secondHand) {
            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(StaticClass.usatoModello));
            bufferedWriter.write("£");
            bufferedWriter.write("\n");

            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(StaticClass.marcaUsato));
            bufferedWriter.write("£");
            bufferedWriter.write("\n");

            bufferedWriter.newLine();
            bufferedWriter.write(String.valueOf(StaticClass.usatoAnno));
            bufferedWriter.write("£");
            bufferedWriter.write("\n");
        }

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.sconto));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(StaticClass.prezzoSconto));
        bufferedWriter.write("£");
        bufferedWriter.write("\n");

        bufferedWriter.close();
        readFile();
        DButils.setPrev(builder);
        DButils.getIdPrev(builder);
        DButils.setLink(StaticClass.idUser, StaticClass.idPreventivo, StaticClass.secondHand, StaticClass.city);

        if(StaticClass.secondHand) {
            StaticClass.preventivoApprovato  = true;
            popUp_conUsato.setVisible(true);
            popUp_conUsato.setDisable(false);
        }else {
            StaticClass.preventivoApprovato  = false;
            popUp_senzaUsato.setVisible(true);
            popUp_senzaUsato.setDisable(false);
        }
    }

    @FXML
    private void readFile() throws FileNotFoundException, SQLException {
        try (Scanner scanner = new Scanner(new File("Prev.txt"))) {
            while (scanner.hasNextLine()) {
                builder.append(scanner.nextLine());
            }
        }
    }

    @FXML
    private void goToCatalog(ActionEvent event) {
        DButils.changeScene(event,"structureCatalog.fxml",null,null,null);
    }
}

