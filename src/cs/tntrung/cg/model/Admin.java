package cs.tntrung.cg.model;

import java.time.Instant;

public class Admin {
    private String email;
    private String password;
    private String name;
    private String yearOfBirth;
    private String position;
    private Role role;
    private Instant addedAt;
    private Instant updatedAt;
    public Admin(){
        
    }

    public Admin(String email, String password, String name, String yearOfBirth, String position) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.position = position;
    }
    public static Admin parseAdmin(String row){
        Admin adminAccount = new Admin (  );
        String[] fields = row.split ( "," );
        adminAccount.email = fields[0];
        adminAccount.password = fields[1];
        adminAccount.name = fields[2];
        adminAccount.yearOfBirth = ( fields[3] );
        adminAccount.position = fields[4];
        adminAccount.role = Role.parseRole ( fields[5] );
        adminAccount.addedAt = Instant.parse ( fields[6] );
        String temp = fields[7];
        if ( temp != null && !temp.equals ( "null" ) )
            adminAccount.updatedAt = Instant.parse ( temp );
        return adminAccount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    @Override
    public String toString() {
        return email + "," + 
                password + "," +
                name + "," +
                yearOfBirth + "," +
                position + "," +
                role + "," +
                addedAt + "," +
                updatedAt;
    }
}
