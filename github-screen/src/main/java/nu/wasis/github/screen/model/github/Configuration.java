package nu.wasis.github.screen.model.github;

import java.util.Date;

public class Configuration {

    private final String username;
    private final String reponame;
    private final Date date;

    public Configuration(final String username, final String reponame, final Date date) {
        this.username = username;
        this.reponame = reponame;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getReponame() {
        return reponame;
    }

    public Date getDate() {
        return date;
    }

}
