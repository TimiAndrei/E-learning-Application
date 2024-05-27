package com.timi.model;

import java.time.LocalDateTime;
import java.util.List;

public class QuizAttempt {
    private int attemptId;
    private int userId;
    private int quizId;
    private LocalDateTime timestamp;
    private int score;
    private List<Question> questionsAttempted;
    private float durationAttempted;

    public QuizAttempt() {
        this.timestamp = LocalDateTime.now();
    }

    public QuizAttempt(int attemptId, int userId, int quizId, LocalDateTime timestamp, int score, List<Question> questionsAttempted, float durationAttempted) {
        this.attemptId = attemptId;
        this.userId = userId;
        this.quizId = quizId;
        this.timestamp = timestamp;
        this.score = score;
        this.questionsAttempted = questionsAttempted;
        this.durationAttempted = durationAttempted;
    }

    public QuizAttempt(int userId, int quizId, LocalDateTime timestamp, int score, List<Question> questionsAttempted, float durationAttempted) {
        this.userId = userId;
        this.quizId = quizId;
        this.timestamp = timestamp;
        this.score = score;
        this.questionsAttempted = questionsAttempted;
        this.durationAttempted = durationAttempted;
    }

    public float getDurationAttempted() {
        return durationAttempted;
    }

    public void setDurationAttempted(float durationAttempted) {
        this.durationAttempted = durationAttempted;
    }

    public int getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(int attemptId) {
        this.attemptId = attemptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Question> getQuestionsAttempted() {
        return questionsAttempted;
    }

    public void setQuestionsAttempted(List<Question> questionsAttempted) {
        this.questionsAttempted = questionsAttempted;
    }

    public int calculateQuizScore() {
        int totalScore = 0;
        for (Question question : questionsAttempted) {
            if (question.getSelectedOptionIndex() == question.getCorrectOptionIndex()) {
                totalScore++;
            }
        }
        return totalScore;
    }

    @Override
    public String toString() {
        return "QuizAttempt [attemptId=" + attemptId + ", userId=" + userId + ", quizId=" + quizId + ", timestamp="
                + timestamp + ", score=" + score + ", questionsAttempted=" + questionsAttempted + ", durationAttempted="
                + durationAttempted + "]";
    }

    public String showPreview(){
        return "QuizAttempt [attemptId=" + attemptId + ", userId=" + userId + ", quizId=" + quizId + ", timestamp="
                + timestamp + ", score=" + score + ", durationAttempted="
                + durationAttempted + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + attemptId;
        result = prime * result + userId;
        result = prime * result + quizId;
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + score;
        result = prime * result + ((questionsAttempted == null) ? 0 : questionsAttempted.hashCode());
        result = prime * result + Float.floatToIntBits(durationAttempted);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuizAttempt other = (QuizAttempt) obj;
        if (attemptId != other.attemptId)
            return false;
        if (userId != other.userId)
            return false;
        if (quizId != other.quizId)
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (score != other.score)
            return false;
        if (questionsAttempted == null) {
            if (other.questionsAttempted != null)
                return false;
        } else if (!questionsAttempted.equals(other.questionsAttempted))
            return false;
        if (Float.floatToIntBits(durationAttempted) != Float.floatToIntBits(other.durationAttempted))
            return false;
        return true;
    }

}
