package com.crm.pojo;

public class CustomerLinkman {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_linkman.id
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_linkman.customer_id
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    private Integer customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_linkman.link_name
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    private String linkName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_linkman.gender
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    private String gender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_linkman.position
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    private String position;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_linkman.office_phone
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    private String officePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column customer_linkman.phone
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    private String phone;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_linkman.id
     *
     * @return the value of customer_linkman.id
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_linkman.id
     *
     * @param id the value for customer_linkman.id
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_linkman.customer_id
     *
     * @return the value of customer_linkman.customer_id
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_linkman.customer_id
     *
     * @param customerId the value for customer_linkman.customer_id
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_linkman.link_name
     *
     * @return the value of customer_linkman.link_name
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public String getLinkName() {
        return linkName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_linkman.link_name
     *
     * @param linkName the value for customer_linkman.link_name
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_linkman.gender
     *
     * @return the value of customer_linkman.gender
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_linkman.gender
     *
     * @param gender the value for customer_linkman.gender
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_linkman.position
     *
     * @return the value of customer_linkman.position
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_linkman.position
     *
     * @param position the value for customer_linkman.position
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_linkman.office_phone
     *
     * @return the value of customer_linkman.office_phone
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_linkman.office_phone
     *
     * @param officePhone the value for customer_linkman.office_phone
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone == null ? null : officePhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column customer_linkman.phone
     *
     * @return the value of customer_linkman.phone
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column customer_linkman.phone
     *
     * @param phone the value for customer_linkman.phone
     *
     * @mbggenerated Mon Oct 30 10:18:17 CST 2017
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}