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
    private PoemDAO poemDAO;


    public EndpointGroup getRoutes(PoemDAO poemDAO) {
        return () -> {
            get("/", ctx -> ctx.result("Hello World"));
                get("/poem/{id}", ctx -> {
                    Integer id = Integer.parseInt(ctx.pathParam("id"));
                    Poem poem = poemDAO.findById(id);
                    ctx.json(poem);
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
                poemDTO = poemDAO.updateDTO(id, poemDTO);
                ctx.status(HttpStatus.OK);
                ctx.json(poemDTO);
            });

            post("/poems", ctx -> {
                // Modtag og konverter en liste af digte (fra json til dto)
                PoemDTO[] poemDTOS = ctx.bodyAsClass(PoemDTO[].class);
                // Gem alle digtene i databasen (dao) og modtag en liste af de nye digte
                List<PoemDTO> newPoemDTOs = poemDAO.createFromList(poemDTOS);
                ctx.status(HttpStatus.CREATED);
                ctx.json(newPoemDTOs);
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
