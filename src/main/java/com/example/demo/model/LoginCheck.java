package com.example.demo.model;

public class LoginCheck {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column logincheck.token
     *
     * @mbg.generated Thu Jan 02 18:05:06 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column logincheck.loginTime
     *
     * @mbg.generated Thu Jan 02 18:05:06 CST 2020
     */
    private Long logintime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column logincheck.token
     *
     * @return the value of logincheck.token
     *
     * @mbg.generated Thu Jan 02 18:05:06 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column logincheck.token
     *
     * @param token the value for logincheck.token
     *
     * @mbg.generated Thu Jan 02 18:05:06 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column logincheck.loginTime
     *
     * @return the value of logincheck.loginTime
     *
     * @mbg.generated Thu Jan 02 18:05:06 CST 2020
     */
    public Long getLogintime() {
        return logintime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column logincheck.loginTime
     *
     * @param logintime the value for logincheck.loginTime
     *
     * @mbg.generated Thu Jan 02 18:05:06 CST 2020
     */
    public void setLogintime(Long logintime) {
        this.logintime = logintime;
    }
}