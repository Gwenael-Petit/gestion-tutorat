package main.ihm;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchPage extends Application{
public Stage stageA;
	
	public void start(Stage stageA) throws IOException {
		this.stageA = stageA;
		page();
	}
	
	public void page() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource("Maquette_Haute_Fidelite_Prof1.fxml");
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
		}
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stageA.setScene(scene);
		stageA.setTitle("FXML demo");
		stageA.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}

