package main.ihm;

import java.io.IOException;
import java.net.URL;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

public class FilterPage {
	@FXML
	SplitMenuButton studentChoice;
	@FXML
	SplitMenuButton critChoice;
	@FXML
	SplitMenuButton opChoice;
	@FXML
	MenuItem tuteurs;
	@FXML
	MenuItem tutores;
	@FXML
	Button validButton;
	@FXML
	MenuItem all;
	@FXML
	MenuItem moyenne;
	@FXML
	MenuItem absences;
	@FXML
	MenuItem aucun;
	@FXML
	MenuItem sup;
	@FXML
	MenuItem inf;
	@FXML
	Slider slide;
	
	static int choice = 0;
	static int crit = 0;
	static int op = 0;
	static double value = 0;

	public void valid(ActionEvent event) throws IOException {
		
		 slide.valueProperty().addListener(new ChangeListener<Number>() {
	            public void changed(ObservableValue<? extends Number> ov,
	                Number old_val, Number new_val) {
	                    value = (double) new_val;
	            }
	        });

		
		Stage a = (Stage) validButton.getScene().getWindow();
		a.close();
		
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("Maquette_Haute_Fidelite_Prof1.fxml");
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		LaunchPage.stageA.setScene(scene);
	}

	public void tuteurChoice(ActionEvent event) {
		studentChoice.setText("Tuteurs");
		choice = 1;
	}

	public void tutoreChoice(ActionEvent event) {
		studentChoice.setText("Tutores");
		choice = 2;
	}
	
	public void allChoice(ActionEvent event) {
		studentChoice.setText("Tous");
		choice = 0;
	}
	
	public void moyChoice(ActionEvent event) {
		critChoice.setText("Moyenne");
		crit = 1;
	}
	
	public void absChoice(ActionEvent event) {
		critChoice.setText("Absences");
		crit = 2;
	}
	
	public void noChoice(ActionEvent event) {
		critChoice.setText("Aucun");
		crit = 0;
	}
	
	public void supChoice(ActionEvent event) {
		opChoice.setText("supérieur");
		op = 1;
	}
	
	public void infChoice(ActionEvent event) {
		opChoice.setText("inférieur");
		op = 2;
		value = slide.getValue();
	}
	
}
