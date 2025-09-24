package app.routes;

import io.javalin.apibuilder.EndpointGroup;

public class Routes {




    public EndpointGroup getRoutes() {
        return () -> {
            // get("/", ctx -> ctx.result("Hello World"));
            // path("/highscores", highscoresRoutes.getRoutes());
            // path("/highscore", highscoreRoutes.getRoutes());
        };
    }


}
