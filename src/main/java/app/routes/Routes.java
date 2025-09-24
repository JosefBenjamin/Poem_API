package app.routes;
import app.DAOs.PoemDAO;
import app.entities.Poem;
import io.javalin.apibuilder.EndpointGroup;
import static io.javalin.apibuilder.ApiBuilder.*;
import jakarta.persistence.EntityManagerFactory;

public class Routes {

    private EntityManagerFactory emf;
    private PoemDAO poemDAO;


    public EndpointGroup getRoutes() {
        return () -> {
            get("/", ctx -> ctx.result("Hello World"));
            path("/poems", () -> {
                get("/{id}", ctx -> {
                    Integer id = Integer.parseInt(ctx.pathParam("id"));
                    Poem poem = poemDAO.getById(id);
                    ctx.json(poem);
                });
            });
            // get("/", ctx -> ctx.result("Hello World"));
            // path("/highscores", highscoresRoutes.getRoutes());
            // path("/highscore", highscoreRoutes.getRoutes());
        };
    }


}
