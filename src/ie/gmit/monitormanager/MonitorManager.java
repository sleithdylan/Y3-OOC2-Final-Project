package ie.gmit.monitormanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MonitorManager implements Serializable {
	
    private static final long serialVersionUID = 1L;

	// Declares list
	private List<Monitor> monitorList;

	// Constructor
	public MonitorManager() {
		// Creates a Monitor ArrayList
		monitorList = new ArrayList<Monitor>();
	}

	// Getters and Setters
	public List<Monitor> getMonitors() {
		return monitorList;
	}

	public void setMonitors(List<Monitor> monitorList) {
		this.monitorList = monitorList;
	}

	// Adds a Monitor to the Monitor List
	public boolean addMonitor(Monitor monitor) {
		try {
			return monitorList.add(monitor);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	// Deletes a Monitor to the Monitor List
	public boolean deleteMonitor(Monitor monitor) {
		try {
			return monitorList.remove(monitor);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	// Delete Monitor by Number
	public boolean deleteModelByMake(String modelMake) {
		// Searches for Monitor by Number
		Monitor monitor = findModelByMake(modelMake);
		// If a Monitor was found, delete the monitor
		if (monitor != null) {
			return deleteMonitor(monitor);
		} else {
			// If no monitor was found, return false
			return false;
		}
	}

	public Monitor findModelByMake(String modelMake) {

		// Iterates over arrayList
		for (Monitor monitor : monitorList) {
			if (monitor.getModelMake().equals(modelMake)) {
				return monitor;
			}
		}

		// Return null if Model Name was not found
		return null;
	}

	// Find a list of monitors by model make
	public List<Monitor> getMonitorsByModelMake(String modelMake) {
		// Create a new ArrayList to hold Monitors with the same make
		List<Monitor> sameNames = new ArrayList<Monitor>();
		// Iterate over arrayList
		for (Monitor monitor : monitorList) {
			// If a monitor is found with the given make, add to list
			if (monitor.getModelMake().equalsIgnoreCase(modelMake)) {
				sameNames.add(monitor);
			}
		}
		// Check if list has any monitors
		if (sameNames.size() > 0) {
			// If monitors were found then return the list
			return sameNames;
		}
		// If no monitors were found with that model make then return null
		return null;
	}
	
	public void loadMonitorFile(String pathToFile) {
		File inFile = new File(pathToFile);
		FileReader fileReader = null;
		BufferedReader br = null;
		String record = null;

		try {
			fileReader = new FileReader(inFile);
			br = new BufferedReader(fileReader);
			br.readLine(); //discard first line of csv file
			while ((record = br.readLine()) != null) {
				String[] elements = record.split(",");
				Monitor newMonitor = new Monitor(elements[0], elements[1]);
				this.addMonitor(newMonitor);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Returns Total Number of Monitors/Items
	public int findTotalMonitors() {
		return monitorList.size();
	}
	
	public MonitorManager loadDB(String dbPath){
		MonitorManager mm = null;
    	try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dbPath));
			mm = (MonitorManager) in.readObject();
    		in.close();
    	} catch (Exception e) {
    		System.out.print("[Error] Cannot load DB. Cause: ");
    		e.printStackTrace();
    	}
		return mm;
    }
}
