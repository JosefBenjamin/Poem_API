package app.DAOs;

import java.util.List;

public interface IDAO<Entity, DTO,  ID> {

     Entity createEntity(Entity entity);

     DTO createDTO(DTO dot);

     void delete(ID id);

     Entity updateEntity(ID id, Entity entity);

     DTO updateDTO(ID id, DTO dto);

     Entity findById(ID id);

     List<Entity> getAll();
}
