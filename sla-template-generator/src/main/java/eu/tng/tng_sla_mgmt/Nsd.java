package eu.tng.tng_sla_mgmt;

import java.util.ArrayList;
import java.util.List;

public class Nsd {
	private static String name;
	private static String description;
	private static ArrayList<String> mon_desc = new ArrayList<String>();
	private static ArrayList<String> mon_metric = new ArrayList<String>();
	private static ArrayList<String> mon_unit = new ArrayList<String>();

	// public method to get the ns name
	public String getName() {
		return name;
	}

	// public method to set the ns name
	public void setName(String name) {
		this.name = name;
	}

	// public method to get the ns description
	public String getDescription() {
		return description;
	}

	// public method to set the ns description
	public void setDescription(String description) {
		this.description = description;
	}

	// public method to get the ns mon_desc
	public ArrayList<String> GetMonDesc() {
		return mon_desc;
	}

	// public method to set the ns mon_desc
	public void SetMonDesc(ArrayList<String> mon_desc) {
		this.mon_desc = mon_desc;
	}

	// public method to get the ns mon_metric
	public ArrayList<String> GetMonMetric() {
		return mon_metric;
	}

	// public method to set the ns mon_metric
	public void SetMonMetric(ArrayList<String> mon_metric) {
		this.mon_metric = mon_metric;
	}

	// public method to get the ns mon_unit
	public ArrayList<String> GetMonUnit() {
		return mon_unit;
	}

	// public method to set the ns mon_unit
	public void SetMonUnit(ArrayList<String> mon_unit) {
		this.mon_unit = mon_unit;
	}


}
