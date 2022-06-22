package cs.tntrung.cg.services;

import cs.tntrung.cg.model.Admin;
import cs.tntrung.cg.model.Role;
import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.utils.AppUtils;
import cs.tntrung.cg.utils.CSVUtils;
import cs.tntrung.cg.utils.InstantUtils;
import cs.tntrung.cg.utils.ValidateUtils;
import cs.tntrung.cg.views.AdminView;
import cs.tntrung.cg.views.InputOption;

import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;

public class StudentServices implements IStudentService {
    public final static String PATH = "data\\student.csv";
    private static StudentServices instance;


    public StudentServices() {
    }

    public static StudentServices getInstance() {
        if ( instance == null ) instance = new StudentServices ();
        return instance;
    }

    @Override
    public List<Student> findAll() {
        List<String> records = CSVUtils.read ( PATH );
        List<Student> students = new ArrayList<> ();
        for (String record : records) {
            students.add ( Student.parseStudent ( record ) );
        }
        return students;
    }


    //    Đăng nhập cho học viên
    @Override
    public Student studentLogin(String email, String password) {
        List<Student> students = findAll ();
        for (Student student : students) {
            if ( student.getEmail ().equals ( email ) &&
                    student.getPassword ().equals ( password )
                    && student.getRole ().equals ( Role.STUDENT ) ) {
                return student;
            }
        }
        return null;
    }


//  Thêm tài khoản mới

    @Override
    public void add(Student newStudent) {
        ValidateUtils code = new ValidateUtils ();
        newStudent.setCode ( code.newCode () );
        newStudent.setAddedAt ( Instant.now () );
        List<Student> students = findAll ();
        students.add ( newStudent );
        CSVUtils.write ( PATH, students );
    }

