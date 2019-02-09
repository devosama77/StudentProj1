package com.example.samy.studentproj1.mdoel;

public class Question {
    private String question;
    private String answerText1;
    private String answerText2;
    private String answerText3;
    private String answerText4;
    private boolean answer1;
    private boolean answer2;
    private boolean answer3;
    private boolean answer4;


    public Question(String question, String answerText1, String answerText2, String answerText3,
                    String answerText4, boolean answer1, boolean answer2, boolean answer3, boolean answer4) {
        this.question = question;
        this.answerText1 = answerText1;
        this.answerText2 = answerText2;
        this.answerText3 = answerText3;
        this.answerText4 = answerText4;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public Question() {
    }

    public Question(boolean answer1,boolean answer2,boolean answer3,boolean answer4){
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
    }

    public String getAnswerText3() {
        return answerText3;
    }

    public void setAnswerText3(String answerText3) {
        this.answerText3 = answerText3;
    }

    public String getAnswerText4() {
        return answerText4;
    }

    public void setAnswerText4(String answerText4) {
        this.answerText4 = answerText4;
    }

    public boolean isAnswer3() {
        return answer3;
    }

    public void setAnswer3(boolean answer3) {
        this.answer3 = answer3;
    }

    public boolean isAnswer4() {
        return answer4;
    }

    public void setAnswer4(boolean answer4) {
        this.answer4 = answer4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswer1() {
        return answer1;
    }

    public void setAnswer1(boolean answer1) {
        this.answer1 = answer1;
    }

    public boolean isAnswer2() {
        return answer2;
    }

    public void setAnswer2(boolean answer2) {
        this.answer2 = answer2;
    }

    public String getAnswerText1() {
        return answerText1;
    }

    public void setAnswerText1(String answerText1) {
        this.answerText1 = answerText1;
    }

    public String getAnswerText2() {
        return answerText2;
    }

    public void setAnswerText2(String answerText2) {
        this.answerText2 = answerText2;
    }
}
