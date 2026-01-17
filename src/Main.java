

import java.sql.*;
import java.time.Instant;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // ====== CREATE candidate ======
        long candidateId = createCandidate("Test Student", "student1@mail.com");
        System.out.println("CREATE candidateId = " + candidateId);

        // ====== READ candidate ======
        Candidate c = readCandidate(candidateId);
        System.out.println("READ candidate = " + c);

        // ====== UPDATE candidate ======
        boolean candUpdated = updateCandidateEmail(candidateId, "student1_new@mail.com");
        System.out.println("UPDATE candidate ok? " + candUpdated);
        System.out.println("READ candidate after update = " + readCandidate(candidateId));

        // ====== CREATE exam ======
        List<Question> questions = List.of(
                new Question("2 + 2 = ?", "3", "4", "5", 2),
                new Question("Capital of Kazakhstan?", "Almaty", "Astana", "Shymkent", 2)
        );
        Exam examToCreate = new Exam("Demo Exam #1", candidateId, questions);
        long examId = createExam(examToCreate);
        System.out.println("CREATE examId = " + examId);

        // ====== READ exam ======
        Exam exam = readExam(examId);
        System.out.println("READ exam = " + exam);
        System.out.println("Decoded questions = " + Exam.decodeQuestions(exam.getQuestionsText()));

        // ====== UPDATE exam ======
        boolean examUpdated = updateExamStatusAndScore(examId, "FINISHED", 2);
        System.out.println("UPDATE exam ok? " + examUpdated);
        System.out.println("READ exam after update = " + readExam(examId));

        // ====== DELETE exam ======
        boolean examDeleted = deleteExam(examId);
        System.out.println("DELETE exam ok? " + examDeleted);

        // ====== DELETE candidate ======
        boolean candDeleted = deleteCandidate(candidateId);
        System.out.println("DELETE candidate ok? " + candDeleted);
    }

    // ---------------- Candidate CRUD ----------------

    private static long createCandidate(String fullName, String email) throws SQLException {
        String sql = "INSERT INTO candidates(full_name, email) VALUES (?, ?) RETURNING id";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fullName);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getLong(1);
        }
    }

    private static Candidate readCandidate(long id) throws SQLException {
        String sql = "SELECT id, full_name, email, created_at FROM candidates WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;

            Instant createdAt = rs.getTimestamp("created_at").toInstant();
            return new Candidate(
                    rs.getLong("id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    createdAt
            );
        }
    }

    private static boolean updateCandidateEmail(long id, String newEmail) throws SQLException {
        String sql = "UPDATE candidates SET email = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newEmail);
            ps.setLong(2, id);
            return ps.executeUpdate() == 1;
        }
    }

    private static boolean deleteCandidate(long id) throws SQLException {
        String sql = "DELETE FROM candidates WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    // ---------------- Exam CRUD ----------------

    private static long createExam(Exam exam) throws SQLException {
        String sql = """
                INSERT INTO exams(title, candidate_id, status, score, questions_text)
                VALUES (?, ?, ?, ?, ?)
                RETURNING id
                """;
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, exam.getTitle());
            ps.setLong(2, exam.getCandidateId());
            ps.setString(3, exam.getStatus());

            if (exam.getScore() == null) ps.setNull(4, Types.INTEGER);
            else ps.setInt(4, exam.getScore());

            ps.setString(5, exam.getQuestionsText());

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getLong(1);
        }
    }

    private static Exam readExam(long examId) throws SQLException {
        String sql = "SELECT id, title, candidate_id, status, score, questions_text FROM exams WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, examId);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;

            Integer score = (rs.getObject("score") == null) ? null : rs.getInt("score");

            return new Exam(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getLong("candidate_id"),
                    rs.getString("status"),
                    score,
                    rs.getString("questions_text")
            );
        }
    }

    private static boolean updateExamStatusAndScore(long examId, String status, Integer score) throws SQLException {
        String sql = "UPDATE exams SET status = ?, score = ? WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);

            if (score == null) ps.setNull(2, Types.INTEGER);
            else ps.setInt(2, score);

            ps.setLong(3, examId);
            return ps.executeUpdate() == 1;
        }
    }

    private static boolean deleteExam(long examId) throws SQLException {
        String sql = "DELETE FROM exams WHERE id = ?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, examId);
            return ps.executeUpdate() == 1;
        }
    }
}
