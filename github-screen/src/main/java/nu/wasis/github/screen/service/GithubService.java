package nu.wasis.github.screen.service;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import nu.wasis.github.screen.model.Screen;

public class GithubService {
    private static final String contentfilename = "contentfile";

    public static final GithubService INSTANCE = new GithubService();

    private GithubService() {
    }

    public String getResetRepositoryCommand(final String githubUri) {
        final String deleteAll = "rm -rf ./* && rm -rf ./.git";
        final String init = "git init";
        final String createReadme = "echo 'wooohaha!' > README";
        final String createContentFile = "touch " + contentfilename;
        final String addAll = "git add *";
        final String commit = "git commit -am 'test'";
        final String addRemote = "git remote add origin " + githubUri;
        final String push = "git push -u --force origin master";
        return chain(deleteAll, init, createReadme, createContentFile, addAll, commit, addRemote, push);
    }

    public String getPrintSreenCommand(final Screen screen) {
        return getPrintSreenCommand(screen, new Date());
    }

    private String getPrintSreenCommand(final Screen screen, final Date baseDate) {
        final StringBuilder builder = new StringBuilder(10000);

        for (int x = 0; x < Screen.MAX_WIDTH; ++x) {
            for (int y = 0; y < Screen.MAX_HEIGHT; ++y) {
                final Color color = screen.getPixel(x, y);
                final int intensity = color.getGreen() / 10;
                for (int i = 0; i < intensity; ++i) {
                    final Date date = getDate(x, y, i);
                    final String dateString = date.toString();
                    builder.append("echo " + Math.random() + " > " + contentfilename + " && git commit -am 'x' --date '" + dateString + "'; ");
                }
            }
        }

        return builder.toString();
    }

    private String chain(final String... args) {
        String result = "";
        for (int i = 0; i < args.length - 1; ++i) {
            result += args[i] + " && ";
        }
        return result + args[args.length - 1];
    }

    public Date getDate(final int x, final int y, final int minuteOffset) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 1);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.YEAR, -1);
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        cal.add(Calendar.MINUTE, minuteOffset);

        cal.add(Calendar.WEEK_OF_YEAR, x);
        cal.add(Calendar.DAY_OF_WEEK, y);

        return cal.getTime();
    }

}
