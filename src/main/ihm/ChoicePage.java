package main.ihm;

import java.io.IOException;
import java.net.URL;
import java.net.http.WebSocket.Listener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Users.Person;
import main.Users.Student;

//Gère tous les controlleurs de la page permettanr de choisir (Accepter/Refuser) les étudiants
public class ChoicePage implements Initializable {
	@FXML
	Button NextButton;
	@FXML
	ListView<Student> listeleve;
	@FXML
	Button FilterButton;
	@FXML
	CheckBox FullSelect;
	@FXML
	Label name;
	@FXML
	Label year;
	@FXML
	Label score;
	@FXML
	Label miss;
	@FXML
	Button Accept;
	@FXML
	Button Refuse;

	double[] note = new double[1];
	int idx = 0;

	//Permet de passer à la page suivante
	public void next(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("Maquette_Haute_Fidelite_Affectations.fxml");
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		Stage stage = (Stage) NextButton.getScene().getWindow();
		stage.setScene(scene);
	}

	//Ajoute les étudiant à la ListView
	public void addToList() {
		ObservableList<Student> list = FXCollections.observableArrayList();
		Student a = new Student("Martin", "Martin", "pwd", note, "1", "4");
		Student b = new Student("Dupont", "Jean", "pwd", note, "1");
		Student c = new Student("Dubois", "Pierre", "pwd", note, "3", "2");
		Student d = new Student("là", "l'autre", "pwd", note, "3");
		list.addAll(a, b, c, d);
		for (int i = 0; i < list.size(); i++) {
			listeleve.getItems().add(list.get(i));
		}

	}

	//Initialise la LisView et affiche les informations de l'étudiant sélectionné
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addToList();
		listeleve.getSelectionModel().selectedItemProperty()
				.addListener((ChangeListener<? super Student>) new ChangeListener<Student>() {
					@Override
					public void changed(ObservableValue<? extends Student> observable, Student oldValue,
							Student newValue) {
						if (!listeleve.getItems().isEmpty() && newValue != null) {
							name.setText("Nom : ");
							name.setText(name.getText() + " " + newValue.getName() + " " + newValue.getLastName());
							year.setText("Année : ");
							year.setText(year.getText() + " " + newValue.getLevel());
							score.setText("Moyenne : ");
							score.setText(score.getText() + " " + newValue.getScore()[0]);
							miss.setText("Abscences : ");
							miss.setText(miss.getText() + " " + newValue.getModifier());
							for (int i = 0; i < listeleve.getItems().size(); i++) {
								if (listeleve.getItems().get(i) == newValue) {
									idx = i;
								}
							}
						}

					}
				});
	}
	
	//Affiche le fenêtre modale des filtres
	public void toFilters(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("Maquette_Haute_Fidelite_Filtrer.fxml");
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner((Stage) FilterButton.getScene().getWindow());
		stage.show();

	}
	
	//Secltionne tous les éléments de la ListtView
	public void selectAll(ActionEvent event) {
		if (FullSelect.isSelected()) {
			listeleve.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			listeleve.getSelectionModel().selectAll();
		} else {
			listeleve.getSelectionModel().clearSelection();
			listeleve.getSelectionModel().select(0);
		}
	}

	//Accepte un étudiant et le retire de la liste
	public void toAccept(ActionEvent event) {
		if(FullSelect.isSelected()) {
			listeleve.getItems().clear();
		}else {
			listeleve.getItems().remove(idx);
		}
	}

	//Refuse un étudiant et le retire de la liste
	public void toRefuse(ActionEvent event) {
		if(FullSelect.isSelected()) {
			listeleve.getItems().clear();
		}else {
			listeleve.getItems().remove(idx);
		}
	}
}
