package com.my.space.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author:冯大双
 * @Date:2017/6/7
 * @Desc: 动态记录实体类
 */
@Entity
@Table(name = "my_result")
public class RecordInfo extends BaseEntity<Long> {

    // 唯一标识code
    private String code;

    // 用户名
    private String userName;

    // 手机号码
    private String phone;

    // 密码
    private String password;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreateAt;

    // 收藏code；
    private String recordInfoCode;

    // 用户code
    private String userCode;

    // 点赞次数
    private Long goodCount = 0L;

    // 浏览次数
    private Long browseCount = 0L;

    // 标题
    private String title;

    // 封面图片
    private String pictureUrl;

    // 动态内容
    @Lob
    private String content;

    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    // 是否有效 0:有效 1:无效
    private Integer isDisabled = 0;

    public Long getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Long browseCount) {
        this.browseCount = browseCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Long getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Long goodCount) {
        this.goodCount = goodCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getIsDisabled() {
        return isDisabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUserCreateAt() {
        return userCreateAt;
    }

    public void setUserCreateAt(Date userCreateAt) {
        this.userCreateAt = userCreateAt;
    }

    public String getRecordInfoCode() {
        return recordInfoCode;
    }

    public void setRecordInfoCode(String recordInfoCode) {
        this.recordInfoCode = recordInfoCode;
    }

    public void setIsDisabled(Integer isDisabled) {
        this.isDisabled = isDisabled;
    }
}
