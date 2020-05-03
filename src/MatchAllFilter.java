import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    private String name;

    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
        name = "";
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry q) {
        for (Filter f : filters) {
            if (!f.satisfies(q)) return false;
        }
        return true;
    }

    public String getName() {
        for (Filter f : filters) {
            name = name + f.getName() + " ";
        }
        return name;
    }
}
