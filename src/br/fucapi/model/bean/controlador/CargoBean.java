package br.fucapi.model.bean.controlador;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.fucapi.model.bean.Cargo;
import br.fucapi.model.dao.DAO;
import br.fucapi.model.dao.JPAUtil;

@ManagedBean
@ViewScoped
public class CargoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Cargo cargo = new Cargo();
	public int codDep;
	
	public List<Cargo> getCargos(){
		return new DAO<Cargo>(Cargo.class).listaTodos();
	}
	
	public void gravar(int op) {
		
		System.out.println(op);
		
		if (op > 0){
			new DAO<Cargo>(Cargo.class).atualiza(this.cargo);
			this.cargo = new Cargo();
		}
		
		else{
			System.out.println("Departamento Gravado  " + this.cargo.getDescricao());

			new DAO<Cargo>(Cargo.class).adiciona(this.cargo);

			this.cargo = new Cargo();

		}
	}
	
	public void remove(int cod){
		
		System.out.println(cod);
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		Cargo excluir = em.find(Cargo.class, cod);
		
		new DAO<Cargo>(Cargo.class).remove(excluir);

	}
	
	public Cargo getCargo() {
		return cargo;
	}
	
	public void editar(int cod){
		
		this.cargo = new DAO<Cargo>(Cargo.class).buscaPorId(cod);
		
	}	
	
}
