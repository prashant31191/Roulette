package resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import roulette.*;

@Path("/tables")
public class TablesResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table> getTabels() {
		List<Table> tables = new ArrayList<Table>();
		tables.addAll(GameRoulette.getInstance().getTables());
		return tables;
	}
}
