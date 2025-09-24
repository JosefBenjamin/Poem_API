package app.DAOs;

import java.util.List;

public interface IDAO<Entity, DTO, ID> {

     Entity create(Entity entity);

     void delete(ID id);

     Entity update(Entity entity);

     Entity getById(ID id);

    Entity getById(ID id);

    List<Entity> getAll();
}
