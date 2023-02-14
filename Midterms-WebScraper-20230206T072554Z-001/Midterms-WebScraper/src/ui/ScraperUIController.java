package ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import scraper.*;
import thucthe.*;

public class ScraperUIController {
	
	private Gson gson = new Gson();
	private ObservableList<ThucThe> listThucThe;

    @FXML
    private TableView<ThucThe> tblResult;

    @FXML
    private TableColumn<ThucThe, String> colName;

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private TextField tfFilter;

    @FXML
    private Button btnScrape;

    @FXML
    private TextArea taInfo;
    
    @FXML
    private void initialize() {
    	colName.setCellValueFactory(
    			new PropertyValueFactory<ThucThe, String>("ten"));
    	tblResult.setItems(listThucThe);
    	
    	tblResult.setPlaceholder(new Label("No content in table yet!\nSelect type to display results."));
    	
    	String[] cbTypeOpt = {"Di tích", "Kinh đô", "Lễ hội", "Sự kiện", "Triều đại", "Vua"};
    	
    	cbType.setItems(FXCollections.observableArrayList(cbTypeOpt));
    	
    	tblResult.getSelectionModel().selectedItemProperty().addListener(
    			new ChangeListener<ThucThe>() {
					@Override
					public void changed(
							ObservableValue<? extends ThucThe> observable,
							ThucThe oldValue, ThucThe newValue) {
						if (newValue != null) {
							updateInfoArea(newValue);
						}
					}
    			});
    	
    	tfFilter.textProperty().addListener(
    			new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						showFilteredMedia(newValue);
					}
		    	});
    	
    }

	void updateInfoArea(ThucThe newValue) {
		taInfo.setText(newValue.toString());
	}

	void showFilteredMedia(String newValue) {
		FilteredList<ThucThe> filteredItemsOrdered =
				new FilteredList<>(listThucThe);
		
		filteredItemsOrdered.setPredicate(thucthe -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            if (thucthe.isMatch(newValue)) {
            	return true;
            }
			return false;
		});
		
		tblResult.setItems(filteredItemsOrdered);
	}

	@FXML
    void startScrape(ActionEvent event) throws IOException {
		String type = cbType.getValue();
    	Scraper scraper = null;
    	switch (type) {
    		case "Di tích":
    	    	scraper = new DiTichScraper();
    			break;
    		case "Kinh đô":
    	    	scraper = new KinhDoScraper();
    			break;
    		case "Lễ hội":
    	    	scraper = new LeHoiScraper();
    			break;
    		case "Sự kiện":
    	    	scraper = new SuKienScraper();
    			break;
    		case "Triều đại":
    	    	scraper = new TrieuDaiScraper();
    			break;
    		case "Vua":
    	    	scraper = new VuaScraper();
    	}
    	tblResult.setItems(null);
    	scraper.scrape();
    	updateTable(new ActionEvent());
    }

    @FXML
    void updateTable(ActionEvent event) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
    	tfFilter.disableProperty().set(false);
    	btnScrape.disableProperty().set(false);
    	String type = cbType.getValue();
    	String filename = new String();
    	Type listType = null;
    	switch (type) {
    		case "Di tích":
    			filename = "DiTich.json";
    	    	listType = new TypeToken<ArrayList<DiTich>>(){}.getType();
    			break;
    		case "Kinh đô":
    			filename = "KinhDo.json";
    	    	listType = new TypeToken<ArrayList<KinhDo>>(){}.getType();
    			break;
    		case "Lễ hội":
    			filename = "LeHoi.json";
    	    	listType = new TypeToken<ArrayList<LeHoi>>(){}.getType();
    			break;
    		case "Sự kiện":
    			filename = "SuKien.json";
    	    	listType = new TypeToken<ArrayList<SuKien>>(){}.getType();
    			break;
    		case "Triều đại":
    			filename = "TrieuDai.json";
    	    	listType = new TypeToken<ArrayList<TrieuDai>>(){}.getType();
    			break;
    		case "Vua":
    			filename = "Vua.json";
    	    	listType = new TypeToken<ArrayList<Vua>>(){}.getType();
    	}
    	ArrayList<ThucThe> jsonArr = gson.fromJson(new FileReader(filename), listType);
//    	ThucThe[] jsonArr = gson.fromJson(new FileReader(filename), listType);
    	listThucThe = null;
    	if (jsonArr != null) {
        	listThucThe = FXCollections.observableArrayList(jsonArr);
    	}
    	tblResult.setItems(listThucThe);
    	colName.setCellValueFactory(
    			new PropertyValueFactory<ThucThe, String>("ten"));
    }

}
