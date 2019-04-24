package servicerestjava;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Package;

/* Gets the package id based on what package is clicked.
 * Author: Brandon Ezekiel
 * Date: April 23 2019
 */
@Path("/packages")
public class packageRESTservice {
	@GET
	@Path("/getpackageid/{ packageid }")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPackageId(@PathParam("packageid") int packageid)
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExpertsWebApp");
		EntityManager em = factory.createEntityManager();
		String sql = "SELECT p FROM Package p WHERE p.packageId= " + packageid + "";
		Query query = em.createQuery(sql);
		List<Package> packages = query.getResultList();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Package>>() {}.getType();
		String jsonString = gson.toJson(packages, type);
		em.close();
		factory.close();
		jsonString = "[{\"packages\": " + jsonString + "}]";
		return jsonString;
		
	}
	
	//Ethan Shipley and Brandon Ezekiel
	@GET
	@Path("/getallpackages")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPackage()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExpertsWebApp");
		EntityManager em = factory.createEntityManager();
		String sql = "SELECT p FROM Package p";
		Query query = em.createQuery(sql);
		List<Package> packages = query.getResultList();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Package>>() {}.getType();
		String jsonString = gson.toJson(packages, type);
		em.close();
		factory.close();
		jsonString = "[{\"packages\": " + jsonString + "}]";
		return jsonString;
		
	}
	
	//Ethan Shipley and Brandon Ezekiel
	@GET
	@Path("/getallpackagesweb")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPackageweb()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExpertsWebApp");
		EntityManager em = factory.createEntityManager();
		String sql = "SELECT p FROM Package p";
		Query query = em.createQuery(sql);
		List<Package> packages = query.getResultList();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Package>>() {}.getType();
		String jsonString = gson.toJson(packages, type);
		em.close();
		factory.close();
		//jsonString = "[{\"packages\": " + jsonString + "}]";
		return jsonString;
			
	}
}
