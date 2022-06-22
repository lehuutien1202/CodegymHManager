package cs.tntrung.cg.views;

import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.services.StudentServices;
import cs.tntrung.cg.utils.AppUtils;

import java.util.Scanner;

public class LaunchStudentView {
    AdminView adminView = new AdminView ();
    StudentView studentView = new StudentView ();
    Scanner input = new Scanner ( System.in );

    public void launch(String code) {
        do {

            System.out.println ( "                     CODEGYM HUẾ                   " );
            System.out.println ( "∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙" );
            System.out.println ( "         ➤  KỶ LUẬT - NĂNG ĐỘNG - CẦU TIẾN  ➤      " );
            System.out.println ( "❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤" );
            System.out.println ( "╒═══════════════════ HỌC VIÊN ═══════════════════╕" );
            System.out.println ( "│         ● 1. Thông tin học viên                │" );
            System.out.println ( "│         ● 2. Đổi mật khẩu                      │" );
            System.out.println ( "│         ● 3. Học phí                           │" );
            System.out.println ( "│ ◌ 4. Đăng xuất                      ◌ 5. Thoát │" );
            System.out.println ( "╘════════════════════════════════════════════════╛" );
            int choise = AppUtils.retryChoose ( 1, 5 );
            switch (choise) {
                case 1:
                    StudentServices services = new StudentServices ();
                    Student student = services.getByCode ( code );
                    System.out.printf ( "Mã học viên: %s\nLớp học: %s\nHọ tên: %s\nGiới tính: %s\nNgày sinh: %s\nSố điện thoại: %s\nEmail: %s\nTrạng thái: %s\nCCCD | CMND: %s\nNgày cấp: %s\nNơi cấp: %s\nNgười thân: %s\nSĐT người thân: %s\nNgày nhập học: %s\nNgày dự kiến kết thúc: %s\n",
                            student.getCode (), student.getClasses (), student.getName (),
                            student.getGender (), student.getDateOfBirth (), student.getPhoneNumber (),
                            student.getEmail (), student.getStatus (), student.getCitizenNum (),
                            student.getDateRange (), student.getIssuedBy (), student.getRelative (),
                            student.getRelativePhoneNum (), student.getDayAdmission (), student.getEstimatedEndDate () );
                    System.out.println ( "Nhấn (1) để quay lại  ||  (2) để đăng xuất  ||  (3) để thoát" );
                    int option = AppUtils.retryChoose ( 1,3 );
                    switch (option){
                        case 1:
                            launch ( code );
                            break;
                        case 2:
                            Login.login ();
                            break;
                        case 3:
                            AppUtils.exit ();
                            break;
                    }
                    break;
                case 2:
                    studentView.changePassword (code);
                    break;
                case 3:
                    studentView.tuition ( code );
                    break;
                case 4:
                    AppUtils.login ();
                    break;
                case 5:
                    AppUtils.exit ();
                    break;
            }
        }while (true);
    }
}
