package com.youngindians.Models;

/**
 * Created by shrivatsa on 14/08/17.
 */

public class User {
    private int id;
    private String name;
    private String email;
    private String user_type;
    private String image;
    private String designation;
    private String company;
    private String position;
    private String vertical;
    private String birthday;
    private String gender;

    public User() {

    }

    public User(String name, String email, String user_type, String image, String designation, String company, String position, String vertical, String birthday, String gender) {
        this.name = name;
        this.email = email;
        this.user_type = user_type;
        this.image = image;
        this.designation = designation;
        this.company = company;
        this.position = position;
        this.vertical = vertical;
        this.birthday = birthday;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!name.equals(user.name)) return false;
        if (!email.equals(user.email)) return false;
        if (!user_type.equals(user.user_type)) return false;
        if (!image.equals(user.image)) return false;
        if (!designation.equals(user.designation)) return false;
        if (!company.equals(user.company)) return false;
        if (!position.equals(user.position)) return false;
        if (!vertical.equals(user.vertical)) return false;
        if (!birthday.equals(user.birthday)) return false;
        return gender.equals(user.gender);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + user_type.hashCode();
        result = 31 * result + image.hashCode();
        result = 31 * result + designation.hashCode();
        result = 31 * result + company.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + vertical.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", user_type='" + user_type + '\'' +
                ", image='" + image + '\'' +
                ", designation='" + designation + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", vertical='" + vertical + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
