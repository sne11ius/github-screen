package nu.wasis.github.screen.service;

import java.text.SimpleDateFormat;

import nu.wasis.github.screen.model.GithubConfiguration;
import nu.wasis.github.screen.model.Pixel;
import nu.wasis.github.screen.model.Screen;

import org.apache.log4j.Logger;
import org.jboss.resteasy.client.ClientRequest;

public class GithubService {

    private static final String GITHUB_SCREEN_ENDPOINT = "https://github.com/users/{username}/contributions_calendar_data";
    // https://github.com/sne11ius?tab=contributions&from=2013-02-20&_pjax=%23contribution-activity
    // private static final String GITHUB_SCREEN_ENDPOINT =
    // "https://github.com/{username}?tab=contributions&from={date}";// "&_pjax=%23contribution-activity";
    private static final Logger LOG = Logger.getLogger(GithubService.class);

    public static final GithubService INSTANCE = new GithubService();
    private static final SimpleDateFormat DATE_PARSER = new SimpleDateFormat("yyyy/MM/dd");

    private GithubService() {
    }

    public Screen loadScreen(final GithubConfiguration configuration) throws Exception {
        final String calendarData = getCalendarData(configuration);
        // LOG.debug("=================");
        // LOG.debug(calendarData);
        final String[] data = calendarData.replace("[", "").replace("]", "").replace("\"", "").split(",");
        final Screen screen = new Screen();
        for (int i = 0; i < data.length - 1; i += 2) {
            final String date = data[i];
            final int contributions = Integer.parseInt(data[i + 1]);
            // LOG.debug("date: " + date);
            // LOG.debug("contributions: " + contributions);
            screen.addPixel(new Pixel(DATE_PARSER.parse(date), contributions));
        }
        // for (final String string : data) {
        // LOG.debug(string);
        // }
        // LOG.debug(data);
        // LOG.debug("=================");
        return screen;
    }

    private String getCalendarData(final GithubConfiguration configuration) throws Exception {
        // https://github.com/sne11ius?tab=contributions&from=2013-02-20&_pjax=%23contribution-activity
        final ClientRequest request = new ClientRequest(GITHUB_SCREEN_ENDPOINT);
        request.pathParameter("username", configuration.getUsername());
        return request.get(String.class).getEntity();
    }

}
