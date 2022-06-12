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
import main.Users.Level;
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

	double[] notea = new double[]{15.5};
	double[] noteb = new double[]{18};
	double[] notec = new double[]{10};
	double[] noted = new double[]{5};
	int idx = 0;
	
	ObservableList<Student> list = FXCollections.observableArrayList();
	Student a = new Student("Martin", "Martin", "pwd", notea, "1", "4");
	Student b = new Student("Dupont", "Jean", "pwd", noteb, "1");
	Student c = new Student("Dubois", "Pierre", "pwd", notea, "3", "2");
	Student d = new Student("Boulanger", "Pierre", "pwd", noteb, "3");
	Student e = new Student("Petit", "Charles", "pwd", notec, "3");
	Student f = new Student("Leroy", "Lucas", "pwd", noted, "3", "1");
	Student g = new Student("Dumoulin", "Mateo", "pwd", notec, "1");
	Student h = new Student("Legrand", "Louis", "pwd", noted, "1");
	ObservableList<Student> tmp = FXCollections.observableArrayList(a,b,c,d,e,f,g,h);
	
	public static ObservableList<Student> accepted = FXCollections.observableArrayList();

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
		if(FilterPage.choice == 0) {
			if(FilterPage.crit == 0) {
				list.addAll(tmp);
			}else if(FilterPage.crit == 1) {
				if(FilterPage.op == 1) {
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getScore()[0] > FilterPage.value) {
							list.add(tmp.get(i));
						}
					}
				}else{
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getScore()[0] < FilterPage.value) {
							list.add(tmp.get(i));
						}
					}
				}
			}else {
				if(FilterPage.op == 1) {
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getModifier() > FilterPage.value) {
							list.add(tmp.get(i));
						}
					}
				}else{
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getModifier() < FilterPage.value) {
							list.add(tmp.get(i));
						}
					}
				}
				
			}
			for (int i = 0; i < list.size(); i++) {
				listeleve.getItems().add(list.get(i));
			}
		}else if(FilterPage.choice == 1) {
			if(FilterPage.crit == 0) {
				list.addAll(c, d, e, f);
			}else if(FilterPage.crit == 1) {
				if(FilterPage.op == 1) {
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getScore()[0] > FilterPage.value && tmp.get(i).getLevel() == Level.THIRD) {
							list.add(tmp.get(i));
						}
					}
				}else{
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getScore()[0] < FilterPage.value && tmp.get(i).getLevel() == Level.THIRD) {
							list.add(tmp.get(i));
						}
					}
				}
			}else {
				if(FilterPage.op == 1) {
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getModifier() > FilterPage.value && tmp.get(i).getLevel() == Level.THIRD) {
							list.add(tmp.get(i));
						}
					}
				}else{
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getModifier() < FilterPage.value && tmp.get(i).getLevel() == Level.THIRD) {
							list.add(tmp.get(i));
						}
					}
				}
				
			}
			for (int i = 0; i < list.size(); i++) {
				listeleve.getItems().add(list.get(i));
			}
		}else {
			if(FilterPage.crit == 0) {
				list.addAll(a, b, g, h);
			}else if(FilterPage.crit == 1) {
				if(FilterPage.op == 1) {
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getScore()[0] > FilterPage.value && tmp.get(i).getLevel() == Level.FIRST) {
							list.add(tmp.get(i));
						}
					}
				}else{
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getScore()[0] < FilterPage.value && tmp.get(i).getLevel() == Level.FIRST) {
							list.add(tmp.get(i));
						}
					}
				}
			}else {
				if(FilterPage.op == 1) {
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getModifier() > FilterPage.value && tmp.get(i).getLevel() == Level.FIRST) {
							list.add(tmp.get(i));
						}
					}
				}else{
					for(int i = 0; i < tmp.size(); i++) {
						if(tmp.get(i).getModifier() < FilterPage.value && tmp.get(i).getLevel() == Level.FIRST) {
							list.add(tmp.get(i));
						}
					}
				}
				
			}
			for (int i = 0; i < list.size(); i++) {
				listeleve.getItems().add(list.get(i));
			}
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
			accepted.addAll(listeleve.getItems());
			listeleve.getItems().clear();
		}else {
			accepted.add(listeleve.getItems().get(idx));
			listeleve.getItems().remove(idx);
			list.remove(idx);
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
