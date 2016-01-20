package br.fucapi.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

		private static EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("WEBN3_Fernando");

		public EntityManager getEntityManager() {
			return emf.createEntityManager();
		}

		public void close(EntityManager em) {
			em.close();
		}
}
