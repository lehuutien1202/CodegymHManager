package cs.tntrung.cg.views;

import cs.tntrung.cg.utils.AppUtils;


public class Login {
    public static void login() {
        do {
            System.out.println ("                 ☝ CODEGYM HUẾ ☝");
            System.out.println ("       ➤ KỶ LUẬT - NĂNG ĐỘNG - CẦU TIẾN ➤");
            System.out.println ();
            System.out.println ("╒═════════════════ ĐĂNG NHẬP ═════════════════╕");
            System.out.println ("│                                             │");
            System.out.println ("│             ● 1. Quản trị viên              │");
            System.out.println ("│             ● 2. Học viên                   │");
            System.out.println ("│             ● 3. Đóng chương trình          │");
            System.out.println ("│                                             │");
            System.out.println ("╘═════════════════════════════════════════════╛");
            int choise = AppUtils.retryChoose ( 1,3 );
            switch (choise) {
                case 1:
                    AdminView adminView = new AdminView ();
                    adminView.loginAdmin ();
                    break;
                case 2:
                    StudentView studentView = new StudentView ();
                    studentView.loginStudent ();
                    break;
                case 3:
                    AppUtils.exit ();
                    break;
            }
        }while (true);
    }
}
