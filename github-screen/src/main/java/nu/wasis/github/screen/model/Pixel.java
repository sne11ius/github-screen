package nu.wasis.github.screen.model;

import java.util.Date;

public class Pixel {

    private final Date date;
    private final int contributions;

    public Pixel(final Date date, final int contributions) {
        this.date = date;
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return "Pixel [date=" + date + ", contributions=" + contributions + "]";
    }

    public Date getDate() {
        return date;
    }

    public int getContributions() {
        return contributions;
    }

}
