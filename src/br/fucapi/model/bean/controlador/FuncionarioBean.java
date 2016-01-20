package br.fucapi.model.bean.controlador;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.fucapi.model.bean.Funcionario;
import br.fucapi.model.dao.DAO;
import br.fucapi.model.dao.JPAUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{
		
	private static final long serialVersionUID = 1L;
	private Funcionario funcionario = new Funcionario();
	public int codDep;
	
	public List<Funcionario> getFuncionarios(){
		return new DAO<Funcionario>(Funcionario.class).listaTodos();
	}
	
	public void gravar(int op) {
		
		System.out.println(op);
		
		if (op > 0){
			new DAO<Funcionario>(Funcionario.class).atualiza(this.funcionario);
			this.funcionario = new Funcionario();
		}
		
		else{
			System.out.println("Funcionario Gravado  " + this.funcionario.getNome());

			new DAO<Funcionario>(Funcionario.class).adiciona(this.funcionario);

			this.funcionario = new Funcionario();

		}
	}
	
	public void remove(int cod){
		
		System.out.println(cod);
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		Funcionario excluir = em.find(Funcionario.class, cod);
		
		new DAO<Funcionario>(Funcionario.class).remove(excluir);

	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void editar(int cod){
		
		this.funcionario = new DAO<Funcionario>(Funcionario.class).buscaPorId(cod);
		
		
	}
}
