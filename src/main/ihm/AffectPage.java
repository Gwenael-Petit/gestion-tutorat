package main.ihm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Users.Student;

//Gère tous les controlleurs de la page permettanr d'affecter les couples tuteur/turore
public class AffectPage implements Initializable{
	@FXML
    Button TerminerButton;
	@FXML
	Button RetourButton;
	@FXML
	ListView<Student> list1;
	@FXML
	ListView<Student> list23;
	@FXML
	TableColumn<Couple, Student> col1;
	@FXML
	TableColumn<Couple, Student> col23;
	@FXML
	TableView<Couple> table;
	
	double[] note = new double[1];
	int idx = 0;
	
	ObservableList<Couple> affected = FXCollections.observableArrayList();
	
	
	Student a = new Student("Martin", "Martin", "pwd", note, "1", "4");
	Student b = new Student("Dupont", "Jean", "pwd", note, "1");
	Student c = new Student("Dubois", "Pierre", "pwd", note, "3", "2");
	Student d = new Student("là", "l'autre", "pwd", note, "3");
	
	Couple ac =  new Couple(a,c);
	Couple bd =  new Couple(b,d);
	
	//Ferme l'application
	public void next(ActionEvent event) {
		Stage stage = (Stage) TerminerButton.getScene().getWindow();
		stage.close();
	}
	
	//Retour à la page précédente
	public void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("Maquette_Haute_Fidelite_Prof1.fxml");
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();
		

		Scene scene = new Scene(root);
		Stage stage = (Stage) RetourButton.getScene().getWindow();
		stage.setScene(scene);
	}
	
	//Ajoute les étudiants aux Listview
	public void addToList() {
		ObservableList<Student> list = FXCollections.observableArrayList();
		list.addAll(a, b, c, d);
		for (int i = 0; i < list.size(); i++) {
			list1.getItems().add(list.get(i));
			list23.getItems().add(list.get(i));
		}
	}
	
	//Initialise ListView et TableView
	public void initialize(URL arg0, ResourceBundle arg1) {
		addToList();
		affected.addAll(ac, bd);
		col1.setCellValueFactory(new PropertyValueFactory<>("tuteur"));
		col23.setCellValueFactory(new PropertyValueFactory<>("tutore"));
		table.setItems(affected);
	}

}
