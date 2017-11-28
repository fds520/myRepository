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
 * @Desc: 一条动态的评论列表
 */
@Entity
@Table(name = "my_recordcomment")
public class RecordComment extends BaseEntity<Long> {

    // 动态code
    private String recordInfoCode;

    // 评论人的code
    private String userCode;

    // 评论内容
    private String content;

    // 是否有效 0:有效,1:无效
    private Integer isDisabled = 0;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Integer isDisabled) {
        this.isDisabled = isDisabled;
    }
}
