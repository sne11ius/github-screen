package nu.wasis.github.screen;

import java.util.Date;

import nu.wasis.github.screen.model.Screen;
import nu.wasis.github.screen.model.github.Configuration;
import nu.wasis.github.screen.service.GithubService;

import org.apache.log4j.Logger;

public class App {

    private static final Logger LOG = Logger.getLogger(App.class);

    public static void main(final String[] args) throws Exception {
        final Configuration config = new Configuration("sne11ius", "jlog", new Date());

        final Screen screen = GithubService.INSTANCE.loadScreen(config);
        LOG.debug(screen);
    }
}
