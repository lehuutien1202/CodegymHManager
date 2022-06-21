package cs.tntrung.cg.views;

import cs.tntrung.cg.model.Receipt;
import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.services.IStudentService;
import cs.tntrung.cg.services.ReceiptServices;
import cs.tntrung.cg.services.StudentServices;
import cs.tntrung.cg.utils.AppUtils;
import cs.tntrung.cg.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final Scanner input = new Scanner ( System.in );
    IStudentService studentService = new StudentServices ();

    ReceiptServices receiptServices = new ReceiptServices ();
    AdminView adminView = new AdminView ();

    public void loginStudent() {
        boolean isRetry;
        System.out.println ();
        System.out.println ( " ✎✎✎ ĐĂNG NHẬP DÀNH CHO HỌC VIÊN ✎✎✎ " );
        System.out.println ();
        do {
            String email = inputEmail ();
            String password = adminView.inputPassword (InputOption.SIGNIN);
            if ( studentService.studentLogin ( email, password ) == null ) {
                System.out.println ( "Tài khoản không tồn tại!" );
                isRetry = AdminView.isRetry ();
            } else {
                System.out.println ();
                System.out.println ( "                 ĐĂNG NHẬP THÀNH CÔNG" );
                System.out.println ();
                Student student_1 = studentService.getByEmail ( email );
                String code = student_1.getCode ();
                LaunchStudentView student = new LaunchStudentView ();
                student.launch (code);
                isRetry = false;
            }
        } while (isRetry);
    }

    public void changePassword(String code) {
        System.out.println ();
        System.out.println ( "===== ĐỔI MẬT KHẨU =====" );
        System.out.println ();
        Student student = studentService.getByCode ( code );
        String beforePw;
        String afterPw;
        System.out.print ("❒ Nhập mật khẩu cũ: ");
        do {
            beforePw = input.nextLine ();
            if (!student.getPassword ().equals ( beforePw )) {
                System.out.print ("Mật khẩu không đúng, nhập lại:");
                continue;
            }else {
                do {
                    afterPw  = adminView.inputPassword (InputOption.CHANGE_PASSWORD);
                    if ( afterPw.equals ( beforePw ) ){
                        System.out.println ("Trùng mật khẩu cũ, nhập lại: ");
                        continue;
                    }else {
                        System.out.print ("❒ Nhập lại mật khẩu mới: ");
                        String afterPw2;
                        do {
                            afterPw2 = input.nextLine ();
                            if (!afterPw.equals ( afterPw2 )){
                                System.out.print ("Mật khẩu không khớp, nhập lại: ");
                                continue;
                            }
                            break;
                        }while (true);
                    }
                    break;
                } while (true);
            }
            break;
        }while (true);
        student.setPassword ( afterPw );
        studentService.edit ( student );
        System.out.println ();
        System.out.println ("THAY ĐỔI MẬT KHẨU THÀNH CÔNG!");
        System.out.println ();
//        AppUtils.isRetryStudent ( InputOption.CHANGE_PASSWORD );
    }

    public String inputEmail() {
        System.out.print ( "❒ Email đăng nhập: " );
        String email;
        do {
            if ( !ValidateUtils.emailValid ( email = AppUtils.retryString ( "Email đăng nhập" ) ) ) {
                System.out.print ( email + " không đúng định dạng, hãy nhập lại: " );
                continue;
            }
            if ( !studentService.existsEmail ( email ) ) {
                System.out.print ( "Email không tồn tại, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return email;
    }

    public String inputCode() {
        System.out.print ( "❒ Nhập mã học viên của bạn: " );
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
    public void tuition(String code){
        System.out.println ();
        List<Receipt> receipts = receiptServices.receiptByCode ( code );
        ArrayList<Integer> nums = new ArrayList<> ();
        for (Receipt receipt : receipts) {
            if ( receipt.getStatus ().equals ( "Đã in" ) ){
                nums.add ( receipt.getCountMonth () );
            }
        }
        int max = nums.get ( 0 );
        for (int i = 0; i < nums.size (); i++) {
            if ( nums.get ( i ) > max) {
                max = nums.get ( i );
            }
        }
        long money = max * 7000000L;
        System.out.println ("Số đợt đã nộp học phí: " + max + " đợt");
        System.out.println ("Số tiền đã đóng: " + AppUtils.doubleToVND ( money ));
        System.out.println ();
        System.out.println ("Nhập (1) để quay lại || (2) để đăng xuất || (3) để thoát");
//        System.out.print ("Chọn ➤ ");
        int option = AppUtils.retryChoose ( 1, 3 );
        switch (option) {
            case 1:
                LaunchStudentView launchStudentView = new LaunchStudentView ();
                launchStudentView.launch ( code );
                break;
            case 2:
                AppUtils.login ();
                break;
            case 3:
                AppUtils.exit ();
                break;
        }
    }
}
