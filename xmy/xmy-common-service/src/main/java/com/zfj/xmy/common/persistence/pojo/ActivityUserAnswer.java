package com.zfj.xmy.common.persistence.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;

public class ActivityUserAnswer implements Serializable {

    /**
     * activity_user_answer.id
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "id")
    private Long id;


    /**
     * activity_user_answer.user_id (用户ID)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "user_id")
    private Long userId;


    /**
     * activity_user_answer.activity_id (活动ID)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "activity_id")
    private Long activityId;


    /**
     * activity_user_answer.question_id (问题ID)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "question_id")
    private Long questionId;


    /**
     * activity_user_answer.user_answer (用户答案)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "user_answer")
    private String userAnswer;


    /**
     * activity_user_answer.is_true (0:正确 1:不正确)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "is_true")
    private Integer isTrue;


    /**
     * activity_user_answer.create_time (创建时间)
     * @ibatorgenerated 2018-01-26 16:14:48
     */
    @Column(name = "create_time")
    private Date createTime;

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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Integer getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Integer isTrue) {
        this.isTrue = isTrue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}