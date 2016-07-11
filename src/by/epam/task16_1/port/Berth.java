package by.epam.task16_1.port;

import by.epam.task16_1.warehouse.Container;
import by.epam.task16_1.warehouse.Warehouse;

import java.util.List;

public class Berth {

	private int id;
	private Warehouse portWarehouse;

	public Berth(int id, Warehouse warehouse) {
		this.id = id;
		portWarehouse = warehouse;
	}

	public int getId() {
		return id;
	}

	public boolean add(Warehouse shipWarehouse, int numberOfContainers) throws InterruptedException {
		boolean result = false;

		List<Container> containers = shipWarehouse.getContainer(numberOfContainers);
		synchronized (portWarehouse) {
			result = portWarehouse.addContainer(containers);
		}
		
		return result;
		
	}
	
	

	public boolean get(Warehouse shipWarehouse, int numberOfContainers) throws InterruptedException {
		boolean result = false;

		List<Container> containers = null;
		synchronized (portWarehouse) {
			containers = portWarehouse.getContainer(numberOfContainers);
		}

		if(containers != null){
			shipWarehouse.addContainer(containers);
			result = true;
		}
		
		return result;
	}
	
	
}
