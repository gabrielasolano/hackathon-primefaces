package com.stefanini.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Proprietario;
import com.stefanini.service.ProprietarioService;

@Named("proprietarioMB")
@SessionScoped
public class ProprietarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProprietarioService proprietarioService;

	@Inject
	private Proprietario proprietario;

	private Collection<Proprietario> lista = new ArrayList<Proprietario>();
	private Integer cpf;
	private String nome;
	private String endereco;
	private String bairro;
	private String cidade;
	private String uf;
	private String sexo;
	private String dtNascimento;

	@PostConstruct
	public void inicia() {
		lista = proprietarioService.listar();
	}

	public Proprietario getProprietario() {
		if (proprietario == null) {
			proprietario = new Proprietario();
		}
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Collection<Proprietario> getLista() {
		return lista;
	}

	public void setLista(Collection<Proprietario> lista) {
		this.lista = lista;
	}

	
	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public void cadastrarProprietario() {

		try {
			proprietarioService.incluir(getProprietario());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Cadastrado com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}

		this.proprietario = new Proprietario();
	}

	
	public void alterar(Integer id) {

		try {
			this.proprietario.setCpfProprietario(id);
			proprietarioService.alterar(this.proprietario);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Alterado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}

		this.proprietario = new Proprietario();

	}

	public String removerProprietario(Integer cpf) {

		try {
			proprietarioService.remover(cpf);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Removido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", e.getMessage()));
		}
		this.proprietario = new Proprietario();
		return "listarProprietario.faces?faces-redirect=true";
	}

	public String voltar() {
		return "listarProprietario.faces?faces-redirect=true";

	}

	public Collection<Proprietario> listar() {
		return this.proprietarioService.listar();
	}

	public String recuperaId() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		cpf = Integer.parseInt(params.get("cpf"));
		nome = params.get("nome");
		endereco = params.get("endereco");
		bairro = params.get("bairro");
		cidade = params.get("cidade");
		uf = params.get("uf");
		sexo = params.get("sexo");
		dtNascimento = params.get("dtNascimento");

		return "alterarProprietario?faces-redirect=true";
	}

}
