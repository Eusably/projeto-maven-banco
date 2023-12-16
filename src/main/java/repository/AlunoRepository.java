package repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.mysql.cj.util.StringUtils;

import model.Aluno;
import model.Disciplina;
import model.seletor.AlunoSeletor;
import repository.base.AbstractCrudRepository;

@Stateless
public class AlunoRepository extends AbstractCrudRepository<Aluno>{
	
	@Inject
	private DisciplinaRepository disciplinaRepository;

	public List<Aluno> pesquisar(AlunoSeletor seletor) {
		String filtros = "";
		if(!StringUtils.isNullOrEmpty(seletor.getNome())) {
			filtros = String.format("where upper(a.nome) = upper('%s') ", seletor.getNome());
		}
		if(!StringUtils.isNullOrEmpty(seletor.getSobrenome())) {
			filtros += filtros.isEmpty() ? "where " : " and ";
			filtros += String.format(" upper(a.sobrenome) = upper('%s') ", seletor.getSobrenome());
		}
		if(seletor.getIdade() != null) {
			filtros += filtros.isEmpty() ? "where " : " and ";
			filtros += String.format(" a.idade = %s ", seletor.getIdade());
		}
		if(!StringUtils.isNullOrEmpty(seletor.getSerie())) {
			filtros += filtros.isEmpty() ? "where " : " and ";
			filtros += String.format(" upper(a.serie) = upper('%s') ", seletor.getSerie());
		}
		if(!StringUtils.isNullOrEmpty(seletor.getSexo())) {
			filtros += filtros.isEmpty() ? "where " : " and ";
			filtros += String.format(" upper(a.sexo) = upper('%s') ", seletor.getSexo());
		}
		String sql = String.format("from Aluno a %s", filtros);
		return super.em.createQuery(sql).getResultList();
	}

	@Transactional
	public void matricular(Integer idAluno, String codigoDisciplina) throws Exception {
		Aluno aluno = super.consultar(idAluno);
		if(aluno == null) {
			throw new Exception("Aluno não encontrado com este ID");
		}
		Disciplina disciplina = this.disciplinaRepository.consultar(codigoDisciplina);
		if(disciplina == null) {
			throw new Exception("Disciplina não encontrada com este código");
		}
		
		boolean adicionado = aluno.getDisciplinas().stream().anyMatch(d -> d.getCodigo().equals(codigoDisciplina));		
		if(adicionado) {
			throw new Exception("Aluno já está matriculado nesta disciplina");
		}
		
		aluno.getDisciplinas().add(disciplina);
		super.atualizar(aluno);
	}

	@Transactional
	public void cancelarMatricula(Integer idAluno, String codigoDisciplina) throws Exception {
		Aluno aluno = super.consultar(idAluno);
		if(aluno == null) {
			throw new Exception("Aluno não encontrado com este ID");
		}
		Disciplina disciplina = this.disciplinaRepository.consultar(codigoDisciplina);
		if(disciplina == null) {
			throw new Exception("Disciplina não encontrada com este código");
		}
		
		boolean naoadicionado = aluno.getDisciplinas().stream().noneMatch(d -> d.getCodigo().equals(codigoDisciplina));		
		if(naoadicionado) {
			throw new Exception("Aluno não está matriculado nesta disciplina");
		}
		
		aluno.getDisciplinas().remove(disciplina);
		super.atualizar(aluno);
	}

	@Transactional
	public void removerAluno(Integer id) throws Exception {
		Aluno aluno = super.consultar(id);
		if(aluno == null) {
			throw new Exception("Aluno não encontrado com este ID");
		}
		super.remover(aluno);
	}
	
}
