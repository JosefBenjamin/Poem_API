package app.DAOs;

import java.util.List;

public interface IDAO<Entity, DTO,  ID> {

     Entity create(Entity entity);

     void delete(ID id);

     Entity updateEntity(ID id, Entity entity);

     DTO updateDTO(ID id, DTO dto);

     Entity findById(ID id);

     List<Entity> getAll();
}
