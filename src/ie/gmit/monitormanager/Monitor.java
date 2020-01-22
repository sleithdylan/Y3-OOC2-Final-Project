package ie.gmit.monitormanager;

import java.io.Serializable;

public class Monitor implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// Declares variables
	private String modelMake;
	private String modelNumber;

	// Creates Constructors
	public Monitor(String modelMake) {
		this.modelMake = modelMake;
	}

	public Monitor(String modelMake, String modelNumber) {
		this.modelMake = modelMake;
		this.modelNumber = modelNumber;
	}

	// Getters and Setters
	public String getModelMake() {
		return modelMake;
	}

	public void setModelMake(String modelMake) {
		this.modelMake = modelMake;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
}
