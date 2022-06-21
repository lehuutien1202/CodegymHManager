package cs.tntrung.cg.model;

public enum Role {
    ADMIN("ADMIN"), STUDENT("STUDENT");

    private String value;
    Role(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
    public static Role parseRole(String value) {
        Role[] values = values ();
        for (Role role : values) {
            if ( role.value.equals ( value ) ) return role;
        }
        throw new IllegalArgumentException ("INVALID!");
    }
}
