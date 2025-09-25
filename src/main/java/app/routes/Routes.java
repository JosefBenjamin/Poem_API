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


    public EndpointGroup getRoutes() {
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
        };
    }
}
