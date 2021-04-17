package com.plaidcamp.mealogram.apiServer.mealogram.vo;

/**
 * VO : Controller Parameter / Body Vo
 */
public class PostVo {

    private String post_id;

    private String user_id;

    private String write_time;

    private String eatting_time;

    private int fullness;

    private int satisfation;

    private String text;

    private String ctime;

    private String utime;

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setWrite_time(String write_time) {
        this.write_time = write_time;
    }

    public void setEatting_time(String eatting_time) {
        this.eatting_time = eatting_time;
    }

    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    public void setSatisfation(int satisfation) {
        this.satisfation = satisfation;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getPost_id() {
        return post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getWrite_time() {
        return write_time;
    }

    public String getEatting_time() {
        return eatting_time;
    }

    public int getFullness() {
        return fullness;
    }

    public int getSatisfation() {
        return satisfation;
    }

    public String getText() {
        return text;
    }

    public String getCtime() {
        return ctime;
    }

    public String getUtime() {
        return utime;
    }

    @Override
    public String toString() {
        return "InsertPostVo{" +
                "post_id='" + post_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", write_time='" + write_time + '\'' +
                ", eatting_time='" + eatting_time + '\'' +
                ", fullness=" + fullness +
                ", satisfation=" + satisfation +
                ", text='" + text + '\'' +
                ", ctime='" + ctime + '\'' +
                ", utime='" + utime + '\'' +
                '}';
    }
}
