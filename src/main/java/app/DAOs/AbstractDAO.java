package app.DAOs;
import app.DTOs.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AbstractDAO<Entity, DTO, ID> implements IDAO<Entity, DTO, ID>{

    protected static EntityManagerFactory emf;

    AbstractDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Entity create(Entity entity) {
       try (EntityManager em = emf.createEntityManager()) {
           em.getTransaction().begin();
           em.persist(entity);
           em.getTransaction().commit();
       }
       return entity;
    }
}
