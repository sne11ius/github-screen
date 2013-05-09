package nu.wasis.github.screen;

import java.util.Date;

import nu.wasis.github.screen.model.GithubConfiguration;
import nu.wasis.github.screen.model.Screen;
import nu.wasis.github.screen.service.GithubService;

import org.apache.log4j.Logger;

/**
 * Hello world!
 * 
 */
public class App {

    private static final Logger LOG = Logger.getLogger(App.class);

    public static void main(final String[] args) throws Exception {
        final GithubConfiguration config = new GithubConfiguration("sne11ius", "jlog", new Date());

        final Screen screen = GithubService.INSTANCE.loadScreen(config);
        LOG.debug(screen);
    }
}
