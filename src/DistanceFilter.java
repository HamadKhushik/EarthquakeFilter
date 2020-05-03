public class DistanceFilter implements Filter {
    private Location loc;
    private double distMax;
    private String name;

    public DistanceFilter(Location l, double max, String nameAssigned) {
        loc = l;
        distMax = max;
        name = nameAssigned;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(loc) < distMax;
    }

    public String getName() {
        return name;
    }
}
