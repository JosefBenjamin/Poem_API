package app.DAOs;
import app.DTOs.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public abstract class AbstractDAO<Entity, ID> implements IDAO<Entity, ID>{

    public static EntityManagerFactory emf;
    public  Class<Entity> entityClass;

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

    public void delete(ID id) {
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "DELETE FROM " + entityClass.getSimpleName() + " a WHERE a.id = :id";
            em.createQuery(jpql).setParameter("id",id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

    public Entity update(Entity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }
        return entity;
    }

    public Entity findById(ID id){
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT FROM " + entityClass.getSimpleName() + " a WHERE a.id = :id";
            return em.createQuery(jpql, entityClass)
                    .setParameter("id",id)
                    .getSingleResult();
        }
    }

    public List<Entity> getAll(){
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT a FROM " + entityClass.getSimpleName() + " a";
            return em.createQuery(jpql, entityClass).getResultList();
        }
    }
}
