package app.DAOs;

import java.util.List;

public interface IDAO<Entity,  ID> {

     Entity create(Entity entity);

     void delete(ID id);

     Entity update(Entity entity);

     Entity findById(ID id);

     List<Entity> getAll();
}
