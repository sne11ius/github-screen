package nu.wasis.github.screen;

import java.io.File;
import java.io.FileOutputStream;

import nu.wasis.github.screen.model.Screen;
import nu.wasis.github.screen.service.GithubService;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class GithubScreen {

    private static final Logger LOG = Logger.getLogger(GithubScreen.class);

    private static final String USERNAME = "screen-controller";

    public static void main(final String[] args) throws Exception {
        final Screen screen = new Screen("/home/cornelius/src/github-screen/src/main/resources/awesome.png");
        final String resetRepositoryCommand = GithubService.INSTANCE.getResetRepositoryCommand("https://github.com/" + USERNAME + "/screen-slave");
        final String printSreenCommand = GithubService.INSTANCE.getPrintSreenCommand(screen);

        final String filename = "/home/cornelius/bin/doit";
        final File outFile = new File(filename);
        final FileOutputStream outStream = new FileOutputStream(outFile);

        IOUtils.write(resetRepositoryCommand, outStream);
        IOUtils.write("git config user.email '" + USERNAME + "@example.com'; git config user.name '" + USERNAME + "'; ", outStream);
        IOUtils.write(printSreenCommand, outStream);

        outStream.close();

        LOG.debug("Appended stuff to " + filename);
    }
}
