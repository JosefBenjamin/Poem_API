package app.DAOs;
import app.entities.*;
import app.DTOs.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class PoemDAO extends AbstractDAO<Poem,  Integer>{

    public PoemDAO(EntityManagerFactory emf) {
        super(emf);
    }
}
