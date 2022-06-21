package cs.tntrung.cg.services;

import cs.tntrung.cg.model.Admin;
import cs.tntrung.cg.model.Role;
import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.utils.AppUtils;
import cs.tntrung.cg.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminServices implements IAdminService {
    public final static String PATH_ADMIN = "data\\admin.csv";
    private static AdminServices instance;
    Scanner input = new Scanner ( System.in );

    public AdminServices(){

    }
    public static AdminServices getInstance() {
        if ( instance == null ) instance = new AdminServices ();
        return instance;
    }
    @Override
    public List<Admin> findAll() {
        List<String> records = CSVUtils.read ( PATH_ADMIN );
        List<Admin> admins = new ArrayList<> ();
        for (String record : records) {
            admins.add ( Admin.parseAdmin ( record ) );
        }
        return admins;
    }

    @Override
    public Admin adminLogin(String email, String password) {
        List<Admin> admins = findAll ();
        for (Admin admin : admins) {
            if ( admin.getEmail ().equals ( email ) &&
                    admin.getPassword ().equals ( password )
                    && admin.getRole ().equals ( Role.ADMIN ) ) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public boolean existsEmail(String email) {
        List<Admin> admins = findAll ();
        for (Admin admin : admins) {
            if ( admin.getEmail ().equals ( email ) )
                return true;
        }
        return false;
    }

    @Override
    public void add(Admin newAdmin) {
        newAdmin.setAddedAt ( Instant.now () );
        List<Admin> admins = findAll ();
        admins.add ( newAdmin );
        CSVUtils.write ( PATH_ADMIN, admins );
    }
}
