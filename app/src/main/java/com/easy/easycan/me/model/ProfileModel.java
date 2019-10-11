package com.easy.easycan.me.model;

import java.io.Serializable;

/**
 * @author merlin720
 * date 2019-10-10
 * email zy44638@gmail.com
 * description
 */
public class ProfileModel implements Serializable {
    private String id;
    private String parent_id;
    private String recommender_id;
    private String company_id;
    private String department_id;
    private String wechat_union_id;
    private String user_type;
    private String amount;
    private String bonus;
    private String point;
    private String sign_date;
    private String role_name;
    private String phone;
    private String phone_key;
    private String name;
    private String avatar;
    private String real_name;



    private String sex;
    private String identity_no;
    private String address;
    private String company_name;
    private String company_phone;
    private String employment_date;
    private String region_name;
    private String basic_wage;
    private String cargo_commission;
    private String vehicle_commission;
    private String introduce;
    private String bank_name;
    private String bank_type;
    private String bank_account;
    private String register_ip;
    private String register_time;
    private String login_ip;
    private String login_time;
    private String activity_time;
    private String is_authentication;
    private String is_manager;
    private String is_dev;
    private String active_status;
    private String optimistic_lock;
    private String user_type_name;
    private String text;
    private ProfileCompanyBean company;
    private ProfileAuthenticationBean authentication;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getSign_date() {
        return sign_date;
    }

    public void setSign_date(String sign_date) {
        this.sign_date = sign_date;
    }

    public String getPhone_key() {
        return phone_key;
    }

    public void setPhone_key(String phone_key) {
        this.phone_key = phone_key;
    }

    public String getIdentity_no() {
        return identity_no;
    }

    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
    }

    public String getCompany_phone() {
        return company_phone;
    }

    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

    public String getEmployment_date() {
        return employment_date;
    }

    public void setEmployment_date(String employment_date) {
        this.employment_date = employment_date;
    }

    public String getBasic_wage() {
        return basic_wage;
    }

    public void setBasic_wage(String basic_wage) {
        this.basic_wage = basic_wage;
    }

    public String getCargo_commission() {
        return cargo_commission;
    }

    public void setCargo_commission(String cargo_commission) {
        this.cargo_commission = cargo_commission;
    }

    public String getVehicle_commission() {
        return vehicle_commission;
    }

    public void setVehicle_commission(String vehicle_commission) {
        this.vehicle_commission = vehicle_commission;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRegister_ip() {
        return register_ip;
    }

    public void setRegister_ip(String register_ip) {
        this.register_ip = register_ip;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getActivity_time() {
        return activity_time;
    }

    public void setActivity_time(String activity_time) {
        this.activity_time = activity_time;
    }

    public String getIs_authentication() {
        return is_authentication;
    }

    public void setIs_authentication(String is_authentication) {
        this.is_authentication = is_authentication;
    }

    public String getIs_manager() {
        return is_manager;
    }

    public void setIs_manager(String is_manager) {
        this.is_manager = is_manager;
    }

    public String getIs_dev() {
        return is_dev;
    }

    public void setIs_dev(String is_dev) {
        this.is_dev = is_dev;
    }

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }

    public String getOptimistic_lock() {
        return optimistic_lock;
    }

    public void setOptimistic_lock(String optimistic_lock) {
        this.optimistic_lock = optimistic_lock;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProfileAuthenticationBean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(ProfileAuthenticationBean authentication) {
        this.authentication = authentication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getRecommender_id() {
        return recommender_id;
    }

    public void setRecommender_id(String recommender_id) {
        this.recommender_id = recommender_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getWechat_union_id() {
        return wechat_union_id;
    }

    public void setWechat_union_id(String wechat_union_id) {
        this.wechat_union_id = wechat_union_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip;
    }

    public String getUser_type_name() {
        return user_type_name;
    }

    public void setUser_type_name(String user_type_name) {
        this.user_type_name = user_type_name;
    }

    public ProfileCompanyBean getCompany() {
        return company;
    }

    public void setCompany(ProfileCompanyBean company) {
        this.company = company;
    }
}
