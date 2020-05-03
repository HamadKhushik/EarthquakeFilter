public class MinMagFilter implements Filter {
    private double magMin;
    private String name;

    public MinMagFilter(double min, String nameAssigned) {
        magMin = min;
        name = nameAssigned;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin;
    }

    public String getName() {
        return name;
    }

}

