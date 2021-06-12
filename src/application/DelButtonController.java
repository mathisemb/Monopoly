package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import modele.joueur.Joueur;

public class DelButtonController implements EventHandler<ActionEvent> {
	ListView<Joueur> _list;
	
	public DelButtonController(ListView<Joueur> list) {
		this._list = list;
	}
	
	@Override
	public void handle(ActionEvent event) {
		ObservableList<Integer> selectedIndices = _list.getSelectionModel().getSelectedIndices();
		for (int i= selectedIndices.size()-1;i>=0;i--) {
			int indice = selectedIndices.get(i).intValue();
			_list.getItems().remove(indice);
		}
	}
}