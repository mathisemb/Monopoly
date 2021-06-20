package application;
	

import java.io.IOException;

import java.util.ArrayList;
import java.util.Optional;

import application.event.EventAchatTerrain;
import application.event.EventGererMaison;
import application.event.EventJouer;
import application.event.EventLiberation;
import application.event.EventPasser;
import application.event.EventPayerPrison;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.cartes.Carte;
import modele.cartes.lesCartes.CarteLiberePrison;
import modele.cases.Case;
import modele.cases.Propriete;
import modele.cases.lesCases.CaseCaisseCommunaute;
import modele.cases.lesCases.CaseChance;
import modele.exceptions.MonopolyException;
import modele.joueur.Joueur;
import modele.plateau.Plateau;
import ui.Pion;
import ui.UIPlateau;


public class Monopoly extends Application {
	public final static String ACTION_ACHAT_TERRAIN = "Acheter le terrain";
	public final static String ACTION_GERER_MAISON = "Gerer les maisons et les hotels";
	public final static String ACTION_PAYER_PRISON = "Payer pour sortir de prison";
	public final static String ACTION_LIBERATION = "Utiliser une carte de liberation";
	public final static String ACTION_PASSER = "Passer au suivant";
	public final static String ACTION_JOUER = "Lancer les des";
	
	
	
	private	ArrayList<ToggleButton> tabBoutonsJoueurs = new ArrayList<ToggleButton>();
	
	private UIPlateau uiPlateau;
	private Canvas grillePane;
	private Button bAvancer;
	private TextField tfDe1;
	private TextField tfDe2;
	private Label messageFooter;
	
	Stage window;
	Scene sceneIntro, scenePrincipale;
	
	/**
	 * YL : ListView peut contenir n'importe quel type d'objet. Pour l'instant, ce sont des String
	 * --> A modifier !!
	 */
	private ListView<Propriete> proprietesJoueurCourant;
	
	/**
	 * YL : la liste des joueurs est reprÃ©sentÃ©e par une liste de noms, ainsi que la liste des pions. 
	 * --> A modifier !!
	 */
	private ArrayList<Joueur>	listeJoueurs = new ArrayList<Joueur>();
	
	private	int	terrainSelectionne = -1;
	private TextField tfPorteMonnaie;

	private	int		nbDoubles = 0;
	
	private	FenetreTerrain fenetreTerrain; // = new FenetreTerrain(/*this.getTerrainSelectionne()*/);
	
	public ListView<Propriete>  getZoneProprietes() {
		return proprietesJoueurCourant;
	}

	public ArrayList<ToggleButton> getTabBoutonsJoueurs() {
		return tabBoutonsJoueurs;
	}

	public ArrayList<Joueur> getListeJoueurs() {
		return listeJoueurs;
	}


	
	public TextField getTfValeurDe1() {
		return tfDe1;
	}

	public TextField getTfValeurDe2() {
		return tfDe2;
	}

	public TextField getTfPorteMonnaie() {
		return tfPorteMonnaie;
	}

	public UIPlateau getUiPlateau() {
		return uiPlateau;
	}
	
	public Canvas getGrillePane() {
		return grillePane;
	}


	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			
			
			// La scène d'intro
			HBox root = new HBox();
			root.getStyleClass().addAll("hbox", "pane", "vbox");

			// Partie visuelle
			VBox vb1 = new VBox();
			Label titre = new Label("Entrez ici tous les joueurs de la partie");
			ListView<Joueur> list = new ListView<Joueur>();
			list.setMaxHeight(200);
			list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			VBox vb2 = new VBox();
			Button AddButton = new Button("Ajouter un joueur");
			Button DelButton = new Button("Supprimer un joueur");
			Button ClearButton = new Button("Supprimer tous les joueurs");
			Button Jouer = new Button("Commencer la partie");

			vb1.getChildren().addAll(titre, list);
			vb2.getChildren().addAll(AddButton, DelButton, ClearButton, Jouer);
			root.getChildren().addAll(vb1, vb2);
			
			Scene sceneIntro = new Scene(root,410,250);

			
			
