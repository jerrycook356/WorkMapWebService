package com.JerryCook.WebService.Model;

public class CoalAnnotation {

	public int id;
	public String stockpile;
	public String company;
	public String source;
	public double latitude;
	public double longitude;
	
	
	public void setId(int id) {
		this.id  = id;
	}
	public int getId() {
		return id;
	}
	
	public void setStockpile(String stockpile) {
		this.stockpile = stockpile;
	}
	public String getStockpile()
	{
		return stockpile;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompany()
	{
		return company;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSource() {
		return source;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
		
	}
	public double getLongitude() {
		return longitude;
	}
}
