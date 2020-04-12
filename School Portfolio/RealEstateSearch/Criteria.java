package realEstate;

public class Criteria {
	private int  	minimumPrice,
					maximumPrice,
					minimumArea,
					maximumArea,
					minimumNumberOfBedrooms,
					maximumNumberOfBedrooms;
	
	//Default constructor. Loads 0s to all variables.
	public Criteria() {
		minimumPrice = 0;
		maximumPrice = 0;
		minimumArea = 0;
		maximumArea = 0;
		minimumNumberOfBedrooms = 0;
		maximumNumberOfBedrooms = 0;
	}
	
	//Constructor. Loads local variables with provide values
	public Criteria(int minP, int maxP, int minA, int maxA, int minB, int maxB) {
		minimumPrice = minP;
		maximumPrice = maxP;
		minimumArea = minA;
		maximumArea = maxA;
		minimumNumberOfBedrooms = minB;
		maximumNumberOfBedrooms = maxB;
	}
	
	//Get
	public int getMinimumPrice() {
		return minimumPrice;
	}
	
	//Get
	public int getMaximumPrice() {
		return maximumPrice;
	}
	
	//get
	public int getMinimumArea() {
		return minimumArea;
	}
	
	//Get
	public int getMaximumArea() {
		return maximumArea;
	}
	
	//Get
	public int getMinimumNumberOfBedrooms() {
		return minimumNumberOfBedrooms;
	}
	
	//Get
	public int getMaximumNumberOfBedrooms() {
		return maximumNumberOfBedrooms;
	}
}
