package services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Disciplina;
import repository.DisciplinaRepository;

@Path("/disciplina")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisciplinaService {

	@Inject
	private DisciplinaRepository disciplinaRepository;

	@GET
	public Response listar() {
		return Response.ok().entity(this.disciplinaRepository.pesquisarTodos()).build();
	}
	
	@GET
	@Path("/consultar")
	public Response consultar(@QueryParam("codigo") String codigo) {
		return Response.ok().entity(this.disciplinaRepository.consultar(codigo)).build();
	}
	
	@GET
	@Path("/consultar-alunos")
	public Response consultarAlunos(@QueryParam("codigo") String codigo) {
		return Response.ok().entity(this.disciplinaRepository.consultarAlunos(codigo)).build();
	}

	@POST
	public Response cadastrar(Disciplina disciplina) {
		try {
			this.disciplinaRepository.cadastrar(disciplina);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	/*{
	    "codigo": "PROG001",
	    "descricao": "Programação 001",
	    "professor": "Daiane Dorner"
	}*/
	@PUT
	public Response atualizar(Disciplina disciplina) {
		try {
			this.disciplinaRepository.editar(disciplina);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@DELETE
	public Response remover(@QueryParam("codigo") String codigo) {
		try {
			this.disciplinaRepository.remover(codigo);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

}
