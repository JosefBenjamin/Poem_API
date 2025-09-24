package app.DAOs;
import app.DTOs.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class AbstractDAO<Entity, DTO, ID> implements IDAO<Entity, DTO, ID>{

    public static EntityManagerFactory emf;
    public  Class<Entity> entityClass;

    AbstractDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public  Entity create(Entity entity) {
       try (EntityManager em = emf.createEntityManager()) {
           em.getTransaction().begin();
           em.persist(entity);
           em.getTransaction().commit();
       }
       return entity;
    }

    @Override
    public void delete(ID id) {
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "DELETE FROM " + entityClass.getSimpleName() + " a WHERE a.id = :id";
            em.createQuery(jpql).setParameter("id",id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public Entity update(Entity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }
        return entity;
    }

    public Entity getById(ID id){
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT FROM " + entityClass.getSimpleName() + " a WHERE a.id = :id";
            return em.createQuery(jpql, entityClass)
                    .setParameter("id",id)
                    .getSingleResult();
        }
    }
}
