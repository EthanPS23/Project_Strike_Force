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

import model.Provstate;

/* Gets all of the provinces or states depending on what country id is passed.
 * Author: Ethan Shipley
 * Course CMPP 264
 * Date: April 7 2019
 */
@Path("/provstates")
public class ProvStateRESTService {
	// http://localhost:8080/TravelExpertsWebApp/rest/provstates/getprovstates/CA
	@GET
	@Path("/getprovstates/{ countryid }")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProvState(@PathParam("countryid") String countryid)
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExpertsWebApp");
		EntityManager em = factory.createEntityManager();
		String sql = "SELECT p FROM Provstate p WHERE p.countryId= '" + countryid + "'";
		Query query = em.createQuery(sql);
		List<Provstate> provstates = query.getResultList();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Provstate>>() {}.getType();
		String jsonString = gson.toJson(provstates, type);
		em.close();
		factory.close();
		return jsonString;
	}
	
	@GET
	@Path("/getprovstatesapp/{ countryid }")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProvStateApp(@PathParam("countryid") String countryid)
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("TravelExpertsWebApp");
		EntityManager em = factory.createEntityManager();
		String sql = "SELECT p FROM Provstate p WHERE p.countryId= '" + countryid + "'";
		Query query = em.createQuery(sql);
		List<Provstate> provstates = query.getResultList();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Provstate>>() {}.getType();
		String jsonString = gson.toJson(provstates, type);
		em.close();
		factory.close();
		jsonString = "[{\"provstates\": " + jsonString + "}]";
		return jsonString;
	}
}
