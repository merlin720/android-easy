package com.easy.easycan.me.model;

import java.io.Serializable;

/**
 * @author merlin720
 * date 2019-10-10
 * email zy44638@gmail.com
 * description
 */
public class ProfileAuthenticationBean implements Serializable {
    private String id;
    private String ref_id;
    private String user_id;
    private String type;
    private String amount;
    private String name;
    private String real_name;
    private String identity_no;
    private String identity_image;
    private String identity_back_image;
    private String face_image;
    private String company_name;
    private String company_business_license_no;
    private String company_business_license_image;
    private String company_authorization_certificate_image;
    private String company_dangerous_license_image;
    private String status;
    private String reason;
    private String bank_type;
    private String bank_name;
    private String bank_account;
    private String add_time;

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdentity_no() {
        return identity_no;
    }

    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
    }

    public String getIdentity_image() {
        return identity_image;
    }

    public void setIdentity_image(String identity_image) {
        this.identity_image = identity_image;
    }

    public String getIdentity_back_image() {
        return identity_back_image;
    }

    public void setIdentity_back_image(String identity_back_image) {
        this.identity_back_image = identity_back_image;
    }

    public String getFace_image() {
        return face_image;
    }

    public void setFace_image(String face_image) {
        this.face_image = face_image;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_business_license_no() {
        return company_business_license_no;
    }

    public void setCompany_business_license_no(String company_business_license_no) {
        this.company_business_license_no = company_business_license_no;
    }

    public String getCompany_business_license_image() {
        return company_business_license_image;
    }

    public void setCompany_business_license_image(String company_business_license_image) {
        this.company_business_license_image = company_business_license_image;
    }

    public String getCompany_authorization_certificate_image() {
        return company_authorization_certificate_image;
    }

    public void setCompany_authorization_certificate_image(String company_authorization_certificate_image) {
        this.company_authorization_certificate_image = company_authorization_certificate_image;
    }

    public String getCompany_dangerous_license_image() {
        return company_dangerous_license_image;
    }

    public void setCompany_dangerous_license_image(String company_dangerous_license_image) {
        this.company_dangerous_license_image = company_dangerous_license_image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }
}
