

public class Question {
    private String text;
    private String optionA;
    private String optionB;
    private String optionC;
    private int correctOption; // 1=A, 2=B, 3=C

    public Question() {}

    public Question(String text, String optionA, String optionB, String optionC, int correctOption) {
        this.text = text;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctOption = correctOption;
    }

    public String getText() { return text; }
    public String getOptionA() { return optionA; }
    public String getOptionB() { return optionB; }
    public String getOptionC() { return optionC; }
    public int getCorrectOption() { return correctOption; }

    public void setText(String text) { this.text = text; }
    public void setOptionA(String optionA) { this.optionA = optionA; }
    public void setOptionB(String optionB) { this.optionB = optionB; }
    public void setOptionC(String optionC) { this.optionC = optionC; }
    public void setCorrectOption(int correctOption) { this.correctOption = correctOption; }

    // Простой формат одной строкой для БД
    // Q|A|B|C|correct
    public String toStorageString() {
        return safe(text) + "|" + safe(optionA) + "|" + safe(optionB) + "|" + safe(optionC) + "|" + correctOption;
    }

    public static Question fromStorageString(String s) {
        String[] parts = s.split("\\|", -1);
        if (parts.length < 5) return new Question(s, "", "", "", 0);
        return new Question(parts[0], parts[1], parts[2], parts[3], parseIntSafe(parts[4]));
    }

    private static String safe(String x) {
        if (x == null) return "";
        // чтобы не ломать разделители
        return x.replace("|", "/").replace(";", ",");
    }

    private static int parseIntSafe(String x) {
        try { return Integer.parseInt(x.trim()); }
        catch (Exception e) { return 0; }
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", A='" + optionA + '\'' +
                ", B='" + optionB + '\'' +
                ", C='" + optionC + '\'' +
                ", correct=" + correctOption +
                '}';
    }
}
