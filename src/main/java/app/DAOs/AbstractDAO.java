package app.DAOs;
import app.DTOs.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public abstract class AbstractDAO<Entity, ID> implements IDAO<Entity, ID>{

    public static EntityManagerFactory emf;
    public Class<Entity> entityClass;

    /**
     * Constructor that initializes the AbstractDAO with the EntityManagerFactory
     * @param emf EntityManagerFactory used for database operations
     */
    AbstractDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Creates a new entity in the database
     * @param entity The entity object to be persisted
     * @return The created entity with any generated values (like ID)
     */
    public Entity create(Entity entity) {
       try (EntityManager em = emf.createEntityManager()) {
           em.getTransaction().begin();
           em.persist(entity);
           em.getTransaction().commit();
       }
       return entity;
    }

    /**
     * Deletes an entity from the database by its ID
     * @param id The unique identifier of the entity to delete
     */
    public void delete(ID id) {
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "DELETE FROM " + entityClass.getSimpleName() + " a WHERE a.id = :id";
            em.createQuery(jpql).setParameter("id",id)
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

    /**
     * Updates an existing entity in the database
     * @param entity The entity object with updated values
     * @return The updated entity
     */
    public Entity update(Entity entity) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }
        return entity;
    }

    /**
     * Finds and retrieves a single entity from the database by its ID
     * @param id The unique identifier of the entity to find
     * @return The entity if found, or throws exception if not found
     */
    public Entity findById(ID id){
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT FROM " + entityClass.getSimpleName() + " a WHERE a.id = :id";
            return em.createQuery(jpql, entityClass)
                    .setParameter("id",id)
                    .getSingleResult();
        }
    }

    /**
     * Retrieves all entities of this type from the database
     * @return A list containing all entities, or empty list if none found
     */
    public List<Entity> getAll(){
        try(EntityManager em = emf.createEntityManager()) {
            String jpql = "SELECT a FROM " + entityClass.getSimpleName() + " a";
            return em.createQuery(jpql, entityClass).getResultList();
        }
    }
}
