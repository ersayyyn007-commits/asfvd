

import java.util.ArrayList;
import java.util.List;

public class Exam {
    private long id;
    private String title;
    private long candidateId;
    private String status;   // NEW / FINISHED
    private Integer score;   // nullable
    private String questionsText; // хранение в БД

    public Exam() {}

    public Exam(long id, String title, long candidateId, String status, Integer score, String questionsText) {
        this.id = id;
        this.title = title;
        this.candidateId = candidateId;
        this.status = status;
        this.score = score;
        this.questionsText = questionsText;
    }

    public Exam(String title, long candidateId, List<Question> questions) {
        this.title = title;
        this.candidateId = candidateId;
        this.status = "NEW";
        this.score = null;
        this.questionsText = encodeQuestions(questions);
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public long getCandidateId() { return candidateId; }
    public String getStatus() { return status; }
    public Integer getScore() { return score; }
    public String getQuestionsText() { return questionsText; }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCandidateId(long candidateId) { this.candidateId = candidateId; }
    public void setStatus(String status) { this.status = status; }
    public void setScore(Integer score) { this.score = score; }
    public void setQuestionsText(String questionsText) { this.questionsText = questionsText; }

    // вопросы храним в TEXT так:
    // question1;question2;question3
    // где question = Q|A|B|C|correct
    public static String encodeQuestions(List<Question> questions) {
        if (questions == null || questions.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < questions.size(); i++) {
            if (i > 0) sb.append(";");
            sb.append(questions.get(i).toStorageString());
        }
        return sb.toString();
    }

    public static List<Question> decodeQuestions(String questionsText) {
        List<Question> list = new ArrayList<>();
        if (questionsText == null || questionsText.isBlank()) return list;
        String[] items = questionsText.split(";", -1);
        for (String it : items) {
            if (!it.isBlank()) list.add(Question.fromStorageString(it));
        }
        return list;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", candidateId=" + candidateId +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", questionsText='" + questionsText + '\'' +
                '}';
    }
}
