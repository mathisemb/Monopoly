package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.cases.lesCases.CaseTerrainConstructible;
import modele.exceptions.MonopolyException;
import modele.plateau.Plateau;


public class FenetreTerrain extends Stage {
	private CaseTerrainConstructible leTerrain;
	private ListView<String> lesMaisons;
	
	public FenetreTerrain(CaseTerrainConstructible terrain) {
		
		Dialog<CaseTerrainConstructible> dialog = new Dialog<>();
		dialog.setTitle("Gestion des constructions");
		
		leTerrain = terrain;
		
		HBox root = new HBox();
		root.getStyleClass().addAll("hbox", "pane", "vbox");

		// Partie gauche
		VBox vb1 = new VBox();
		Label titre = new Label("Les maisons sur le terrain : " + leTerrain.toString());
		lesMaisons = new ListView<String>();
		lesMaisons.setMaxHeight(200);
		lesMaisons.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		for (int i = 1; i <= leTerrain.getNbMaisons(); i++) // on met les maisons déjà achetées
			lesMaisons.getItems().add("Maison n° " + i);

		vb1.getChildren().addAll(titre, lesMaisons);

		// Partie droite
		VBox vb2 = new VBox();
		Button AddButton = new Button("Acheter une maison");
		Button DelButton = new Button("Vendre une maison");
		DelButton.setDisable(true); // à faire plus tard
		Button ClearButton = new Button("Vendre le terrain");
		ClearButton.setDisable(true); // à faire plus tard
		vb2.getChildren().addAll(AddButton, DelButton, ClearButton);
		
		root.getChildren().addAll(vb1, vb2); // on rassemble les 2 parties
		
		dialog.getDialogPane().getChildren().add(root);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		
		dialog.getDialogPane().setContent(root);
		
		
		// Bouton Add avec classe anonyme
		AddButton.addEventHandler(ActionEvent.ACTION,
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						if (leTerrain.getNbMaisons() == 4) {
							Plateau.getInstance().getJoueurPresent().acheterMaison(leTerrain);
							lesMaisons.getItems().add("Hôtel");
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText("");
							alert.setContentText("Un hôtel a été construit");
							alert.showAndWait();
							AddButton.setDisable(true);
						}
						else {
							if (leTerrain.getNbMaisons() == 3)
								AddButton.setText("Acheter un hôtel"); // on met à jour la fonction du bouton
							
							Plateau.getInstance().getJoueurPresent().acheterMaison(leTerrain);
							lesMaisons.getItems().add("Maison n° " + (lesMaisons.getItems().size()+1));
							
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText("");
							alert.setContentText("Une maison a été construite");
							alert.showAndWait();
						}
					} catch (MonopolyException e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Achat d'une maison");
						alert.setContentText(e.getMessage());
						alert.setHeaderText("Tu ne peux pas faire cette action !");
						alert.showAndWait();
					}
				}
			}
		);
		
		
		// Bouton Delete à développer plus tard
		/*
		DelButton.addEventHandler(ActionEvent.ACTION,
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					ObservableList<Integer> selectedIndices = lesMaisons.getSelectionModel().getSelectedIndices();
					for (int i = selectedIndices.size()-1;i>=0;i--) {
						int indice = selectedIndices.get(i).intValue();
						lesMaisons.getItems().remove(indice);
					}
					// on met à jour les n° de maison pour que ça soit plus beau
					for (int i = 0; i <= lesMaisons.getItems().size(); i++) {
						String maison = "Maison n° " + (i+1);
						lesMaisons.getItems().set(i, maison);
					}
				}
			}
		);
		
		// Mis à jour de l'état du bouton Delete avec addListener
		lesMaisons.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> { DelButton.setDisable(lesMaisons.getSelectionModel().getSelectedItems().size()== 0);}
		);
		DelButton.setDisable(true); // initialisation du bouton à inaccessible dès le départ
		
		*/

		dialog.showAndWait();
	
		
	}

}
