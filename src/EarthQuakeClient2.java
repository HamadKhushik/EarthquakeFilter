import java.util.ArrayList;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        //Filter f = new MinMagFilter(4.0);
        /* Original Code
        ArrayList<QuakeEntry> m7 = filter(list, f);
        for (QuakeEntry qe : m7) {
            System.out.println(qe);
        }*/
        // test MagnitudeFilter() and DepthFilter()
        Filter f1 = new MagnitudeFilter(3.5, 4.5, "Magnitude");
        Filter f2 = new DepthFilter(-55000.0, -20000.0, "Depth");
        ArrayList<QuakeEntry> m7 = filter(list, f1);
        ArrayList<QuakeEntry> m8 = filter(m7, f2);
        for (QuakeEntry qe : m8) {
            System.out.println(qe);
        }
        System.out.println("The number of quakes matching the criteria are : " + m8.size());
        /* test Distance and Phrase filter
        Location lo = new Location(39.7392, -104.9903);
        Filter f3 = new DistanceFilter(lo, 1000000, "Distance");
        Filter f4 = new PhraseFilter("end", "a", "Phrase");
        ArrayList<QuakeEntry> m9 = filter(list, f3);
        ArrayList<QuakeEntry> m = filter(m9, f4);
        for (QuakeEntry qe : m) {
            System.out.println(qe);
        }
        System.out.println("The number of quakes matching the criteria are : " + m.size());*/
    }

    public ArrayList<QuakeEntry> readData() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        return list;
    }

    public void printData(ArrayList<QuakeEntry> q) {
        for (QuakeEntry qe : q) {
            System.out.println(qe);
        }
        System.out.println("The total number of results are : " + q.size());
    }

    public void testMatchAllFilter() {
        EarthQuakeClient2 eq2 = new EarthQuakeClient2();
        ArrayList<QuakeEntry> list = eq2.readData();
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "o", "Phrase"));
        ArrayList<QuakeEntry> result = filter(list, maf);
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }
        String s = maf.getName();
        System.out.println("Filters used are : " + s);
        System.out.println("The number of quakes matching the criteria are : " + result.size());
    }

    public void testMatchAllFilter2() {
        EarthQuakeClient2 eq2 = new EarthQuakeClient2();
        ArrayList<QuakeEntry> list = eq2.readData();
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "Magnitude"));
        Location loc = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(loc, 3000000, "Distance"));
        maf.addFilter(new PhraseFilter("any", "e", "Phrase"));
        ArrayList<QuakeEntry> result = filter(list, maf);
        for (QuakeEntry qe : result) {
            System.out.println(qe);
        }
        eq2.printData(result);
        String s = maf.getName();
        System.out.println("Filters used are : " + s);
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for (QuakeEntry qe : list) {
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }

    public static void main(String[] args) {
        EarthQuakeClient2 ec = new EarthQuakeClient2();
        //ec.quakesWithFilter();
        ec.testMatchAllFilter2();
    }

}

