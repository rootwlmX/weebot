package indi.madoka.weeb.core.bean.update.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * @author Arcueid
 */
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MessageSender {
    /**
     * 发送者 QQ 号
     */
    private Integer userId;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 性别, male 或 female 或 unknown
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "消息发送人:{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
