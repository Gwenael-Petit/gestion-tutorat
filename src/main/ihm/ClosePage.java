package main.ihm;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClosePage {
	@FXML
    Button TerminerButton;
	@FXML
	Button RetourButton;

	public void next(ActionEvent event) {
		Stage stage = (Stage) TerminerButton.getScene().getWindow();
		stage.close();
	}
	
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

}
