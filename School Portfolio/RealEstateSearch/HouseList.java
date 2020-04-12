package realEstate;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HouseList {
	private ArrayList<House> houseList = new ArrayList<House>();
	
	//Default constructor.
	public HouseList() {
		
	}
	
	//Constructor. Reads file from provided file name and builds arrayList of houses.
	public HouseList(File file) {
		try {
			Scanner fileReader = new Scanner(file);
			while (fileReader.hasNextLine()) {
				String house = fileReader.nextLine();
				String[] houseData = house.split("\\s+", 4);
				House h = new House(houseData[0], Integer.parseInt(houseData[1]), Integer.parseInt(houseData[2]), Integer.parseInt(houseData[3]));
				houseList.add(h);
			}
			fileReader.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
	
	//Nicely prints list of selected houses
	public void printHouses(Criteria C) {
		for(int i = 0; i < houseList.size(); i++) {
			if(houseList.get(i).satisfies(C)) {
				System.out.println(houseList.get(i).toString());
				System.out.println("-----------------------------------------------------");
			}
		}
	}
	
	//Returns a nicely formatted string of selected houses
	public String getHouses(Criteria C) {
		String output = "";
		for(int i = 0; i < houseList.size(); i++) {
			if(houseList.get(i).satisfies(C)) {
				output += (houseList.get(i).toString() + '\n');
				output += ("-----------------------------------------------------" + '\n');
			}
		}
		return output;
	}
	
	//Returns ArrayList of houses matching given criteria C
	public ArrayList<House> getHouseList(Criteria C) {
		ArrayList<House> aList = new ArrayList<House>();
		for(int i = 0; i < houseList.size(); i++) {
			if(houseList.get(i).satisfies(C)) {
				aList.add(houseList.get(i));
			}
		}
		return aList;
	}
	
	//Returns an random house from list and removes it.
	public House getRandomHouse(ArrayList<House> list) {
		Random rand = new Random();
		int r = rand.nextInt(list.size());
		House house = list.get(r);
		list.remove(r);
		return house;
	}
}
