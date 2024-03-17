package com.example.a4issa;

import Console.Domain.Inchiriere;
import Console.Domain.Masina;
import Console.Exceptions.DateIntervalsValidator;
import Console.Exceptions.DateValidator;
import Console.Exceptions.DoesntExistException;
import Console.Exceptions.DuplicateEntityException;
import Console.Repository.BDInchiriereRepo;
import Console.Repository.BDMasinaRepo;
import Console.Repository.IRepo;
import Console.Repository.MemoryRepo;
import Console.Service.ServiceInchirieri;
import Console.Service.ServiceMasini;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        IRepo<Masina> repoMasini = new BDMasinaRepo();
        ServiceMasini serviceMasini = new ServiceMasini(repoMasini);
        IRepo<Inchiriere> repoInchirieri = new BDInchiriereRepo();
        ServiceInchirieri serviceInchirieri = new ServiceInchirieri(repoInchirieri,repoMasini);
        /*try {
            serviceMasini.adaugaMasina(1,"vw","golf");
            serviceMasini.adaugaMasina(2,"mazda","miata");
            serviceInchirieri.adaugaInchiriere(1,1,"11-02-2021","23-03-2021");
            serviceInchirieri.adaugaInchiriere(2,1,"5-04-2023","9-04-2023");
            serviceInchirieri.adaugaInchiriere(3,2,"22-02-2021","16-10-2022");
        } catch (Exception e) {}*/

        HBox mainHorizontalBox = new HBox(); //fereastra principala a aplicatiei, aici afisam tot

        VBox masiniVerticalBox = new VBox(); //partea din stanga pentru masini
        masiniVerticalBox.setPadding(new Insets(20));

        VBox inchirieriVerticalBox = new VBox(); //partea din dreapta pentru inchirieri
        inchirieriVerticalBox.setPadding(new Insets(20));

        mainHorizontalBox.getChildren().add(masiniVerticalBox);
        mainHorizontalBox.getChildren().add(inchirieriVerticalBox);




        //PENTRU PARTEA DE STATISTICI

        VBox statisticiVerticalBox = new VBox(); //partea din stanga pentru masini
        statisticiVerticalBox.setPadding(new Insets(10));

        //Prima statistica
        Label pb1Label = new Label("Nr. de inchirieri al fiecarei masini");
        ListView<String> pb1ListView = new ListView<>();
        ObservableList<String> inchirieriPerMasina = FXCollections.observableArrayList(serviceInchirieri.pb1());
        pb1ListView.setItems(inchirieriPerMasina);
        statisticiVerticalBox.getChildren().add(pb1Label);
        statisticiVerticalBox.getChildren().add(pb1ListView);

        //A doua statistica
        Label pb2Label = new Label("Nr. de inchirieri pentru fiecare luna");
        ListView<String> pb2ListView = new ListView<>();
        ObservableList<String> inchirieriPerLuna = FXCollections.observableArrayList(serviceInchirieri.pb2());
        pb2ListView.setItems(inchirieriPerLuna);
        statisticiVerticalBox.getChildren().add(pb2Label);
        statisticiVerticalBox.getChildren().add(pb2ListView);

        //A treia statistica
        Label pb3Label = new Label("Nr. de zile de inchiriere pt. fiecare masina");
        ListView<String> pb3ListView = new ListView<>();
        ObservableList<String> masiniNrZile = FXCollections.observableArrayList(serviceInchirieri.pb3());
        pb3ListView.setItems(masiniNrZile);
        statisticiVerticalBox.getChildren().add(pb3Label);
        statisticiVerticalBox.getChildren().add(pb3ListView);

        mainHorizontalBox.getChildren().add(statisticiVerticalBox);



        //PENTRU PARTEA DE INCHIRIERI
        //Prima parte: lista inchirierilor
        ListView<Inchiriere> inchirieriListView = new ListView<>();
        ObservableList<Inchiriere> inchirieri = FXCollections.observableArrayList(serviceInchirieri.getInchirieri());
        inchirieriListView.setItems(inchirieri);
        //inchirieriListView.setPadding(new Insets(0,0,0,10));
        inchirieriVerticalBox.getChildren().add(inchirieriListView);

        //PENTRU PARTEA DE MASINI
        //Prima parte: lista masinilor
        ListView<Masina> masiniListView = new ListView<>();
        ObservableList<Masina> masini = FXCollections.observableArrayList(serviceMasini.getMasini());
        masiniListView.setItems(masini);
        masiniVerticalBox.getChildren().add(masiniListView);

        //PENTRU PARTEA DE MASINI

        //A doua parte: inserare masini
        GridPane masiniGridPane = new GridPane();

        Label idLabel = new Label("id masina");
        idLabel.setPadding(new Insets(0 ,10,0,0));
        TextField idTextField = new TextField();
        Label marcaLabel = new Label("marca masina");
        marcaLabel.setPadding(new Insets(0 ,10,0,0));
        TextField marcaTextField = new TextField();
        Label modelLabel = new Label("model masina");
        modelLabel.setPadding(new Insets(0 ,10,0,0));
        TextField modelTextField = new TextField();

        masiniGridPane.add(idLabel,0,0);
        masiniGridPane.add(idTextField,1,0);
        masiniGridPane.add(marcaLabel,0,1);
        masiniGridPane.add(marcaTextField,1,1);
        masiniGridPane.add(modelLabel, 0,2);
        masiniGridPane.add(modelTextField,1,2);

        masiniVerticalBox.getChildren().add(masiniGridPane);

        masiniListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Masina masina = masiniListView.getSelectionModel().getSelectedItem();
                if(masina != null) {
                    idTextField.setText(Integer.toString(masina.getId()));
                    marcaTextField.setText(masina.getMarca());
                    modelTextField.setText(masina.getModel());
                }
            }
        });

        //A treia parte: butoanele pentru functionalitati
        HBox masiniHorizontalBox = new HBox();

        Button adaugaMasinaButton = new Button("adauga");
        Button modificaMasinaButton = new Button("modifica");
        Button stergeMasinaButton = new Button("sterge");

        masiniHorizontalBox.getChildren().add(adaugaMasinaButton);
        masiniHorizontalBox.getChildren().add(modificaMasinaButton);
        masiniHorizontalBox.getChildren().add(stergeMasinaButton);

        adaugaMasinaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String marca = marcaTextField.getText();
                    String model = modelTextField.getText();
                    serviceMasini.adaugaMasina(id,marca,model);
                    masini.setAll(serviceMasini.getMasini());
                    inchirieriPerMasina.setAll(serviceInchirieri.pb1());
                    inchirieriPerLuna.setAll(serviceInchirieri.pb2());
                    masiniNrZile.setAll(serviceInchirieri.pb3());
                }
                catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        modificaMasinaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    int id = Integer.parseInt(idTextField.getText());
                    String marca = marcaTextField.getText();
                    String model = modelTextField.getText();
                    serviceMasini.modificaMasina(id,marca,model);
                    masini.setAll(serviceMasini.getMasini());
                    inchirieriPerMasina.setAll(serviceInchirieri.pb1());
                    inchirieriPerLuna.setAll(serviceInchirieri.pb2());
                    masiniNrZile.setAll(serviceInchirieri.pb3());
                }
                catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        stergeMasinaButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    if(serviceMasini.nrMasini()==1) throw new DoesntExistException("Trebuie sa existe cel putin o masina in lista.");
                    int id = Integer.parseInt(idTextField.getText());
                    //String marca = marcaTextField.getText();
                    //String model = modelTextField.getText();
                    if(serviceInchirieri.getMasina(id) != null)
                        serviceInchirieri.stergeMasina(id);
                    serviceMasini.stergeMasina(id);
                    masini.setAll(serviceMasini.getMasini());
                    inchirieri.setAll(serviceInchirieri.getInchirieri());
                    inchirieriPerMasina.setAll(serviceInchirieri.pb1());
                    inchirieriPerLuna.setAll(serviceInchirieri.pb2());
                    masiniNrZile.setAll(serviceInchirieri.pb3());
                }
                catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        masiniVerticalBox.getChildren().add(masiniHorizontalBox);


        //PENTRU PARTEA DE INCHIRIERI

        //A doua parte: inserare inchirieri
        GridPane inchirieriGridPane = new GridPane();

        Label idInchiriereLabel = new Label("id inchiriere");
        idInchiriereLabel.setPadding(new Insets(0 ,10,0,0));
        TextField idInchiriereTextField = new TextField();
        Label idMasinaLabel = new Label("id masina");
        idMasinaLabel.setPadding(new Insets(0 ,10,0,0));
        TextField idMasinaTextField = new TextField();
        Label dataiLabel = new Label("data inceput");
        dataiLabel.setPadding(new Insets(0 ,10,0,0));
        TextField dataiTextField = new TextField();
        Label datafLabel = new Label("data sfarsit");
        datafLabel.setPadding(new Insets(0 ,10,0,0));
        TextField datafTextField = new TextField();

        inchirieriGridPane.add(idInchiriereLabel,0,0);
        inchirieriGridPane.add(idInchiriereTextField,1,0);

        inchirieriGridPane.add(idMasinaLabel,0,1);
        inchirieriGridPane.add(idMasinaTextField,1,1);
        inchirieriGridPane.add(dataiLabel, 0,2);
        inchirieriGridPane.add(dataiTextField,1,2);
        inchirieriGridPane.add(datafLabel, 0,3);
        inchirieriGridPane.add(datafTextField,1,3);

        inchirieriVerticalBox.getChildren().add(inchirieriGridPane);

        inchirieriListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Inchiriere inchiriere = inchirieriListView.getSelectionModel().getSelectedItem();
                if(inchiriere != null) {
                    idInchiriereTextField.setText(Integer.toString(inchiriere.getId()));
                    idMasinaTextField.setText(Integer.toString(inchiriere.getIdMasina()));
                    dataiTextField.setText(inchiriere.getData_inceput());
                    datafTextField.setText(inchiriere.getData_sfarsit());
                }
            }
        });

        //A treia parte: butoanele pentru functionalitati
        HBox inchirieriHorizontalBox = new HBox();

        Button adaugaInchiriereButton = new Button("adauga");
        Button modificaInchiriereButton = new Button("modifica");
        Button stergeInchiriereButton = new Button("sterge");

        inchirieriHorizontalBox.getChildren().add(adaugaInchiriereButton);
        inchirieriHorizontalBox.getChildren().add(modificaInchiriereButton);
        inchirieriHorizontalBox.getChildren().add(stergeInchiriereButton);

        adaugaInchiriereButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    int id = Integer.parseInt(idInchiriereTextField.getText());
                    int idm = Integer.parseInt(idMasinaTextField.getText());
                    String datai = dataiTextField.getText();
                    String dataf = datafTextField.getText();
                    DateValidator dateValidator = new DateValidator();
                    DateIntervalsValidator dateIntervalsValidator = new DateIntervalsValidator();
                    dateValidator.verifData(datai);
                    dateValidator.verifData(dataf);
                    dateIntervalsValidator.verifDateIntervalsValidator(datai,dataf);
                    serviceInchirieri.adaugaInchiriere(id,idm,datai,dataf);
                    inchirieri.setAll(serviceInchirieri.getInchirieri());
                    inchirieriPerMasina.setAll(serviceInchirieri.pb1());
                    inchirieriPerLuna.setAll(serviceInchirieri.pb2());
                    masiniNrZile.setAll(serviceInchirieri.pb3());

                }
                catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        modificaInchiriereButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    int id = Integer.parseInt(idInchiriereTextField.getText());
                    int idm = Integer.parseInt(idMasinaTextField.getText());
                    String datai = dataiTextField.getText();
                    String dataf = datafTextField.getText();
                    DateValidator dateValidator = new DateValidator();
                    DateIntervalsValidator dateIntervalsValidator = new DateIntervalsValidator();
                    dateValidator.verifData(datai);
                    dateValidator.verifData(dataf);
                    dateIntervalsValidator.verifDateIntervalsValidator(datai,dataf);
                    serviceInchirieri.modificaInchiriere(id,idm,datai,dataf);
                    inchirieri.setAll(serviceInchirieri.getInchirieri());
                    inchirieriPerMasina.setAll(serviceInchirieri.pb1());
                    inchirieriPerLuna.setAll(serviceInchirieri.pb2());
                    masiniNrZile.setAll(serviceInchirieri.pb3());
                }
                catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });

        stergeInchiriereButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    if(serviceInchirieri.nrInchirieri()==1) throw new DoesntExistException("Trebuie sa existe cel putin o masina in lista.");
                    int id = Integer.parseInt(idInchiriereTextField.getText());
                    serviceInchirieri.stergeInchiriere(id);
                    inchirieri.setAll(serviceInchirieri.getInchirieri());
                    inchirieriPerMasina.setAll(serviceInchirieri.pb1());
                    inchirieriPerLuna.setAll(serviceInchirieri.pb2());
                    masiniNrZile.setAll(serviceInchirieri.pb3());
                }
                catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
            }
        });


        inchirieriVerticalBox.getChildren().add(inchirieriHorizontalBox);
        Scene scene = new Scene(mainHorizontalBox, 920, 440);
        stage.setTitle("Inchirieri masini");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}