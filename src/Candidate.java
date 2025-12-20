public class Candidate extends Person {

    public Candidate(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getRole() {
        return "Candidate";
    }
}
