package app.DAOs;

public interface IDAO<Entity, DTO, ID> {

    Entity create(Entity entity);
    
}
