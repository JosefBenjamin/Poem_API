package app.DAOs;
import app.DTOs.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public abstract class AbstractDAO<Entity, DTO, ID> implements IDAO<Entity, DTO ,ID>{

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
    public Entity createEntity(Entity entity) {
       try (EntityManager em = emf.createEntityManager()) {
           em.getTransaction().begin();
           em.persist(entity);
           em.getTransaction().commit();
       }
       return entity;
    }

    public DTO createDTO(DTO dto){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(dto);
            em.getTransaction().commit();
        }
        return dto;
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
    public Entity updateEntity(ID id, Entity entity) {
        Entity result;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Entity existingEntity = em.find(entityClass, id);

            if(existingEntity == null){
                throw new RuntimeException("Entity with id " + id + " not found");
            }

            result = em.merge(entity);
            em.getTransaction().commit();
        }
        return result;
    }

    public DTO updateDTO(ID id, DTO dto) {
        DTO result;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Entity existingEntity = em.find(entityClass, id);
            if(existingEntity == null){
                throw new RuntimeException("Entity with id " + id + " not found");
            }
            result = em.merge(dto);
            em.getTransaction().commit();
        }
        return result;
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
