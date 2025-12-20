import java.util.ArrayList;

public class Exam {
    private String examName;
    private int duration;
    private ArrayList<Question> questions;

    public Exam(String examName, int duration) {
        this.examName = examName;
        this.duration = duration;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Exam{name='" + examName + "', duration=" + duration +
                " minutes, questions=" + questions + "}";
    }
}

