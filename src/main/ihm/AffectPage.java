package main.ihm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Subject;
import main.Users.Level;
import main.Users.Student;
import main.Users.Tutor;
import main.Users.Tutored;
import main.Util.Graph;

//Gère tous les controlleurs de la page permettanr d'affecter les couples tuteur/turore
public class AffectPage implements Initializable {
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
	@FXML
	Button AutoAffButton;
	@FXML
	Button ManuAffButton;
	@FXML
	TableColumn<CritChoice, Boolean> check;
	@FXML
	TableColumn<CritChoice, String> critName;
	@FXML
	TableView<CritChoice> critTable;

	double[] note = new double[1];
	int idx = 0;

	ObservableList<Couple> affected = FXCollections.observableArrayList();
	ObservableList<Student> accepted = ChoicePage.accepted;
	ArrayList<Tutored> tutore = new ArrayList<>();
	ArrayList<Tutor> tutor = new ArrayList<>();
	Subject mat = new Subject(100, "matière", 0);
	ArrayList<Subject> subjects = new ArrayList<>();
	Map<Subject, Tutor> m1 = new HashMap<>();
	Map<Subject, ArrayList<Tutored>> m2 = new HashMap<>();

	Student a = new Student("Martin", "Martin", "pwd", note, "1", "4");
	Student b = new Student("Dupont", "Jean", "pwd", note, "1");
	Student c = new Student("Dubois", "Pierre", "pwd", note, "3", "2");
	Student d = new Student("là", "l'autre", "pwd", note, "3");

	Couple ac = new Couple(a, c);
	Couple bd = new Couple(b, d);

	// Ferme l'application
	public void next(ActionEvent event) {
		Stage stage = (Stage) TerminerButton.getScene().getWindow();
		stage.close();
	}

	// Retour à la page précédente
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

	// Lance l'affectation automatique
	public void autoAffect(ActionEvent event) {
		addToTable();
	}

	// Lance l'affectation manuelle
	public void manuAffect(ActionEvent event) {
		int i = list1.getSelectionModel().getSelectedIndex();
		int j = list23.getSelectionModel().getSelectedIndex();
		if (i != -1 && j != -1) {
			Couple tmp = new Couple(list1.getItems().get(i), list23.getItems().get(j));
			affected.add(tmp);
			col1.setCellValueFactory(new PropertyValueFactory<>("tuteur"));
			col23.setCellValueFactory(new PropertyValueFactory<>("tutore"));
			table.setItems(affected);
			list1.getItems().remove(i);
			list23.getItems().remove(j);
			tutore.remove(i);
			tutor.remove(j);
		}

	}

	// Ajoute les étudiants aux Listview
	public void addToList() {
		ObservableList<Student> list = FXCollections.observableArrayList();
		list.addAll(accepted);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getLevel() == Level.FIRST) {
				list1.getItems().add(list.get(i));
				Tutored t1 = new Tutored(list.get(i).getLastName(), list.get(i).getName(), list.get(i).getPassword(),
						note, "1", m1);
				tutore.add(t1);
			} else {
				list23.getItems().add(list.get(i));
				Tutor t2 = new Tutor(list.get(i).getLastName(), list.get(i).getName(), list.get(i).getPassword(), note,
						"1", m2);
				tutor.add(t2);
			}
		}
	}

	public void addToTable() {
		subjects.add(mat);
		GrapheNonOrienteValue<Student> graph = Graph.createGraph(tutore, tutor, 0);
		CalculAffectation<Student> calcul = Graph.compute(tutore, tutor, subjects, 0);
		for (int i = 0; i < calcul.getAffectation().size(); i++) {
			if(list23.getItems().size() != 0) {
				calcul.getAffectation().get(i).getExtremite1().setLEVEL(list23.getItems().get(i).getLevel());
				calcul.getAffectation().get(i).getExtremite1().setScore(list23.getItems().get(i).getScore());
				calcul.getAffectation().get(i).getExtremite2().setScore(list1.getItems().get(i).getScore());
			}
			Couple tmp = new Couple(calcul.getAffectation().get(i).getExtremite1(),
					calcul.getAffectation().get(i).getExtremite2());
			affected.add(tmp);
		}

		col1.setCellValueFactory(new PropertyValueFactory<>("tuteur"));
		col23.setCellValueFactory(new PropertyValueFactory<>("tutore"));
		table.setItems(affected);
		list1.getItems().clear();
		list23.getItems().clear();
	}

	// Initialise ListView et TableView
	public void initialize(URL arg0, ResourceBundle arg1) {
		addToList();

		ObservableList<CritChoice> data = FXCollections.observableArrayList();
		data.add(new CritChoice("Moyenne", true));
		data.add(new CritChoice("Absences", false));
		critTable.setItems(data);
		critName.setCellValueFactory(new PropertyValueFactory<CritChoice, String>("name"));
		check.setCellValueFactory(new PropertyValueFactory<CritChoice, Boolean>("checked"));
		check.setCellFactory(new Callback<TableColumn<CritChoice, Boolean>, TableCell<CritChoice, Boolean>>() {
			public TableCell<CritChoice, Boolean> call(TableColumn<CritChoice, Boolean> p) {
				return new CheckBoxTableCell<CritChoice, Boolean>();
			}
		});

	}

	public static class CheckBoxTableCell<S, T> extends TableCell<S, T> {
		private final CheckBox checkBox;
		private ObservableValue<T> ov;

		public CheckBoxTableCell() {
			this.checkBox = new CheckBox();
			this.checkBox.setAlignment(Pos.CENTER);
			setAlignment(Pos.CENTER);
			setGraphic(checkBox);
		}

		@Override
		public void updateItem(T item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				setGraphic(checkBox);
				if (ov instanceof BooleanProperty) {
					checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
				}
				ov = getTableColumn().getCellObservableValue(getIndex());
				if (ov instanceof BooleanProperty) {
					checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
				}
			}
		}
	}

}
