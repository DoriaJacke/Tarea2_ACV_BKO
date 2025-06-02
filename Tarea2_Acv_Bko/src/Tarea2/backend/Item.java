package Tarea2.backend;

public class Item {
    private String questionType;
    private String question;
    private String taxLevel;
    private int duration;
    private int correctChoice;
    private String[] choices;
    private int answer = -1;

    public Item(String questionType, String question, String taxLevel, int duration, int correctChoice, String[] choices) {
        this.questionType = questionType;
        this.question = question;
        this.taxLevel = taxLevel;
        this.duration = duration;
        this.correctChoice = correctChoice;
        this.choices = choices;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTaxLevel() {
        return taxLevel;
    }

    public void setTaxLevel(String taxLevel) {
        this.taxLevel = taxLevel;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public int getAnswer() {
        return answer;
    }
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    //Metodos


}

