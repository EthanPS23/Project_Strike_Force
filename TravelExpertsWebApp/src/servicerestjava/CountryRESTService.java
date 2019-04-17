package servicerestjava;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Country;

/* Gets all of the countries from the mySQL Database.
 * Author: Ethan Shipley
 * Course CMPP 264
 * Date: April 7 2019
 */
@Path("/countries")
public class CountryRESTService {	
	// http://localhost:8080/TravelExpertsWebApp/rest/countries/getallcountries
	@GET
	@Path("/getallcountries")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCountries()
	{
		EntityManagerFactory factory = 
		Persistence.createEntityManagerFactory("TravelExpertsWebApp");
		EntityManager em = factory.createEntityManager();
		String sql = "SELECT c FROM Country c";
		Query query = em.createQuery(sql);
		List<Country> country = query.getResultList();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Country>>() {}.getType();
		String jsonString = gson.toJson(country, type);
		em.close();
		factory.close();
		return jsonString;
	}
}
