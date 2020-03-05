package useInfo;

import java.time.LocalTime;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class UserVO {
    private int id;
    private int pcNumber;
    private String name;
    private String userId;
    private String password;
    private String sex;
    private String email;
    private boolean prepayment;
    private LocalTime remainTime;
    private LocalTime useTime;
    private int cumulativeAmount;
    private LocalTime cumulativeTime;
    private String dateOfBirth;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPcNumber() {
        return pcNumber;
    }
    public void setPcNumber(int pcNumber) {
        this.pcNumber = pcNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isPrepayment() {
        return prepayment;
    }
    public void setPrepayment(boolean prepayment) {
        this.prepayment = prepayment;
    }
    public LocalTime getRemainTime() {
        return remainTime;
    }
    public void setRemainTime(LocalTime remainTime) {
        this.remainTime = remainTime;
    }
    public LocalTime getUseTime() {
        return useTime;
    }
    public void setUseTime(LocalTime useTime) {
        this.useTime = useTime;
    }
    public int getCumulativeAmount() {
        return cumulativeAmount;
    }
    public void setCumulativeAmount(int cumulativeAmount) {
        this.cumulativeAmount = cumulativeAmount;
    }
    public LocalTime getCumulativeTime() {
        return cumulativeTime;
    }
    public void setCumulativeTime(LocalTime cumulativeTime) {
        this.cumulativeTime = cumulativeTime;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}