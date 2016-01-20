package br.fucapi.model.bean.controlador;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.fucapi.model.bean.Cliente;
import br.fucapi.model.dao.DAO;
import br.fucapi.model.dao.JPAUtil;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Cliente cliente = new Cliente();
	public int codDep;
	
	public List<Cliente> getClientes(){
		return new DAO<Cliente>(Cliente.class).listaTodos();
	}
	
	public void gravar(int op) {
		
		System.out.println(op);
		
		if (op > 0){
			new DAO<Cliente>(Cliente.class).atualiza(this.cliente);
			this.cliente = new Cliente();
		}
		
		else{
			System.out.println("Cliente Gravado  " + this.cliente.getNome());

			new DAO<Cliente>(Cliente.class).adiciona(this.cliente);

			this.cliente = new Cliente();

		}
	}
	
	public void remove(int cod){
		
		System.out.println(cod);
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		Cliente excluir = em.find(Cliente.class, cod);
		
		new DAO<Cliente>(Cliente.class).remove(excluir);

	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void editar(int cod){
		
		this.cliente = new DAO<Cliente>(Cliente.class).buscaPorId(cod);
		
		
	}
}
