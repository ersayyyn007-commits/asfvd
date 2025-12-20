public class Assignment_OOP {
    public static void main(String[] args) {

        Candidate c1 = new Candidate(1, "Ayan", "ayan@gmail.com");
        Candidate c2 = new Candidate(2, "Dias", "dias@gmail.com");

        Question q1 = new Question(101, "What is Java?", "Programming language");
        Question q2 = new Question(102, "What is OOP?", "Object Oriented Programming");

        Exam exam = new Exam("Java Basics Exam", 60);
        exam.addQuestion(q1);
        exam.addQuestion(q2);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(exam);

        if (c1.getId() == c2.getId()) {
            System.out.println("Candidates are the same");
        } else {
            System.out.println("Candidates are different");
        }
    }
}
