package cs.tntrung.cg.model;

import java.time.Instant;
import java.util.List;

public class Student {
    private String code;
    private String classes;
    private String fullName;
    private String gender;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
    private Instant addedAt;
    private Instant updatedAt;
    private String status;
    private String citizenNum;
    private String dateRange;
    private String issuedBy;
    private String address;
    private String relative;
    private String relativePhoneNum;
    private String dayAdmission;
    private String estimatedEndDate;

    public Student() {

    }

    public Student(String classes, String name, String gender, String dateOfBirth, String phoneNumber, String email, String password) {
        this.classes = classes;
        this.fullName = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Student(String code, String classes, String name, String gender, String dateOfBirth, String phoneNumber, String email, Role role) {
        this.code = code;
        this.classes = classes;
        this.fullName = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }

    public static Student parseStudent(String row) {
        Student student = new Student ();
        String[] fields = row.split ( "," );
        student.code = fields[0];
        student.classes = fields[1];
        student.fullName = fields[2];
        student.gender = fields[3];
        student.dateOfBirth = fields[4];
        student.phoneNumber = fields[5];
        student.email = fields[6];
        student.password = fields[7];
        student.role = Role.parseRole ( fields[8] );
        student.addedAt = Instant.parse ( fields[9] );
        String temp = fields[10];
        if ( temp != null && !temp.equals ( "null" ) )
            student.updatedAt = Instant.parse ( temp );
        student.status = fields[11];
        student.citizenNum = fields[12];
        student.dateRange = fields[13];
        student.issuedBy = fields[14];
        student.address = fields[15];
        student.relative = fields[16];
        student.relativePhoneNum = fields[17];
        student.dayAdmission = fields[18];
        student.estimatedEndDate = fields[19];
        return student;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCitizenNum() {
        return citizenNum;
    }

    public void setCitizenNum(String citizenNum) {
        this.citizenNum = citizenNum;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getRelativePhoneNum() {
        return relativePhoneNum;
    }

    public void setRelativePhoneNum(String relativePhoneNum) {
        this.relativePhoneNum = relativePhoneNum;
    }

    public String getDayAdmission() {
        return dayAdmission;
    }

    public void setDayAdmission(String dayAdmission) {
        this.dayAdmission = dayAdmission;
    }

    public String getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(String estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    @Override
    public String toString() {
        return code + "," +
                classes + "," +
                fullName + "," +
                gender + "," +
                dateOfBirth + "," +
                phoneNumber + "," +
                email + "," +
                password + "," +
                role + "," +
                addedAt + "," +
                updatedAt + "," +
                status + "," +
                citizenNum + "," +
                dateRange + "," +
                issuedBy + "," +
                address + "," +
                relative + "," +
                relativePhoneNum + "," +
                dayAdmission + "," +
                estimatedEndDate
                ;
    }
}
