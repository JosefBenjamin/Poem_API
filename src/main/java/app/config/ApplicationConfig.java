package app.config;

import app.DAOs.PoemDAO;
import app.routes.Routes;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import jakarta.persistence.EntityManagerFactory;

public class ApplicationConfig {

    public static void configuration(JavalinConfig config){

        //TODO: Dependencies
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        PoemDAO poemDAO = new PoemDAO(emf);        // build DAO with entity class wired in its ctor
        Routes routes = new Routes(poemDAO);        // inject DAO into routes

        config.showJavalinBanner = false;
        config.bundledPlugins.enableRouteOverview("/routes");
        config.router.contextPath = "/api/v1"; // base path for all endpoints

        config.router.apiBuilder(routes.getRoutes()); // mount no-arg route group
    }

    public static Javalin startServer(int port) {
        Javalin app = Javalin.create(ApplicationConfig::configuration);
        app.start(port);
        return app;
    }

    public static void stopServer(Javalin app) {
        app.stop();
    }
}
