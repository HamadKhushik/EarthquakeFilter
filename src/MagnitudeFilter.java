public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
    private String name;

    public MagnitudeFilter(double min, double max, String nameAssigned) {
        magMin = min;
        magMax = max;
        name = nameAssigned;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }

    public String getName() {
        return name;
    }
}
