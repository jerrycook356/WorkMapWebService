package com.JerryCook.WebService.WebService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.JerryCook.WebService.DatabaseHelper.DatabaseHelper;
import com.JerryCook.WebService.Model.CoalAnnotation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Path("WebService")
public class WebService {
	DatabaseHelper dh = new DatabaseHelper();
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		ArrayList<CoalAnnotation> annots = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM coalannotationtable";
			Connection con = dh.getConnection();
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CoalAnnotation ca = new CoalAnnotation();
				ca.setId(rs.getInt(1));
				ca.setStockpile(rs.getString(2));
				ca.setCompany(rs.getString(3));
				ca.setSource(rs.getString(4));
				ca.setLatitude(rs.getDouble(5));
				ca.setLongitude(rs.getDouble(6));
				
				annots.add(ca);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String returnString = gson.toJson(annots);
		return returnString;
	}
	
	@POST
	@Path("addAnnot")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addAnnot(String inString)
	{
		Gson gson = new GsonBuilder().create();
		CoalAnnotation ca = gson.fromJson(inString, CoalAnnotation.class);
		
		String sql = "INSERT INTO coalannotationtable(id,stockpile,company,source,latitude,longitude)"+
		"VALUES(?,?,?,?,?,?)";
		try(Connection con = dh.getConnection())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ca.id);
			ps.setString(2,ca.stockpile);
			ps.setString(3, ca.company);
			ps.setString(4, ca.source);
			ps.setDouble(5, ca.latitude);
			ps.setDouble(6, ca.longitude);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@POST
	@Path("deleteAnnot")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteAnnot(String inString)
	{
		Gson gson = new GsonBuilder().create();
		CoalAnnotation ca = gson.fromJson(inString, CoalAnnotation.class);
		
		String sql = "DELETE FROM coalannotationtable WHERE id = ?"+
		" AND stockpile = ? AND company = ? AND source = ? AND latitude = ? AND longitude = ?";
		try(Connection con = dh.getConnection())
		{
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ca.id);
			ps.setString(2,ca.stockpile);
			ps.setString(3, ca.company);
			ps.setString(4, ca.source);
			ps.setDouble(5, ca.latitude);
			ps.setDouble(6, ca.longitude);
			ps.executeUpdate();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}


