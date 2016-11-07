package com.stefanini.bean;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.service.AgenteService;

@Named("agenteMB")
@SessionScoped
public class AgenteBean {

	    @Inject
	    private AgenteService agenteService;
	    
	    private String nome;
	    private String dtContratacao;
	    private String tempoServico; 
	   

	    public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDtContratacao() {
			return dtContratacao;
		}


		public void setDtContratacao(String dtContratacao) {
			this.dtContratacao = dtContratacao;
		}

		public String getTempoServico() {
			return tempoServico;
		}

		public void setTempoServico(String tempoServico) {
			this.tempoServico = tempoServico;
		}

		public String chamar() {	
			System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + this.nome + " " + getNome());
	        return "/pages/teste";
	    }
		
		public String voltar() {	
			System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + this.nome + " " + getNome());
	        return "../index";
	    }

	}
