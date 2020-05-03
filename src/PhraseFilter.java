public class PhraseFilter implements Filter {
    private String type;
    private String phrase;
    private String name;

    public PhraseFilter(String where, String what, String nameAssigned) {
        type = where;
        phrase = what;
        name = nameAssigned;
    }

    public boolean satisfies(QuakeEntry qe) {
        if (type.equals("start")) {
            return qe.getInfo().startsWith(phrase);
        } else if (type.equals("end")) {
            return qe.getInfo().endsWith(phrase);
        } else if (type.equals("any")) {
            return qe.getInfo().contains(phrase);
        } else
            return false;
    }

    public String getName() {
        return name;
    }
}
