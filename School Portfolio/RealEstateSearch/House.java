package realEstate;

public class House {
	private String address;
	private int price, area, numBedrooms;

	//Default constructor
	public House() {
		address = "";
		price = 0;
		area = 0;
		numBedrooms = 0;
	}
	
	//Constructor. Loads local variables with provided values.
	public House(String addr, int cost, int a, int bedrooms) {
		address = addr;
		price = cost;
		area = a;
		numBedrooms = bedrooms;
	}
	
	//Checks if house satisfies provided criteria. Takes Criteria object.
	public boolean satisfies(Criteria C) {
		boolean search = true;
		
		if(!(price >= C.getMinimumPrice() && price <= C.getMaximumPrice())) {
			search = false;
		}
		if(!(area >= C.getMinimumArea() && area <= C.getMaximumArea())) {
			search = false;
		}
		if(!(numBedrooms >= C.getMinimumNumberOfBedrooms() && numBedrooms <= C.getMaximumNumberOfBedrooms())) {
			search = false;
		}
		
		return search;
	}
	
	//Return a nicely formatted string about the house
	public String toString() {
		return "Address: " + address + "\nPrice: " + price + "\nArea: " + area + "\nBedrooms: " + numBedrooms;
	}
	
	//Get
	public String getAddress() {
		return address;
	}
	
	//Get
	public int getPrice() {
		return price;
	}
	
	//Get
	public int getArea() {
		return area;
	}
	
	//Get
	public int getNumBedrooms() {
		return numBedrooms;
	}
}
