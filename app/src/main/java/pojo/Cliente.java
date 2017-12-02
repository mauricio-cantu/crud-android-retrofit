package pojo;

import java.io.Serializable;

public class Cliente implements Serializable {

	private long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String genero;
	private String estado;
	private boolean tecnico;
	private boolean superiorComp;
	private boolean superiorIncomp;
	
	public Cliente() {
	}

	public Cliente(long id, String nome, String cpf, String telefone, String genero, String estado, boolean tecnico,
			boolean superiorComp, boolean superiorIncomp) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.genero = genero;
		this.estado = estado;
		this.tecnico = tecnico;
		this.superiorComp = superiorComp;
		this.superiorIncomp = superiorIncomp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isTecnico() {
		return tecnico;
	}

	public void setTecnico(boolean tecnico) {
		this.tecnico = tecnico;
	}

	public boolean isSuperiorComp() {
		return superiorComp;
	}

	public void setSuperiorComp(boolean superiorComp) {
		this.superiorComp = superiorComp;
	}

	public boolean isSuperiorIncomp() {
		return superiorIncomp;
	}

	public void setSuperiorIncomp(boolean superiorIncomp) {
		this.superiorIncomp = superiorIncomp;
	}

	@Override
	public String toString() {
		return this.getNome() +
				" : " + this.getGenero() +
                " : " + this.getEstado() +
                " : " + (this.isTecnico() ? "Técnico" : "") +
                " : " + (this.isSuperiorIncomp() ? "Sup. Incomp." : "")  +
                " : " + (this.isSuperiorComp() ? "Sup. Comp." : ""
        );
	}
}
