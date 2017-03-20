import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;
  
@Path("CalculetteService")
public class Calculette {
	@GET
	@Path("/Addition/{i}/{j}")
	@Produces(MediaType.TEXT_PLAIN)
	public String addition(@PathParam("i") int i, @PathParam("j") int j) {
	int res = i + j;
	return "Resultat de l'addition : " + res;
	}
	@GET
	@Path("/Multiplication/{i}/{j}")
	@Produces(MediaType.TEXT_PLAIN)
	public String multiplication(@PathParam("i") int i, @PathParam("j") int j) {
	int res = i * j;
	return "Resultat de la multiplication : " + res;
	}
	@GET
	@Path("/Soustraction/{i}/{j}")
	@Produces(MediaType.TEXT_PLAIN)
	public String soustraction(@PathParam("i") int i, @PathParam("j") int j) {
	int res = i - j;
	return "Resultat de la soustraction : " + res;
	}
}
