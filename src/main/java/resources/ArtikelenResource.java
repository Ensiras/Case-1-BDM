package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("artikelen")
//@Produces(MediaType.APPLICATION_JSON)
public class ArtikelenResource {

    @GET
    public String getTest() {


        return "Het werkt!";
    }

/*    @GET
    @Path("{id}")
    public AbstractArtikel getById(@PathParam("id") int id) {
        ArtikelDao dao = new ArtikelDao(EntityManagerWrapper.getEntityManager("MySQL"));
        return dao.getById(id);
    }*/
}
