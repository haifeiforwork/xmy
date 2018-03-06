package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class ActivityQuestion implements Serializable {

    /**
     * activity_question.id
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "id")
    private Long id;


    /**
     * activity_question.activity_id (活动ID)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "activity_id")
    private Long activityId;


    /**
     * activity_question.activity_question (活动问题)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "activity_question")
    private String activityQuestion;


    /**
     * activity_question.answer_a (答案A)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "answer_a")
    private String answerA;


    /**
     * activity_question.answer_b (答案B)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "answer_b")
    private String answerB;


    /**
     * activity_question.answer_c (答案C)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "answer_c")
    private String answerC;


    /**
     * activity_question.answer_d (答案D)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "answer_d")
    private String answerD;


    /**
     * activity_question.true_answer (正确答案)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "true_answer")
    private String trueAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityQuestion() {
        return activityQuestion;
    }

    public void setActivityQuestion(String activityQuestion) {
        this.activityQuestion = activityQuestion;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }
}