			// Bouton Add avec classe anonyme
			AddButton.addEventHandler(ActionEvent.ACTION,
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						String[] choix = {"Chien", "Bateau", "Brouette", "Chapeau", "Chat", "Chaussure", "DeACoudre", "Voiture"};
						ChoiceDialog<String> boiteChoix = new ChoiceDialog<>(choix[0], choix);

						for(Joueur joueur : list.getItems()) {
							boiteChoix.getItems().remove(joueur.getPion().getNom()); // on enlève les pions déjà pris
						}
						
						boiteChoix.setSelectedItem(boiteChoix.getItems().get(0));
						
						boiteChoix.setTitle("Choix du pion");
						boiteChoix.setHeaderText("Sélectionnez votre pion");
						boiteChoix.setContentText("Pion :");
						Optional<String> selection = boiteChoix.showAndWait();
						if (selection.isPresent()) {
							list.getItems().add(new Joueur( new Pion(selection.get())));
						}
					
					}
				}
			);
			
			// Bouton Delete avec classe DelButtonController dans un autre fichier
			DelButtonController delBC = new DelButtonController(list);
			DelButton.addEventHandler(ActionEvent.ACTION, delBC);
			
			// Bouton Clear avec la méthode setOnAction
			ClearButton.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					list.getItems().clear();
				}
			});
			
			// Mis à jour de l'état du bouton Delete avec addListener
			list.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> { DelButton.setDisable(list.getSelectionModel().getSelectedItems().size()== 0);}
			);
			DelButton.setDisable(true); // initialisation du bouton à inaccessible dès le départ
			
			//Jouer.setOnAction(e -> primaryStage.setScene(scenePrincipale));
			
			Jouer.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					if (list.getItems().size() < 2) {
						Alert dialogA = new Alert(AlertType.ERROR);
						dialogA.getButtonTypes().setAll(ButtonType.OK);
						dialogA.setTitle("Avertissement");
						dialogA.setHeaderText("Nombre incorrect de joueurs");
						dialogA.setContentText("Attention : vous devez entrez au minimum 2 joueurs !");
						dialogA.showAndWait();
					}
					else {
						// La scène principale
						try {
							initPartie(window, list);
						} catch (MonopolyException | NumberFormatException | IOException e) {
							e.printStackTrace();
						}
						
						BorderPane root2 = new BorderPane();
						scenePrincipale = new Scene(root2);			
						initPanneauPlateau(root2);
						initPanneauDroit(root2);
						initFooter(root2);
						uiPlateau.dessiner(grillePane);
						primaryStage.setScene(scenePrincipale);
					}
				}
			});
			
			window.setScene(sceneIntro);
			window.show();
	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void initPanneauDroit(BorderPane root) {
		VBox panneauDroit = new VBox();
		panneauDroit.setAlignment(Pos.TOP_CENTER);

		initBoutonsJoueurs(panneauDroit);
		initDes(panneauDroit);
		initActions(panneauDroit);
		initZonePropriete(panneauDroit);
		
		tabBoutonsJoueurs.get(0).fire();
		
		root.setRight(panneauDroit);
	}

	private void initFooter(BorderPane root) {
		HBox footer = new HBox();
		footer.setAlignment(Pos.BASELINE_LEFT);

		messageFooter = new Label("");
		footer.getChildren().add(messageFooter);
		
		root.setBottom(footer);
	}

	
	private void initZonePropriete(VBox panneauDroit) {
		panneauDroit.getChildren().add(new Label(" "));

		HBox hb = new HBox();
		hb.getChildren().add(new Label("Porte monnaie : "));
		tfPorteMonnaie = new TextField(""+this.getJoueurCourant().getArgent());
		tfPorteMonnaie.setEditable(false);
		hb.getChildren().add(tfPorteMonnaie);
		
		panneauDroit.getChildren().add(hb);
		
		panneauDroit.getChildren().add(new Label(" "));
		panneauDroit.getChildren().add(new Label("Liste des proprietes :"));
		
		
		proprietesJoueurCourant = new ListView<Propriete>();
		proprietesJoueurCourant.setPrefHeight(0);

		proprietesJoueurCourant.getItems().addListener(new ListChangeListener<Propriete>() {
			@Override
			public void onChanged(Change<? extends Propriete> arg0) {
		    	proprietesJoueurCourant.setPrefHeight(proprietesJoueurCourant.getItems().size() * 24 + 4); // 24 et 4 sont des nombres magiques...
			}
		});
		
		proprietesJoueurCourant.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				terrainSelectionne = proprietesJoueurCourant.getSelectionModel().getSelectedIndex();
			}		
		});
		
		panneauDroit.getChildren().add(proprietesJoueurCourant);
	}

	private void initActions(VBox panneauDroit) {
		VBox box = new VBox();
		
		bAvancer = new Button(ACTION_JOUER);
		bAvancer.setOnAction(new EventJouer(this));
		box.getChildren().add(bAvancer);

		Button bPasser = new Button(ACTION_PASSER);
		bPasser.setOnAction(new EventPasser(this));
		box.getChildren().add(bPasser);
		
		Button acheterTerrain = new Button(ACTION_ACHAT_TERRAIN);
		acheterTerrain.setOnAction(new EventAchatTerrain(this));
		box.getChildren().add(acheterTerrain);
				
		Button gererMaisons = new Button(ACTION_GERER_MAISON);
		gererMaisons.setOnAction(new EventGererMaison(this));
		box.getChildren().add(gererMaisons);
				
		Button payerPrison = new Button(ACTION_PAYER_PRISON);
		payerPrison.setOnAction(new EventPayerPrison(this));
		box.getChildren().add(payerPrison);

		Button liberation = new Button(ACTION_LIBERATION);
		liberation.setOnAction(new EventLiberation(this));
		box.getChildren().add(liberation);
		
		panneauDroit.getChildren().add(box);
	}

	private void initDes(VBox panneau) {
		Label des = new Label("Des :");
		tfDe1 = new TextField ();
		tfDe1.setPrefColumnCount(2);
		tfDe2 = new TextField ();
		tfDe2.setPrefColumnCount(2);
		HBox hb = new HBox();

		hb.getChildren().addAll(des, tfDe1, tfDe2);
		hb.setSpacing(2);
	
		panneau.getChildren().add(hb);
	}

	private void initBoutonsJoueurs(VBox panneau) {
		ToggleGroup group = new ToggleGroup();
			
		HBox box = new HBox();
		box.setMouseTransparent(true);
		
		for (Joueur joueur : listeJoueurs) {
			
			ToggleButton bJoueur = new ToggleButton(joueur.toString());
			bJoueur.setToggleGroup(group);
			bJoueur.setUserData(joueur);


			box.getChildren().add(bJoueur);
			tabBoutonsJoueurs.add(bJoueur);
		}

		panneau.getChildren().add(box);
	}

	private void initPanneauPlateau(BorderPane root) {
		Image image = uiPlateau.getImage();
		grillePane = new Canvas(image.getWidth(), image.getHeight());
		root.setCenter(grillePane);
	}

	private void initPartie(Stage primaryStage, ListView<Joueur> list) throws MonopolyException, NumberFormatException, IOException {
		Plateau plateau = Plateau.getInstance();
		plateau.setLesCases("Parametre/Terrains.csv");
		plateau.setLesCartesChance("Parametre/CartesChance.csv");
		plateau.setLesCartesCommunaute("Parametre/CartesCommunaute.csv");
		plateau.setJoueurPresent(list.getItems().get(0));
		
		for(Joueur joueur : list.getItems()) {
			listeJoueurs.add(joueur);
			plateau.ajouterJoueur(joueur);
		}
		
		uiPlateau = new UIPlateau();		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void DialogAction(String message, boolean erreur) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Achat d'un terrain");
		alert.setContentText(message);
	
		if (erreur) {
			alert.setHeaderText("Tu ne peux pas faire cette action !");
		}
		else {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setHeaderText("Achat effectue");			
		}
		
		alert.showAndWait();
	}

	public void DialogInfo(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("");
		alert.setContentText(message);
		alert.showAndWait();
	}

	public int getNbDoubles() {
		return nbDoubles;
	}

	public void setNbDoubles(int nbDoubles) {
		this.nbDoubles = nbDoubles;
	}

	public Label getMessageFooter() {
		return messageFooter;
	}

	public int getTerrainSelectionne() {
		return terrainSelectionne;
	}

	public FenetreTerrain getFenetreTerrain() {
		return fenetreTerrain;
	}

	public Joueur getJoueurCourant() {
		return Plateau.getInstance().getJoueurPresent(); // pour éviter de renommer toutes les occurence de getJoueurCourant
	}

	
	public void setInfosJoueurCourant() {
		this.tfPorteMonnaie.setText(""+Plateau.getInstance().getJoueurPresent().getArgent());
		this.proprietesJoueurCourant.getItems().clear();
		
		for(Propriete prop : Plateau.getInstance().getJoueurPresent().getLesProprietes()) {
			this.proprietesJoueurCourant.getItems().add(prop);
		}
	}
	
	
	public void afficheCarte(Case pos) throws MonopolyException {
		if (!(pos instanceof CaseCaisseCommunaute || pos instanceof CaseChance))
			throw new MonopolyException("Pas de carte à afficher lors d'un arrêt sur la case " + pos.toString());
		else {
			if (pos instanceof CaseCaisseCommunaute) {
				Carte carte = Plateau.getInstance().getLesCartesCommunaute().get(0);
				if (carte instanceof CarteLiberePrison) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("");
					alert.setContentText("Carte communauté :\n\n" + Plateau.getInstance().getLesCartesCommunaute().get(0).getNom());
					ButtonType garder = new ButtonType("Garder", ButtonData.OK_DONE);
					alert.getButtonTypes().add(garder);
					getJoueurCourant().garderCarte(carte);
					alert.showAndWait();
				}
				else {
					this.DialogInfo("Carte communauté :\n\n" + Plateau.getInstance().getLesCartesCommunaute().get(0).getNom());
				}
			}
			else if (pos instanceof CaseChance) {
				this.DialogInfo("Carte chance :\n\n" + Plateau.getInstance().getLesCartesChance().get(0).getNom());
			}
		}	
	}

	
}
