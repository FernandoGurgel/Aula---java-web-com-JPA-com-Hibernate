package br.fucapi.model.bean.controlador;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.fucapi.model.bean.Atende;
import br.fucapi.model.dao.DAO;
import br.fucapi.model.dao.JPAUtil;

@ManagedBean
@ViewScoped
public class AtendeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Atende atendimento = new Atende();
	public int codFun;
	public int codCli;
	
	
	public List<Atende> getAtendimentos(){
		return new DAO<Atende>(Atende.class).listaTodos();
	}
	
	public void gravar(int op) {
		
		System.out.println(op);
		
		if (op > 0){
			new DAO<Atende>(Atende.class).atualiza(this.atendimento);
			this.atendimento = new Atende();
		}
		
		else{
			System.out.println("Atendimento Gravado  " + this.atendimento.getData());

			new DAO<Atende>(Atende.class).adiciona(this.atendimento);

			this.atendimento = new Atende();

		}
	}
	
	public void remove(int cod){
		
		System.out.println(cod);
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		Atende excluir = em.find(Atende.class, cod);
		
		new DAO<Atende>(Atende.class).remove(excluir);

	}
	
	public Atende getAtendimento() {
		return atendimento;
	}
	
	public void editar(int cod){
		
		this.atendimento = new DAO<Atende>(Atende.class).buscaPorId(cod);
		
		
	}
}
