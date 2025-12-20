public class main {
    public static void main(String[] args) {

        Candidate c1 = new Candidate(1, "Ayan", "ayan@gmail.com");
        Candidate c2 = new Candidate(2, "Dias", "dias@gmail.com");

        Question q1 = new Question(101, "What is Java?", "Programming language");
        Question q2 = new Question(102, "What is OOP?", "Object Oriented Programming");
        Question q3 = new Question(103, "What is JVM?", "Java Virtual Machine");

        Exam exam = new Exam("Java Basics Exam", 60);
        exam.addQuestion(q1);
        exam.addQuestion(q2);
        exam.addQuestion(q3);

        System.out.println(c1);
        System.out.println(c2);
        System.out.println(exam);

        System.out.println("\nSearch Question ID 102:");
        System.out.println(exam.findQuestionById(102));

        System.out.println("\nFilter questions with 'Java':");
        System.out.println(exam.filterByKeyword("Java"));

        System.out.println("\nSorted questions:");
        exam.sortById();
        System.out.println(exam);

        System.out.println("\nEquals test:");
        System.out.println(c1.equals(c2) ? "Same candidate" : "Different candidates");
    }
}
