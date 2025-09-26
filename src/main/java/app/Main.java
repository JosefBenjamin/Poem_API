package app;

import app.DAOs.PoemDAO;
import app.config.ApplicationConfig;
import app.config.HibernateConfig;
import app.entities.Poem;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.InternalServerErrorResponse;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Logger debugLogger = LoggerFactory.getLogger("app");

    public static void main(String[] args) {

        //Dependencies
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        PoemDAO poemDAO = new PoemDAO(emf);
        Poem poem = new Poem("Tester", "Tester testing test", "TestStyle");
        Poem poem1 = new Poem("Two Test", "Tester testing test", "TestStyle");
        Poem poem2 = new Poem("Three Test", "Tester testing test", "TestStyle");

        poemDAO.createEntity(poem);
        poemDAO.createEntity(poem1);
        poemDAO.createEntity(poem2);

        
         ApplicationConfig.startServer(7096);

         /*
        // Initialize Javalin
        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false; // Disable default Javalin banner
        }).start(7070);

        // Set up routes
        app.get("/", ctx -> {
            logger.info("Handling request to /");
            ctx.result("Hello, Javalin with Logging!");
        });
        app.get("/error", ctx -> {
            logger.error("An error endpoint was accessed");
            throw new RuntimeException("This is an intentional error for logging demonstration.");
        });

        // Log the server start
        logger.info("Javalin application started on http://localhost:7070");
        debugLogger.debug("Debug log message from Main class during startup");

        // Exception handling example
        app.exception(Exception.class, (e, ctx) -> {
            logger.error("An exception occurred: {}", e.getMessage(), e);
            ctx.status(500).result("Internal Server Error");
        });
        app.error(HttpStatus.INTERNAL_SERVER_ERROR, ctx -> {
            logger.error("Bummer: {}", ctx.status());
            Map<String, String> msg = Map.of("error", "Internal Server Error, dude!", "status",
                    String.valueOf(ctx.status()));
            throw new InternalServerErrorResponse("Off limits!", msg);
        });

          */
    }


}