    //Sửa thông tin tài khoản tài khoản
    @Override
    public void edit(Student afterStudent) {
        List<Student> students = findAll ();
        for (Student student : students) {
            if ( student.getCode ().equals ( afterStudent.getCode () ) ) {
                String classes = afterStudent.getClasses ();
                if ( classes != null && !classes.isEmpty () )
                    student.setClasses ( classes );
                String fullName = afterStudent.getName ();
                if ( fullName != null && !fullName.isEmpty () )
                    student.setName ( fullName );
                String gender = afterStudent.getGender ();
                if ( gender != null && !gender.isEmpty () )
                    student.setGender ( gender );
                String dayOfBirth = afterStudent.getDateOfBirth ();
                if ( dayOfBirth != null && !dayOfBirth.isEmpty () )
                    student.setDateOfBirth ( dayOfBirth );
                String phoneNumber = afterStudent.getPhoneNumber ();
                if ( phoneNumber != null && !phoneNumber.isEmpty () )
                    student.setPhoneNumber ( phoneNumber );
                String email = afterStudent.getEmail ();
                if ( email != null && !email.isEmpty () )
                    student.setEmail ( email );
                String password = afterStudent.getPassword ();
                if ( password != null && !password.isEmpty () )
                    student.setPassword ( password );
                student.setUpdatedAt ( Instant.now () );
                String status = afterStudent.getStatus ();
                if ( status != null && !status.isEmpty () )
                    student.setStatus ( status );
                String citizenNum = afterStudent.getCitizenNum ();
                if ( citizenNum != null && !citizenNum.isEmpty () )
                    student.setCitizenNum ( citizenNum );
                String dateRange = afterStudent.getDateRange ();
                if ( dateRange != null && !dateRange.isEmpty () )
                    student.setDateRange ( dateRange );
                String issuedBy = afterStudent.getIssuedBy ();
                if ( issuedBy != null && !issuedBy.isEmpty () )
                    student.setIssuedBy ( issuedBy );
                String address = afterStudent.getAddress ();
                if ( address != null && !address.isEmpty () )
                    student.setAddress ( address );
                String relative = afterStudent.getRelative ();
                if ( relative != null && !relative.isEmpty () )
                    student.setRelative ( relative );
                String relativePhoneNum = afterStudent.getRelativePhoneNum ();
                if ( relativePhoneNum != null && !relativePhoneNum.isEmpty () )
                    student.setRelativePhoneNum ( relativePhoneNum );
                String dayAdmission = afterStudent.getDayAdmission ();
                if ( dayAdmission != null && !dayAdmission.isEmpty () )
                    student.setDayAdmission ( dayAdmission );
                String estimatedEndDate = afterStudent.getEstimatedEndDate ();
                if ( estimatedEndDate != null && !estimatedEndDate.isEmpty () )
                    student.setEstimatedEndDate ( estimatedEndDate );
                CSVUtils.write ( PATH, students );
                break;
            }
        }
    }
    @Override
    public List<Student> sortFullNameADC(List<Student> studentList) {
        studentList.sort ( new Comparator<Student> () {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName ().compareTo ( o2.getName () );
            }
        } );
        return studentList;
    }

    @Override
    public List<Student> sortFullNameDEC(List<Student> studentList) {
        studentList.sort ( new Comparator<Student> () {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getName ().compareTo ( o1.getName () );
            }
        } );
        return studentList;
    }

    @Override
    public Student getByCode(String code) {
        for (Student student : findAll ()) {
            if ( student.getCode ().equals ( code ) )
                return student;
        }
        return null;
    }

    @Override
    public Student getByEmail(String email) {
        for (Student student : findAll ()) {
            if ( student.getEmail ().equals ( email ) )
                return student;
        }
        return null;
    }

    @Override
    public Student getByPhone(String phone) {
        for (Student student : findAll ()) {
            if ( student.getPhoneNumber ().equals ( phone ) )
                return student;
        }
        return null;
    }

    @Override
    public void deleteByCode(String code) {
        List<Student> students = findAll ();
        students.removeIf ( new Predicate<Student> () {
            @Override
            public boolean test(Student student) {
                return student.getCode ().equals ( code );
            }
        } );
        CSVUtils.write ( PATH, students );
    }

    @Override
    public void studentByClass() {
        do {
            String classes;
            do {
                System.out.print ( "Nhập lớp học: " );
                classes = AppUtils.retryString ( "Lớp học" );
                if ( !existsClass ( classes ) ) {
                    System.out.println ( classes + " không tồn tại, nhập lại: " );
                    continue;
                }
                break;
            }while (true);
            List<Student> students = findAll ();
            List<Student> studentByClass = new ArrayList<> ();
            for (Student student : students) {
                if ( student.getClasses ().equals ( classes ) ) {
                    studentByClass.add ( student );
                }
            }
            System.out.println ( "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
            System.out.println ( "║                                                                                ❣❣❣❣❣   DANH SÁCH HỌC VIÊN THEO LỚP    ❣❣❣❣❣                                                                                               ║" );
            System.out.println ( "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );
            System.out.printf ( "║\t%-5s│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                    "STT", "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
            System.out.println ( "╟─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
            int i = 1;
            for (Student student : studentByClass) {
                System.out.printf ( "║\t%-5d│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                        i, student.getCode (),
                        student.getClasses (),
                        student.getName (),
                        student.getGender (),
                        student.getDateOfBirth (),
                        student.getPhoneNumber (),
                        student.getEmail (),
                        InstantUtils.instantToStringDayTime ( student.getAddedAt () ),
                        student.getUpdatedAt () == null ? "" : InstantUtils.instantToStringDayTime ( student.getUpdatedAt () )
                );
                System.out.println ( "╟−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−╢" );
                i++;
            }
            System.out.println ( "╚═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
            System.out.println ( "Nhấn (1) để sắp xếp theo tên (A -> Z)  ||  (2) để mở tùy chọn" );
            int choises = AppUtils.retryChoose ( 1, 2 );
            switch (choises) {
                case 1:
                    AdminView adminView = new AdminView ();
                    adminView.sorfStudentByNameADC ( studentByClass );
                    break;
                case 2:
                    AppUtils.isRetryAdmin ( InputOption.SHOW );
                    break;
                default:
                    System.out.print ( "Nhập sai, hãy nhập lại: " );
                    break;
            }
        }while (true);
    }

    @Override
    public boolean existsCode(String code) {
        List<Student> students = findAll ();
        for (Student student : students) {
            if ( student.getCode ().equals ( code ) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsEmail(String email) {
        List<Student> students = findAll ();
        for (Student student : students) {
            if ( student.getEmail ().equals ( email ) )
                return true;
        }
        return false;
    }

    @Override
    public boolean existsClass(String classes) {
        List<Student> students = findAll ();
        for (Student student : students) {
            if ( student.getClasses ().equals ( classes ) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsCitizenNum(String citizenNum) {
        List<Student> students = findAll ();
        for (Student student : students) {
            if ( student.getCitizenNum ().equals ( citizenNum ) )
                return true;
        }
        return false;
    }

    @Override
    public boolean existsPhoneNumber(String phoneNumber) {
        List<Student> students = findAll ();
        for (Student student : students) {
            if ( student.getPhoneNumber ().equals ( phoneNumber ) )
                return true;
        }
        return false;
    }

    @Override
    public boolean checkNameStudent(String name) {
        List<Student> students = findAll ();
        for (Student student:students) {
            if ( student.getName ().toLowerCase ().contains ( name.toLowerCase () ) ){
                return true;
            }
        }
        return false;
    }
}
