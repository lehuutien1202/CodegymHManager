package cs.tntrung.cg.services;

import cs.tntrung.cg.model.Admin;
import cs.tntrung.cg.model.Student;

import java.util.List;

public interface IAdminService {
    List<Admin> findAll();
    Admin adminLogin(String username, String password);
    boolean existsEmail(String email);
    Admin getByEmail(String email);
    void add(Admin newAdmin);
}
