package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import javax.persistence.Column;

public class UserAnswer implements Serializable {

    /**
     * user_answer.id (答案id)
     * @ibatorgenerated 2017-08-04 11:39:15
     */
    @Column(name = "id")
    private Long id;


    /**
     * user_answer.user_id (用户id)
     * @ibatorgenerated 2017-08-04 11:39:15
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * user_answer.question_id (问题id)
     * @ibatorgenerated 2017-08-04 11:39:15
     */
    @Column(name = "question_id")
    private Long questionId;


    /**
     * user_answer.answer (问题答案)
     * @ibatorgenerated 2017-08-04 11:39:15
     */
    @Column(name = "answer")
    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}