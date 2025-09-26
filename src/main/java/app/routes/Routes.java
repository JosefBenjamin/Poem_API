package app.routes;
import app.DAOs.PoemDAO;
import app.DTOs.PoemDTO;
import app.entities.Poem;
import io.javalin.apibuilder.EndpointGroup;
import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Routes {

    private EntityManagerFactory emf;
    private final PoemDAO poemDAO;

    public Routes(PoemDAO poemDAO) {
        this.poemDAO = poemDAO;
    }


    public EndpointGroup getRoutes() {
        return () -> {
            //get("/", ctx -> ctx.result("Hello World"));
                    get("/poem/{id}", ctx -> {
                    int id = Integer.parseInt(ctx.pathParam("id"));
                    Poem poem = poemDAO.findById(id);
                    if(poem == null) {
                        ctx.status(404).result("Poem not found");
                        return;
                    }
                    PoemDTO poemDTO = new PoemDTO();
                    ctx.status(HttpStatus.OK).json(poemDTO.convertToDTO(poem));
                });

            get("/poems", ctx ->{
                List<Poem> listOfPoems = poemDAO.getAll();
                List <PoemDTO> listOfDTOs = listOfPoems.stream()
                        .map((x) ->   new PoemDTO()
                                            .convertToDTO(x))
                                            .collect(Collectors.toList());
                ctx.status(HttpStatus.OK);
                ctx.json(listOfDTOs);
                });

            put("/poem/{id}", ctx -> {
                int id = Integer.parseInt(ctx.pathParam("id"));
                PoemDTO poemDTO = ctx.bodyAsClass(PoemDTO.class);
                PoemDTO updated = poemDAO.updateDTO(id, poemDTO);
                if(updated == null) {
                    ctx.status(HttpStatus.NOT_FOUND).result("Poem not found");
                    return;
                }
                ctx.json(poemDTO);
            });

            post("/poems", ctx -> {
                // Modtag og konverter en liste af digte (fra json til dto)
                PoemDTO[] body = ctx.bodyAsClass(PoemDTO[].class);
                // Gem alle digtene i databasen (dao) og modtag en liste af de nye digte
                List<PoemDTO> created = poemDAO.createFromList(body);
                ctx.status(HttpStatus.CREATED).json(created);
            });

            post("/poem", ctx -> {
                PoemDTO poemDTO = ctx.bodyAsClass(PoemDTO.class);
                PoemDTO newPoemDTO = poemDAO.createDTO(poemDTO);
                ctx.status(HttpStatus.CREATED);
                ctx.json(newPoemDTO);
            });

            delete("/poem/{id}", ctx -> {
                        int id = Integer.parseInt(ctx.pathParam("id"));
                        poemDAO.delete(id);
                        ctx.result("deleted: " + id);
                    });
        };

    }
}
