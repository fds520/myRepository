package com.my.space.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @Author:冯大双
 * @Date:2017/6/9
 * @Desc: 用户点赞记录表
 */
@Entity
@Table(name = "my_userandgood")
public class UserAndGood extends BaseEntity<Long>{

    // 用户code
    private String userCode;

    // 用户名
    private String userName;

    // 动态code
    private String recordInfoCode;

    // 1：点赞 0：不点赞
    private Integer status = 1;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    public Date getCreateAt() {
        return createAt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRecordInfoCode() {
        return recordInfoCode;
    }

    public void setRecordInfoCode(String recordInfoCode) {
        this.recordInfoCode = recordInfoCode;
    }
}
