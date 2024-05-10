package com.timi.model;

import java.util.List;

public class Question {
    private int questionId;
    private String content;
    private List<String> options;
    private int correctOptionIndex;
    private int selectedOptionIndex;

    public Question() {
    }

    public Question(int questionId, String content, List<String> options, int correctOptionIndex) {
        this.questionId = questionId;
        this.content = content;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    // constructor without questionId
    public Question(String content, List<String> options, int correctOptionIndex) {
        this.content = content;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public Question(int questionId, String content, List<String> options, int correctOptionIndex, int selectedOptionIndex) {
        this.questionId = questionId;
        this.content = content;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public Question(String content, List<String> options, int correctOptionIndex, int selectedOptionIndex) {
        this.content = content;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }

    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", content=" + content + ", options=" + options
                + ", correctOptionIndex=" + correctOptionIndex + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + questionId;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((options == null) ? 0 : options.hashCode());
        result = prime * result + correctOptionIndex;
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
        Question other = (Question) obj;
        if (questionId != other.questionId)
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (options == null) {
            if (other.options != null)
                return false;
        } else if (!options.equals(other.options))
            return false;
        if (correctOptionIndex != other.correctOptionIndex)
            return false;
        return true;
    }

}
