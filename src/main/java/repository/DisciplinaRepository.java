package repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

import model.Aluno;
import model.Disciplina;
import repository.base.AbstractCrudRepository;

@Stateless
public class DisciplinaRepository extends AbstractCrudRepository<Disciplina>{
	
	@Transactional
	public void cadastrar(Disciplina disciplina) throws Exception {
		if(this.consultar(disciplina.getCodigo()) == null) {
			super.inserir(disciplina);
		} else {
			throw new Exception("Disciplina já cadastrada com este código");
		}
	}	
	
	@Transactional
	public void editar(Disciplina disciplina) throws Exception {
		if(this.consultar(disciplina.getCodigo()) != null) {
			super.atualizar(disciplina);
		} else {
			throw new Exception("Disciplina não encontrada com este código");
		}
	}
	
	@Transactional
	public void remover(String codigo) throws Exception {
		Disciplina disciplina = this.consultar(codigo);
		if(disciplina != null) {
			super.remover(disciplina);
		} else {
			throw new Exception("Disciplina não encontrada com este código");
		}
	}
	
	public Disciplina consultar(String codigo) {
		List<Disciplina> lista = super.em.createQuery("from Disciplina where codigo = :codigo").setParameter("codigo", codigo).getResultList();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> consultarAlunos(String codigo) {
		return super.em.createNativeQuery("select a.* from eusably.matricula m \n"
				+ "join eusably.disciplina d on d.codigo = m.codigo_disciplina \n"
				+ "join eusably.aluno a on a.id = m.id_aluno \n"
				+ "where d.codigo = :codigo \n"
				+ "order by a.nome", Aluno.class)
				.setParameter("codigo", codigo)
				.getResultList();
	}
}
