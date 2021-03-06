package com.stefanini.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Agente;
import com.stefanini.model.Infracoes;
import com.stefanini.model.Localinfracao;
import com.stefanini.model.Tipoinfracao;
import com.stefanini.service.AgenteService;
import com.stefanini.service.InfracoesService;
import com.stefanini.service.LocalinfracaoService;
import com.stefanini.service.TipoinfracaoService;

@Named("infracoesMB")
@SessionScoped
public class InfracoesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
    private InfracoesService infracoesService;
	
	@Inject
	private AgenteService agenteService;
	
	@Inject
	private TipoinfracaoService tipoInfracaoService;
	
	@Inject
	private LocalinfracaoService localinfracaoService;
    
	@Inject
    private Infracoes infracoes;
	
	private Integer idAgente;
	private Integer idTipoInfracao;
	private Integer idLocalInfracao;
	
	private Collection<Agente> listaAgente = new ArrayList<Agente>();
	private Collection<Tipoinfracao> listaTipoInfracao = new ArrayList<Tipoinfracao>();
	private Collection<Localinfracao> listaLocalInfracao = new ArrayList<Localinfracao>();
	
	private Collection<Infracoes> lista = new ArrayList<Infracoes>();



	public Collection<Infracoes> getLista() {
		return lista;
	}

	public void setLista(Collection<Infracoes> lista) {
		this.lista = lista;
	}

	public Infracoes getInfracoes() {
		if(infracoes == null){
			infracoes = new Infracoes();
		}
		return infracoes;
	}

	public void setInfracoes(Infracoes infracoes) {
		this.infracoes = infracoes;
	}
	
/*	public void listar(){
		
		this.agenteService.listar();
	}*/
	
	@PostConstruct
	public void inicia(){
		listaAgente = agenteService.listar();
		listaTipoInfracao = tipoInfracaoService.listar();
		listaLocalInfracao = localinfracaoService.listar();
		lista = infracoesService.listar();
	}
	
	public void cadastrarInfracoes(){
		
		this.infracoes.setPlaca("teste");
		
		Tipoinfracao tipo = new Tipoinfracao();
		for(Tipoinfracao tp : listaTipoInfracao){
			if(tp.getIdTipoInfracao().equals(this.idTipoInfracao)){
				tipo = tp;
			}
		}
		this.infracoes.setTipoInfracao(tipo);
		
		Agente agente = new Agente();
		for(Agente ag : listaAgente){
			if(ag.getIdAgente().equals(this.idAgente)){
				agente = ag;
			}
		}
		
		this.infracoes.setAgente(agente);
		
		Localinfracao local = new Localinfracao();
		for(Localinfracao lc : listaLocalInfracao){
			if(lc.getIdLocalInfracao().equals(this.idLocalInfracao)){
				local = lc;
			}
		}
		this.infracoes.setLocalInfracao(local);
		
		try{
			infracoesService.incluir(this.infracoes);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Infração cadastrada com sucesso!"));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Infração não cadastrada!"));
		}
					
		this.infracoes = new Infracoes();
	}
	
	public void alterar(){
		
		this.infracoes.setPlaca("teste");
		
		Tipoinfracao tipo = new Tipoinfracao();
		for(Tipoinfracao tp : listaTipoInfracao){
			if(tp.getIdTipoInfracao().equals(this.idTipoInfracao)){
				tipo = tp;
			}
		}
		this.infracoes.setTipoInfracao(tipo);
		
		Agente agente = new Agente();
		for(Agente ag : listaAgente){
			if(ag.getIdAgente().equals(this.idAgente)){
				agente = ag;
			}
		}
		this.infracoes.setAgente(agente);
		
		Localinfracao local = new Localinfracao();
		for(Localinfracao lc : listaLocalInfracao){
			if(lc.getIdLocalInfracao().equals(this.idLocalInfracao)){
				local = lc;
			}
		}
		this.infracoes.setLocalInfracao(local);
		
		try{
			procuraId();
			infracoesService.alterar(this.infracoes);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Infração alterada com sucesso!"));
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Infração não alterada!"));
		}
					
		this.infracoes = new Infracoes();
	}
	
	public void remover() {
		
		try {
			procuraId();
			infracoesService.remover(this.infracoes.getIdInfracao());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção", "Infração removida com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", "Infração não removida!"));
		}
		this.infracoes = new Infracoes();
	}
	
	private void procuraId() throws Exception {
		inicia();
		for (Infracoes inf : lista) {
			if(inf.getIdInfracao().equals(this.infracoes.getIdInfracao())){
				return;
			}
		}
		throw new Exception();
	}

	public Integer getIdAgente() {
		return idAgente;
	}

	public void setIdAgente(Integer idAgente) {
		this.idAgente = idAgente;
	}

	public Integer getIdTipoInfracao() {
		return idTipoInfracao;
	}

	public void setIdTipoInfracao(Integer idTipoInfracao) {
		this.idTipoInfracao = idTipoInfracao;
	}

	public Integer getIdLocalInfracao() {
		return idLocalInfracao;
	}

	public void setIdLocalInfracao(Integer idLocalInfracao) {
		this.idLocalInfracao = idLocalInfracao;
	}
	
	public String voltar() {
		return "/index.faces?faces-redirect=true";

	}
	
	public Collection<Infracoes> listar() {
		return this.infracoesService.listar();
	}

}
