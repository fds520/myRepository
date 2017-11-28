package com.my.space.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @Author:冯大双
 * @Date:2017/7/20
 * @Desc: 用户收藏动态实体类
 */
@Entity
@Table(name = "my_usercollect")
public class UserCollect extends BaseEntity<Long>{

    // 用户code
    private String userCode;

    // 收藏code；
    private String recordInfoCode;

    // 状态 0:未收藏 1：已经收藏
    private Integer status = 1;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    public Date getCreateAt() {
        return createAt;
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
