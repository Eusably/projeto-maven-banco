package model.seletor;

public class AlunoSeletor {

	private String nome;
	private String sobrenome;
	private Integer idade;
	private String serie;
	private String sexo;

	private Long pagina;
	private Long limite;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Long getPagina() {
		return pagina;
	}

	public void setPagina(Long pagina) {
		this.pagina = pagina;
	}

	public Long getLimite() {
		return limite;
	}

	public void setLimite(Long limite) {
		this.limite = limite;
	}
	
	public Long getOffset() {
		return this.pagina + 1;
	}

}
