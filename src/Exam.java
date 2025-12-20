import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exam {
    private String examName;
    private int duration;
    private List<Question> questions = new ArrayList<>();

    public Exam(String examName, int duration) {
        this.examName = examName;
        this.duration = duration;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    // SEARCH
    public Question findQuestionById(int id) {
        for (Question q : questions) {
            if (q.getQuestionId() == id) {
                return q;
            }
        }
        return null;
    }

    // FILTER
    public List<Question> filterByKeyword(String keyword) {
        return questions.stream()
                .filter(q -> q.getQuestionText().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // SORT
    public void sortById() {
        questions.sort(Comparator.comparingInt(Question::getQuestionId));
    }

    @Override
    public String toString() {
        return "Exam{name='" + examName + "', duration=" + duration +
                " minutes, questions=" + questions + "}";
    }
}
