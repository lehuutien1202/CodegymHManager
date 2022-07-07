package cs.tntrung.cg.views;

import cs.tntrung.cg.model.Admin;
import cs.tntrung.cg.model.Role;
import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.services.AdminServices;
import cs.tntrung.cg.services.IStudentService;
import cs.tntrung.cg.services.StudentServices;
import cs.tntrung.cg.utils.AppUtils;
import cs.tntrung.cg.utils.InstantUtils;
import cs.tntrung.cg.utils.ValidateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class AdminView extends StudentServices {
    private static final Scanner input = new Scanner ( System.in );
    private final IStudentService studentService;
    AdminServices adminService = new AdminServices ();
    LaunchAdminView launchAdminView = new LaunchAdminView ();

    public AdminView() {
        studentService = StudentServices.getInstance ();
    }

    //  Đăng nhập cho admin
    public void loginAdmin() {
        boolean isRetry;
        System.out.println ();
        System.out.println ( " ✎✎✎ ĐĂNG NHẬP DÀNH CHO QUẢN TRỊ VIÊN ✎✎✎ " );
        System.out.println ();
        do {
            String email = inputEmailAdmin(InputOption.SIGNIN);
            String password = inputPassword ( InputOption.SIGNIN );
            if ( adminService.adminLogin ( email, password ) == null ) {
                System.out.println ( "Tài khoản không tồn tại!" );
                isRetry = isRetry ();
            } else {
                System.out.println ();
                Admin admin = adminService.getByEmail ( email );
                System.out.println ( "ĐĂNG NHẬP THÀNH CÔNG!" );
                System.out.printf ( "%s %s %s ", "XIN CHÀO!", admin.getPosition (), admin.getName () );
                System.out.println ();
                LaunchAdminView adminView = new LaunchAdminView ();
                adminView.launch ();
                isRetry = false;
            }
        } while (isRetry);
    }

    public void singup() {
        do {
            System.out.println ( "╒═════════════════ TẠO TÀI KHOẢN ═════════════════╕" );
            System.out.println ( "│                                                 │" );
            System.out.println ( "│              ▸ 1. Quản trị viên                 │" );
            System.out.println ( "│              ▹ 2. Học viên                      │" );
            System.out.println ( "│                                                 │" );
            System.out.println ( "│ ◌ 3: Đăng xuất |   ◌ 4: Quay lại  |  ◌ 5: Thoát │" );
            System.out.println ( "╘═════════════════════════════════════════════════╛" );
            int choise = AppUtils.retryChoose ( 1, 5 );
            switch (choise) {
                case 1:
                    signupAdmin ();
                    break;
                case 2:
                    signupStudent ();
                    break;
                case 3:
                    Login.login ();
                    break;
                case 4:
                    launchAdminView.launch ();
                    break;
                case 5:
                    AppUtils.exit ();
                    break;
            }
        } while (true);
    }

    private void signupAdmin() {
        do {
            System.out.println ( "\t┌─────────────────────────────────────┐" );
            System.out.println ( "\t│ Tạo tài khoản mới cho quản trị viên │" );
            System.out.println ( "\t└─────────────────────────────────────┘" );
            String email = inputEmailAdmin ( InputOption.ADD );
            String password = inputPassword ( InputOption.CHANGE_PASSWORD );
            String name = inputFullName ( InputOption.ADD );
            String year = inputYear ();
            String position = inputPosition ();
            Admin newAdmin = new Admin ( email, password, name, year, position );
            newAdmin.setRole ( Role.ADMIN );
            adminService.add ( newAdmin );
            System.out.println ( "Đã thêm thành công!" );
            AppUtils.isRetryAdmin ( InputOption.ADD );
        } while (true);
    }

    private void signupStudent() {
        do {
            System.out.println ( "\t┌────────────────────────────────┐" );
            System.out.println ( "\t│ Tạo tài khoản mới cho học viên │" );
            System.out.println ( "\t└────────────────────────────────┘" );
            String classes = inputClass ( InputOption.ADD );
            String fullName = inputFullName ( InputOption.ADD );
            String gender = inputGender ( InputOption.ADD );
            String dayOfBirth = inputDate ( InputOption.ADD );
            String phoneNumber = inputPhoneNum ( InputOption.ADD );
            String email = inputEmail ( InputOption.ADD );
            String password = inputPassword ( InputOption.SIGNIN );
            Student newStudent = new Student ( classes, fullName, gender, dayOfBirth, phoneNumber, email, password );
            newStudent.setRole ( Role.STUDENT );
            studentService.add ( newStudent );
            System.out.println ();
            System.out.println ( "Đã thêm thành công!" );
            System.out.println ();
            AppUtils.isRetryAdmin ( InputOption.ADD );
        } while (true);
    }
// Hiển thị danh sách sinh viên
    public void showStudentList() {
        do {
            ShowStudent show = new ShowStudent ();
            show.showStudentList ();
            int choise_1 = AppUtils.retryChoose ( 1, 6 );
            switch (choise_1) {
                case 1:
                    ShowStudent showStudent = new ShowStudent ();
                    showStudent.showAllStudent ();
                    break;
                case 2:
                    StudentServices studentServices = new StudentServices ();
                    studentServices.studentByClass ();
                    break;
                case 3:
                    SearchStudent student = new SearchStudent ();
                    student.searchStudent ();
                    break;
                case 4:
                    AppUtils.login ();
                    break;
                case 5:
                    launchAdminView.launch ();
                    break;
                case 6:
                    AppUtils.exit ();
                    break;
            }
        } while (true);
    }
//  sắp xếp theo tên tăng dần
    public void sorfStudentByNameADC(List<Student> studentList) {
        List<Student> sorfListADC = studentService.sortFullNameADC ( studentList );
        System.out.println ( "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                                ❣❣❣❣❣   DANH SÁCH HỌC VIÊN THEO TÊN (A -> Z)   ❣❣❣❣❣                                                                                       ║" );
        System.out.println ( "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );
        System.out.printf ( "║\t%-5s│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                "STT", "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
        System.out.println ( "╟─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        int i = 1;
        for (Student student : sorfListADC) {
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
        System.out.println ( "Nhấn (1) để sắp xếp theo tên (Z -> A) ||  (2) để mở tùy chọn" );
        System.out.print ( "☞☞☞ " );
        int choises_11 = AppUtils.retryChoose ( 1, 2 );
        switch (choises_11) {
            case 1:
                AdminView adminView = new AdminView ();
                adminView.sorfStudentByNameDEC ( sorfListADC );
                break;
            case 2:
                AppUtils.isRetryAdmin ( InputOption.SHOW );
                break;
        }
        //        AppUtils.isRetryAdmin ( InputOption.SHOW );
    }
// Sắp xếp theo tên giảm dần
    public void sorfStudentByNameDEC(List<Student> studentList) {
        List<Student> sorfListDEC = studentService.sortFullNameDEC ( studentList );
        System.out.println ( "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                                ❣❣❣❣❣   DANH SÁCH HỌC VIÊN THEO TÊN (Z -> A)   ❣❣❣❣❣                                                                                       ║" );
        System.out.println ( "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );
        System.out.printf ( "║\t%-5s│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                "STT", "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
        System.out.println ( "╟─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        int i = 1;
        for (Student student : sorfListDEC) {
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
        System.out.println ( "Nhấn (1) để sắp xếp theo tên (A -> Z) ||  (2) để mở tùy chọn" );
        System.out.print ( "☞☞☞ " );
        int choises_11 = AppUtils.retryChoose ( 1, 2 );
        switch (choises_11) {
            case 1:
                AdminView adminView = new AdminView ();
                adminView.sorfStudentByNameADC ( sorfListDEC );
                break;
            case 2:
                AppUtils.isRetryAdmin ( InputOption.SHOW );
                break;
        }
    }

    public void addAll() {
        do {
            ShowStudent list = new ShowStudent ();
            list.showToAction ();
            String code = inputCode ( InputOption.INFO );
            Student students = new Student ();
            students.setCode ( code );
            Student student = studentService.getByCode ( code );
            System.out.println ( "Nhập thông tin chi tiết cho học viên: " + student.getName () );
            String status = inputStatus ();
            student.setStatus ( status );
            String citizenNum = inputCitizenNum ();
            student.setCitizenNum ( citizenNum );
            String dateRange = inputDate ( InputOption.INFO );
            student.setDateRange ( dateRange );
            String issuedBy = inputIssuedBy ();
            student.setIssuedBy ( issuedBy );
            String address = inputAddress ();
            student.setAddress ( address );
            String relative = inputFullName ( InputOption.INFO );
            student.setRelative ( relative );
            String relativePhoneNum = inputPhoneNum ( InputOption.INFO );
            student.setRelativePhoneNum ( relativePhoneNum );
            String dayAdmission = inputDate ( InputOption.ADMISSION );
            student.setDayAdmission ( dayAdmission );
            String estimatedEndDate = setEndDate ( dayAdmission );
            student.setEstimatedEndDate ( estimatedEndDate );
            studentService.edit ( student );
            System.out.println ();
            System.out.println ( "THÀNH CÔNG!" );
            System.out.println ();
            System.out.println ( "Nhấn (1) để thêm tiếp  ||  (2) để mở tùy chọn" );
            int choises = AppUtils.retryChoose ( 1, 2 );
            switch (choises) {
                case 1:
                    addAll ();
                    break;
                case 2:
                    AppUtils.isRetryAdmin ( InputOption.ADD_INFO );
                    break;
                default:
                    System.out.print ( "Nhập sai, hãy nhập lại: " );
                    break;
            }
        } while (true);
    }

    public void addOne() {
        ShowStudent list = new ShowStudent ();
        list.showToAction ();
        String code = inputCode ( InputOption.INFO );
        do {
            Student students = new Student ();
            students.setCode ( code );
            Student student = studentService.getByCode ( code );
            System.out.println ( "Nhập thông tin chi tiết cho học viên: " + student.getName () );
            System.out.println ( "╒══════════════ ✎ THÊM THÔNG TIN ✎ ══════════════╕" );
            System.out.println ( "│                                                 │" );
            System.out.println ( "│                 ✍ 1. Trạng thái                 │" );
            System.out.println ( "│                 ✍ 2. CCCD | CMND                │" );
            System.out.println ( "│                 ✍ 3. Địa chỉ                    │" );
            System.out.println ( "│                 ✍ 4. Người thân                 │" );
            System.out.println ( "│                 ✍ 5. Ngày nhập học              │" );
            System.out.println ( "│                                                 │" );
            System.out.println ( "│ ◌ 6. Đăng xuất |  ◌ 7. Quay lại    | ◌ 8. Thoát │" );
            System.out.println ( "╘═════════════════════════════════════════════════╛" );
            int choose = AppUtils.retryChoose ( 1, 8 );
            switch (choose) {
                case 1:
                    System.out.println ( "Trạng thái hiện tại: " + student.getStatus () );
                    String status = inputStatus ();
                    student.setStatus ( status );
                    studentService.edit ( student );
                    System.out.println ( "✑✑ Thay đổi trạng thái thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.ADD_INFO );
                    break;
                case 2:
                    System.out.println ( "Số CCCD | CMND hiện tại: " + student.getCitizenNum () );
                    String citizenNum = inputCitizenNum ();
                    student.setCitizenNum ( citizenNum );
                    System.out.println ( "Ngày cấp hiện tại: " + student.getDateRange () );
                    String dateRange = inputDate ( InputOption.INFO );
                    student.setDateRange ( dateRange );
                    System.out.println ( "Nơi cấp hiện tại: " + student.getIssuedBy () );
                    String issuedBy = inputIssuedBy ();
                    student.setIssuedBy ( issuedBy );
                    studentService.edit ( student );
                    System.out.println ( "✑✑ Thay đổi thông tin CCCD (CMND) thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.ADD_INFO );
                    break;
                case 3:
                    System.out.println ( "Địa chỉ hiện tại: " + student.getAddress () );
                    String address = inputAddress ();
                    student.setAddress ( address );
                    studentService.edit ( student );
                    System.out.println ( "✑✑ Thay đổi địa chỉ thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.ADD_INFO );
                    break;
                case 4:
                    System.out.println ( "Tên người thân hiện tại: " + student.getRelative () );
                    String relative = inputFullName ( InputOption.INFO );
                    student.setRelative ( relative );
                    System.out.println ( "Số ĐT của người thân hiện tại: " + student.getRelativePhoneNum () );
                    String relativePhoneNum = inputPhoneNum ( InputOption.INFO );
                    student.setRelativePhoneNum ( relativePhoneNum );
                    studentService.edit ( student );
                    System.out.println ( "✑✑ Thay đổi thông tin người thân thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.ADD_INFO );
                    break;
                case 5:
                    System.out.println ( "Ngày nhập học hiện tại: " + student.getDayAdmission () );
                    String dayAdmission = inputDate ( InputOption.ADMISSION );
                    student.setDayAdmission ( dayAdmission );
                    String estimatedEndDate = setEndDate ( dayAdmission );
                    student.setEstimatedEndDate ( estimatedEndDate );
                    studentService.edit ( student );
                    System.out.println ( "✑✑ Thay đổi ngày nhập học thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.ADD_INFO );
                    break;
                case 6:
                    AppUtils.login ();
                    break;
                case 7:
                    ShowStudent showStudent = new ShowStudent ();
                    showStudent.showAddInfoStudent ();
                    break;
                case 8:
                    AppUtils.exit ();
                    break;
            }
        } while (true);
    }

    // Sửa thông tin tài khoản học viên
    public void editStudent() {

        ShowStudent list = new ShowStudent ();
        list.showToAction ();
        System.out.println ( "✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍" );
        String code = inputCode ( InputOption.UPDATE );
        do {
            Student student = studentService.getByCode ( code );
            System.out.println ();
            System.out.println ();
            System.out.println ( "➧➧➧➧➧➧➧ Thông tin học viên cần chỉnh sửa: " );
            System.out.printf ( "Mã học viên: %s\nLớp học: %s\nHọ tên: %s\nGiới tính: %s\nNgày sinh: %s\nSố điện thoại: %s\nEmail: %s\n",
                    student.getCode (), student.getClasses (), student.getName (), student.getGender (), student.getDateOfBirth (), student.getPhoneNumber (), student.getEmail () );
            System.out.println ();
            System.out.println ( "╒════════════════ ✎ CHỈNH SỬA ✎ ════════════════╕" );
            System.out.println ( "│            ✍ 1. Lớp học                        │" );
            System.out.println ( "│            ✍ 2. Tên                            │" );
            System.out.println ( "│            ✍ 3. Giới tính                      │" );
            System.out.println ( "│            ✍ 4. Ngày sinh                      │" );
            System.out.println ( "│            ✍ 5. Số điện thoại                  │" );
            System.out.println ( "│            ✍ 6. Email                          │" );
            System.out.println ( "│            ✍ 7. Chỉnh sửa toàn bộ              │" );
            System.out.println ( "│            ✍ 8. Tùy chọn                       │" );
            System.out.println ( "╘════════════════════════════════════════════════╛" );
            int option = AppUtils.retryChoose ( 1, 8 );
            Student afterStudent = new Student ();
            afterStudent.setCode ( code );
            Student student_edit = studentService.getByCode ( code );
            switch (option) {
                case 1:
                    System.out.println ( "Lớp học hiện tại: " + student_edit.getClasses () );
                    String classes = inputClass ( InputOption.UPDATE );
                    afterStudent.setClasses ( classes );
                    studentService.edit ( afterStudent );
                    System.out.println ( "✑✑ Thay đổi lớp học thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.UPDATE );
                    break;
                case 2:
                    System.out.println ( "Tên hiện tại: " + student_edit.getName () );
                    String name = inputFullName ( InputOption.UPDATE );
                    afterStudent.setName ( name );
                    studentService.edit ( afterStudent );
                    System.out.println ( "✑✑ Thay đổi tên thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.UPDATE );
                    break;
                case 3:
                    System.out.println ( "Giới tính hiện tại: " + student_edit.getGender () );
                    String gender = inputGender ( InputOption.UPDATE );
                    afterStudent.setGender ( gender );
                    studentService.edit ( afterStudent );
                    System.out.println ( "✑✑ Thay đổi giới tính thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.UPDATE );
                    break;
                case 4:
                    System.out.println ( "Ngày sinh hiện tại: " + student_edit.getDateOfBirth () );
                    String dayOfBirth = inputDate ( InputOption.UPDATE );
                    afterStudent.setDateOfBirth ( dayOfBirth );
                    studentService.edit ( afterStudent );
                    System.out.println ( "✑✑ Thay đổi ngày sinh thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.UPDATE );
                    break;
                case 5:
                    System.out.println ( "Số điện thoại hiện tại: " + student_edit.getPhoneNumber () );
                    String phoneNum = inputPhoneNum ( InputOption.UPDATE );
                    afterStudent.setPhoneNumber ( phoneNum );
                    studentService.edit ( afterStudent );
                    System.out.println ( "✑✑ Thay đổi số điện thoại thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.UPDATE );
                    break;
                case 6:
                    System.out.println ( "Email hiện tại: " + student_edit.getEmail () );
                    String email = inputEmail ( InputOption.UPDATE );
                    afterStudent.setEmail ( email );
                    studentService.edit ( afterStudent );
                    System.out.println ( "✑✑ Thay đổi email thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.UPDATE );
                    break;
                case 7:
                    System.out.println ( "Lớp học hiện tại: " + student_edit.getClasses () );
                    String classesEdit = inputClass ( InputOption.UPDATE );
                    afterStudent.setClasses ( classesEdit );
                    System.out.println ( "Tên hiện tại: " + student_edit.getName () );
                    String nameEdit = inputFullName ( InputOption.UPDATE );
                    afterStudent.setName ( nameEdit );
                    System.out.println ( "Giới tính hiện tại: " + student_edit.getGender () );
                    String genderEdit = inputGender ( InputOption.UPDATE );
                    afterStudent.setGender ( genderEdit );
                    System.out.println ( "Ngày sinh hiện tại: " + student_edit.getDateOfBirth () );
                    String dayOfBirthEdit = inputDate ( InputOption.UPDATE );
                    afterStudent.setDateOfBirth ( dayOfBirthEdit );
                    System.out.println ( "Số điện thoại hiện tại: " + student_edit.getPhoneNumber () );
                    String phoneNumEdit = inputPhoneNum ( InputOption.UPDATE );
                    afterStudent.setPhoneNumber ( phoneNumEdit );
                    System.out.println ( "Email hiện tại: " + student_edit.getEmail () );
                    String emailEdit = inputEmail ( InputOption.UPDATE );
                    afterStudent.setEmail ( emailEdit );
                    studentService.edit ( afterStudent );
                    System.out.println ( "✑✑ Thay đổi thành công ✑✑" );
                    AppUtils.isRetryAdmin ( InputOption.UPDATE );
                    break;
                case 8:
                    AppUtils.isRetryAdmin ( InputOption.UPDATE );
                    break;
            }
        } while (true);
    }


    public void removeStudent() {
        do {
            ShowStudent list = new ShowStudent ();
            list.showToAction ();
            String code;
            code = inputCode ( InputOption.DELETE );
            Student student = studentService.getByCode ( code );
            System.out.println ();
            System.out.println ( "➧➧➧➧➧➧➧ Thông tin học viên muốn xóa: " );
            System.out.printf ( "Mã học viên: %s\nLớp học: %s\nHọ tên: %s\nGiới tính: %s\nNgày sinh: %s\nSố điện thoại: %s\nEmail: %s\n",
                    student.getCode (), student.getClasses (), student.getName (), student.getGender (), student.getDateOfBirth (), student.getPhoneNumber (), student.getEmail () );
            System.out.println ();
            System.out.println ( "╒═════BẠN CÓ CHẮC MUỐN XÓA═════╕" );
            System.out.println ( "│                              │" );
            System.out.println ( "│        ▫ 1. Xác nhận         │" );
            System.out.println ( "│        ▪ 2. Hủy bỏ           │" );
            System.out.println ( "│                              │" );
            System.out.println ( "╘═════════◦◦◦◦◦◦◦◦◦◦◦◦═════════╛" );
            int option = AppUtils.retryChoose ( 1, 2 );
            if ( option == 1 ) {
                studentService.deleteByCode ( code );
                System.out.println ( "Đã xóa thành công!" );
                AppUtils.isRetryAdmin ( InputOption.DELETE );
            } else if ( option == 2 ) {
                AppUtils.isRetryAdmin ( InputOption.DELETE );
            }
        } while (true);
    }

    public String inputClass(InputOption option) {
        switch (option) {
            case ADD:
                System.out.print ( "Nhập lớp học (VD: C0322G1): " );
                break;
            case UPDATE:
                System.out.print ( "Nhập lớp học muốn thay đổi: " );
                break;
        }
        String classes;
        do {
            if ( !ValidateUtils.classValid ( classes = AppUtils.retryString ( "Tên lớp" ) ) ) {
                System.out.print ( classes + " không đúng định dạng, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return classes;
    }

    public String inputFullName(InputOption option) {
        switch (option) {
            case ADD:
                System.out.print ( "Nhập họ và tên (VD: Tran Ngoc Trung): " );
                break;
            case UPDATE:
                System.out.print ( "Nhập tên muốn thay đổi: " );
                break;
            case INFO:
                System.out.print ( "Nhập họ và tên người thân: " );
                break;
            case SEARCH:
                System.out.print ( "Nhập tên học viên cần tìm kiếm: " );
        }
        String fullName;
        do {
            if ( !ValidateUtils.fullNameValid ( fullName = AppUtils.retryString ( "Họ và tên" ) ) ) {
                System.out.print ( fullName + " không đúng định dạng, hãy nhập lại (VD: Tran Ngoc Trung): " );
                continue;
            }
            break;
        } while (true);
        return fullName;
    }

    public String inputGender(InputOption option) {
        String gender = "";
        System.out.println ( "╒══════GIỚI TÍNH══════╕" );
        System.out.println ( "│      ▫ 1. Nam       │" );
        System.out.println ( "│      ▪ 2. Nữ        │" );
        System.out.println ( "│      ▫ 3. Khác      │" );
        System.out.println ( "╘══════◦◦◦◦◦◦◦◦◦══════╛" );
        int options = AppUtils.retryChoose ( 1, 3 );
        if ( options == 1 ) {
            gender = "Nam";
        } else if ( options == 2 ) {
            gender = "Nữ";
        } else if ( options == 3 ) {
            gender = "Khác";
        }
        return gender;
    }

    public String inputDate(InputOption option) {
        switch (option) {
            case ADD:
                System.out.print ( "Nhập ngày sinh (dd-MM-yyyy): " );
                break;
            case UPDATE:
                System.out.print ( "Nhập ngày sinh muốn thay đổi (dd-MM-yyyy): " );
                break;
            case INFO:
                System.out.print ( "Nhập ngày cấp (dd-MM-yyyy): " );
                break;
            case ADMISSION:
                System.out.print ( "Nhập học ngày (dd-MM-yyyy): " );
                break;
        }
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-MM-yyyy" );
        String date;
        do {
            if ( !ValidateUtils.dateValid ( date = AppUtils.retryString ( "Ngày sinh" ) ) ) {
                System.out.print ( date + " không đúng, hãy nhập lại: " );
                continue;
            }
            try {
                Date dateinput = sdf.parse ( date );
                int compareTime = dateinput.compareTo ( Date.from ( Instant.now () ) );
                if ( compareTime > 0 ) {
                    System.out.print ( "Thời gian không hợp lệ, hãy nhập lại: " );
                    continue;
                }
            } catch (ParseException e) {
                System.out.print ( date + " nhập sai, hãy nhập lại: " );
            }
            break;
        } while (true);
        return date;
    }

    public String inputPhoneNum(InputOption option) {
        switch (option) {
            case ADD:
                System.out.print ( "Nhập số điện thoại: " );
                break;
            case UPDATE:
                System.out.print ( "Nhập số điện thoại muốn sửa: " );
                break;
            case INFO:
                System.out.print ( "Nhập số điện thoại của người thân: " );
                break;
        }
        String phoneNum;
        do {
            switch (option) {
                case ADD:
                case UPDATE:
                case INFO:
                    if ( !ValidateUtils.phoneNumValid ( phoneNum = AppUtils.retryString ( "Số điện thoại" ) ) ) {
                        System.out.print ( phoneNum + " không đúng định dạng, hãy nhập lại:" );
                        continue;
                    }
                    if ( studentService.existsPhoneNumber ( phoneNum ) ) {
                        System.out.print ( "Số điện thoại đã tồn tại, hãy nhập lại: " );
                        continue;
                    }
                    return phoneNum;
//                    break;
                case SEARCH:
                    if ( !ValidateUtils.phoneNumValid ( phoneNum = AppUtils.retryString ( "Số điện thoại" ) ) ) {
                        System.out.print ( phoneNum + " không đúng định dạng, hãy nhập lại: " );
                        continue;
                    }
                    if ( !studentService.existsPhoneNumber ( phoneNum ) ) {
                        System.out.print ( "Số điện thoại không tồn tại, hãy nhập lại: " );
                        continue;
                    }
                    return phoneNum;
//                    break;
            }
        } while (true);
    }

    public String inputCode(InputOption option) {
        switch (option) {
            case DELETE:
                System.out.print ( "Nhập mã học viên muốn xóa: " );
                break;
            case UPDATE:
                System.out.print ( "Nhập mã học viên muốn sửa: " );
                break;
            case INFO:
                System.out.print ( "Nhập mã học viên muốn thêm thông tin: " );
                break;
        }
        String code;
        do {
            if ( !ValidateUtils.codeValid ( code = AppUtils.retryString ( "Mã học viên" ) ) ) {
                System.out.print ( code + " không đúng, hãy nhập lại: " );
                continue;
            }
            if ( !studentService.existsCode ( code ) ) {
                System.out.print ( "Mã học viên không tồn tại, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return code;
    }

    public String inputEmail() {
        System.out.print ( "❒ Email đăng nhập: " );
        String email;
        do {
            if ( !ValidateUtils.emailValid ( email = AppUtils.retryString ( "Email đăng nhập" ) ) ) {
                System.out.print ( email + " không đúng, hãy nhập lại: " );
                continue;
            }
            if ( !adminService.existsEmail ( email ) ) {
                System.out.print ( "Email không tồn tại, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return email;
    }

    public String inputEmail(InputOption option) {
        switch (option) {
            case ADD:
                System.out.print ( "❒ Nhập email: " );
                break;
            case UPDATE:
                System.out.print ( "❒ Nhập email muốn sửa: " );
                break;
        }
        String email;
        do {
            if ( !ValidateUtils.emailValid ( email = AppUtils.retryString ( "Email" ) ) ) {
                System.out.print ( email + " không đúng định dạng, hãy nhập lại: " );
                continue;
            }
            if ( studentService.existsEmail ( email ) ) {
                System.out.print ( "Email đã tồn tại, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return email;
    }
    public String inputEmailAdmin(InputOption option) {
        switch (option) {
            case SIGNIN:
                System.out.print ( "❒ Nhập email: " );
                break;
            case ADD:
                System.out.print ( "❒ Nhập email muốn thêm: " );
                break;
        }
        String email = null;
        do {
            switch (option) {
                case SIGNIN:
                    if ( !ValidateUtils.emailValid ( email = AppUtils.retryString ( "Email" ) ) ) {
                        System.out.print ( email + " không đúng định dạng, hãy nhập lại: " );
                        continue;
                    }
                    if ( !adminService.existsEmail ( email ) ) {
                        System.out.print ( "Email không tồn tại, hãy nhập lại: " );
                        continue;
                    }
                    break;
                case ADD:
                    if ( !ValidateUtils.emailValid ( email = AppUtils.retryString ( "Email" ) ) ) {
                        System.out.print ( email + " không đúng định dạng, hãy nhập lại: " );
                        continue;
                    }
                    if ( adminService.existsEmail ( email ) ) {
                        System.out.print ( "Email đã tồn tại, hãy nhập lại: " );
                        continue;
                    }
                    break;
            }
            break;
//            if ( !ValidateUtils.emailValid ( email = AppUtils.retryString ( "Email" ) ) ) {
//                System.out.print ( email + " không đúng định dạng, hãy nhập lại: " );
//                continue;
//            }
//            if ( adminService.existsEmail ( email ) ) {
//                System.out.print ( "Email đã tồn tại, hãy nhập lại: " );
//                continue;
//            }
//            break;
        } while (true);
        return email;
    }

    public String inputPassword(InputOption option) {
        switch (option) {
            case SIGNIN:
                System.out.print ( "❒ Nhập mật khẩu: " );
                break;
            case CHANGE_PASSWORD:
                System.out.print ( "❒ Nhập mật khẩu mới: " );
                break;
        }
        String password;
        do {
            if ( !ValidateUtils.passwordValid ( password = AppUtils.retryString ( "Mật khẩu" ) ) ) {
                switch (option) {
                    case SIGNIN:
                        System.out.println ( "Sai mật khẩu!" );
                        AppUtils.isRetryAdmin ( InputOption.SIGNIN );
                        break;
                    case CHANGE_PASSWORD:
                        System.out.print ( password + " không đúng (8-15 ký tự, bắt đầu bằng chữ hoa, gồm: chữ; số; ký tự đặc biệt), hãy nhập lại: " );
                        break;
                }
                continue;
            }
            break;
        } while (true);
        return password;
    }

    public String inputStatus() {
        String status = "";
        System.out.println ( "Chọn trạng thái: " );
        System.out.println ( "╒══════════TRẠNG THÁI══════════╕" );
        System.out.println ( "│   ▫ 1. Đang học              │" );
        System.out.println ( "│   ▪ 2. Hoàn thành khóa học   │" );
        System.out.println ( "╘═════════◦◦◦◦◦◦◦◦◦◦◦◦═════════╛" );
        int options = AppUtils.retryChoose ( 1, 2 );
        if ( options == 1 ) {
            status = "Đang học";
        } else if ( options == 2 ) {
            status = "Hoàn thành khóa học";
        }
        return status;
    }

    public String inputPosition() {
        String position = "";
        System.out.println ( "Chọn trạng thái: " );
        System.out.println ( "╒══════════CHỨC VỤ══════════╕" );
        System.out.println ( "│      ▫ 1. Giám đốc        │" );
        System.out.println ( "│      ▪ 2. Giáo vụ         │" );
        System.out.println ( "│      ▫ 3. Giảng viên      │" );
        System.out.println ( "╘════════◦◦◦◦◦◦◦◦◦◦◦════════╛" );
        int options = AppUtils.retryChoose ( 1, 3 );
        if ( options == 1 ) {
            position = "Giám đốc";
        } else if ( options == 2 ) {
            position = "Giáo vụ";
        } else if ( options == 3 ) {
            position = "Giảng viên";
        }
        return position;
    }

    public String inputCitizenNum() {
        System.out.print ( "❒ Nhập CCCD | CMND: " );
        String citizenNum;
        do {
            if ( !ValidateUtils.citizenNumValid ( citizenNum = AppUtils.retryString ( "CCCD | CMND" ) ) ) {
                System.out.print ( citizenNum + " không đúng định dạng, hãy nhập lại: " );
                continue;
            }
            if ( studentService.existsCitizenNum ( citizenNum ) ) {
                System.out.print ( "Đã tồn tại, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return citizenNum;
    }

    private String inputYear() {
        System.out.print ( "❒ Nhập năm sinh: " );
        String year;
        do {
            if ( !ValidateUtils.yearValid ( year = AppUtils.retryString ( "Năm sinh" ) ) ) {
                System.out.print ( year + " không đúng, hãy nhập lại [1960 - 2003]: " );
                continue;
            }
            break;
        } while (true);
        return year;
    }

    private String inputIssuedBy() {
        System.out.print ( "❒ Nhập nơi cấp: " );
        String issuedBy;
        do {
            if ( !ValidateUtils.issuedByValid ( issuedBy = AppUtils.retryString ( "Nơi cấp" ) ) ) {
                System.out.print ( issuedBy + " không đúng, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return issuedBy;
    }

    private String inputAddress() {
        System.out.print ( "❒ Nhập địa chỉ: " );
        String address;
        do {
            if ( !ValidateUtils.addressValid ( address = AppUtils.retryString ( "Địa chỉ" ) ) ) {
                System.out.print ( address + " không đúng, hãy nhập lại (gồm chữ hoặc số hoặc dấu '.'): " );
                continue;
            }
            break;
        } while (true);
        return address;
    }

    public String setEndDate(String dayAdmission) {
        String estimatedEndDate;
        SimpleDateFormat sdf = new SimpleDateFormat ( "dd-MM-yyyy" );
        Calendar calendar = Calendar.getInstance ();
        Date date;
        try {
            date = sdf.parse ( dayAdmission );
        } catch (ParseException e) {
            throw new RuntimeException ( e );
        }
        calendar.setTime ( date );
        calendar.add ( Calendar.MONTH, 6 );
        estimatedEndDate = sdf.format ( calendar.getTime () );
        return estimatedEndDate;
    }

    public static boolean isRetry() {
        do {
            try {
                System.out.println ( "╔———————————————— CHỌN ———————————————╗" );
                System.out.println ( "│                                     │" );
                System.out.println ( "│    Nhấn 'a' để đăng nhập lại        │" );
                System.out.println ( "│    Nhấn 's' để về trang đăng nhập   │" );
                System.out.println ( "│    Nhấn 'e' để thoát chương trình   │" );
                System.out.println ( "│                                     │" );
                System.out.println ( "╚—————————————————————————————————————╝" );
                System.out.print ( " ⭆ " );
                String option = input.nextLine ();
                switch (option) {
                    case "a":
                        return true;
                    case "s":
                        AppUtils.login ();
                        break;
                    case "e":
                        AppUtils.exit ();
                        break;
                    default:
                        System.out.println ( "Chọn chức năng không đúng! Vui lòng chọn lại" );
                        break;
                }

            } catch (Exception ex) {
                System.out.println ( "❒ Nhập sai! vui lòng nhập lại" );
                ex.printStackTrace ();
            }
        } while (true);
    }
}