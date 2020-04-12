package realEstate;

import java.io.File;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	private TextField minPriceField, maxPriceField, minAreaField, maxAreaField, minBedsField, maxBedsField, outputField;
	@FXML
	private Label errorField;
	@FXML
	private Button notMyDream;
	
	int minPrice, maxPrice, minArea, maxArea, minBeds, maxBeds;
	File file = new File("src/realEstate/houses.txt");
	HouseList availableHouses = new HouseList(file);
	ArrayList<House> selectedHouses = new ArrayList<House>();
	
	//Called when user presses "Find my dream house!" button
	public void calculate(Event event) {
		try {
			if(!minPriceField.getText().equals("")) {
				minPrice = Integer.parseInt(minPriceField.getText());
			}
			else {
				minPrice = 0;
			}
			if(!maxPriceField.getText().equals("")) {
				maxPrice = Integer.parseInt(maxPriceField.getText());
			}
			else {
				maxPrice = Integer.MAX_VALUE;
			}
			if(!minAreaField.getText().equals("")) {
				minArea = Integer.parseInt(minAreaField.getText());
			}
			else {
				minArea = 0;
			}
			if(!maxAreaField.getText().equals("")) {
				maxArea = Integer.parseInt(maxAreaField.getText());
			}
			else {
				maxArea = Integer.MAX_VALUE;
			}
			if(!minBedsField.getText().equals("")) {
				minBeds = Integer.parseInt(minBedsField.getText());
			}
			else {
				minBeds = 0;
			}
			if(!maxBedsField.getText().equals("")) {
				maxBeds = Integer.parseInt(maxBedsField.getText());
			}
			else {
				maxBeds = Integer.MAX_VALUE;
			}
			
			Criteria c = new Criteria(minPrice, maxPrice, minArea, maxArea, minBeds, maxBeds);
			selectedHouses = availableHouses.getHouseList(c);
			House house = availableHouses.getRandomHouse(selectedHouses);
			outputField.setText(house.getAddress());
			notMyDream.setDisable(false);
		}
		catch(NumberFormatException e) {
			errorField.setText("Please enter valid integers only.");
			errorField.setVisible(true);
		}
		catch(Exception e) {
			errorField.setText("An error has occured");
			errorField.setVisible(true);
		}
	}
	
	//Called when user presses "Reset" button
	public void reset(Event event) {
		errorField.setText("No error");
		outputField.setText("");
		minPriceField.setText("");
		maxPriceField.setText("");
		minAreaField.setText("");
		maxAreaField.setText("");
		minBedsField.setText("");
		maxBedsField.setText("");
		outputField.setText("");
		errorField.setVisible(false);
		selectedHouses = new ArrayList<House>();
		notMyDream.setDisable(true);
	}
	
	//Called when user presses "Not my dream - Find me another!" button
	public void findNext(Event event) {
		if(selectedHouses.isEmpty()) {
			outputField.setText("No more available houses");
		}
		else {
			House house = availableHouses.getRandomHouse(selectedHouses);
			outputField.setText(house.getAddress());
		}
	}
}
