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

import model.Aluno;
import model.seletor.AlunoSeletor;
import repository.AlunoRepository;

@Path("/aluno")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlunoService {

	@Inject
	private AlunoRepository alunoRepository;

	@GET
	public Response listar() {
		return Response.ok().entity(this.alunoRepository.pesquisarTodos()).build();
	}
	
	@GET
	@Path("/{id}")
	public Response consultar(@PathParam("id") Integer id) {
		return Response.ok().entity(this.alunoRepository.consultar(id)).build();
	}

	/*{
	    "nome": "Bernardo",
	    "sobrenome": "Silva",
	    "idade": 18,
	    "serie": "3º ano",
	    "sexo": "Masculino",
	    "endereco": {
	        "logradouro": "Rua Gentil Sandin",
	        "bairro": "Praia Comprida",
	        "numero": 33,
	        "municipio": "São José",
	        "estado": "Santa Catarina",
	        "cep": 88103650
	    }
	}*/
	@POST
	public Response cadastrar(Aluno aluno) {
		try {
			this.alunoRepository.inserir(aluno);
			return Response.ok().entity(aluno).build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@PUT
	public Response atualizar(Aluno aluno) {
		try {
			this.alunoRepository.atualizar(aluno);
			return Response.ok().entity(aluno).build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	// pesquisar por nome ou outro atributo
	@POST
	@Path("/pesquisar")
	public Response pesquisar(AlunoSeletor seletor) {
		try {
			return Response.ok().entity(this.alunoRepository.pesquisar(seletor)).build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("/matricular/{idAluno}/{codigoDisciplina}")
	public Response matricular(@PathParam("idAluno") Integer idAluno, @PathParam("codigoDisciplina") String codigoDisciplina) {
		try {
			this.alunoRepository.matricular(idAluno, codigoDisciplina);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("/cancelar-matricula/{idAluno}/{codigoDisciplina}")
	public Response cancelarMatricula(@PathParam("idAluno") Integer idAluno, @PathParam("codigoDisciplina") String codigoDisciplina) {
		try {
			this.alunoRepository.cancelarMatricula(idAluno, codigoDisciplina);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response remover(@PathParam("id") Integer id) {
		try {
			this.alunoRepository.removerAluno(id);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

}
