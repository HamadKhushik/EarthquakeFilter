public class DepthFilter implements Filter {
    private double depMin;
    private double depMax;
    private String name;

    public DepthFilter(double min, double max, String nameAssigned) {
        depMin = min;
        depMax = max;
        name = nameAssigned;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= depMin && qe.getDepth() <= depMax;
    }

    public String getName() {
        return name;
    }

}
