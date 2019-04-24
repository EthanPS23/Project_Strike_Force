package servicerestjava;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Country;

/* Gets all of the provinces or states depending on what country id is passed.
 * Author: James Cockriell
 * Course CMPP 264
 * Date: April 10 2019
 */

/*@Path("/packages")
public class PackagesRESTService {
	
	@GET
	@Path("/getallpackages")
	@Produces(MediaType.APPLICATION_JSON)
	public String getallpackages()
	{
		EntityManagerFactory factory = 
		Persistence.createEntityManagerFactory("TravelExpertsWebApp");
		EntityManager em = factory.createEntityManager();
		String sql = "SELECT p FROM Package p";
		Query query = em.createQuery(sql);
		List<Package> packages = query.getResultList();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Package>>() {}.getType();
		String jsonString = gson.toJson(packages, type);
		em.close();
		factory.close();
		return jsonString;
	}*/
}
