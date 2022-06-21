package cs.tntrung.cg.views;

import cs.tntrung.cg.utils.AppUtils;

import java.util.Scanner;

public class LaunchAdminView {
    Scanner input = new Scanner ( System.in );

    public void launch() {
        do {
            AdminView adminView = new AdminView ();
            System.out.println ( "                     CODEGYM HUẾ                   " );
            System.out.println ( "∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙" );
            System.out.println ( "         ➤  KỶ LUẬT - NĂNG ĐỘNG - CẦU TIẾN  ➤      " );
            System.out.println ( "❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤" );
            System.out.println ( "╒═════════════════ QUẢN TRỊ VIÊN ═════════════════╕" );
            System.out.println ( "│                                                 │" );
            System.out.println ( "│         ● 1. Danh sách học viên                 │" );
            System.out.println ( "│         ● 2. Tạo tài khoản                      │" );
            System.out.println ( "│         ● 3. Thông tin học viên                 │" );
            System.out.println ( "│         ● 4. Chỉnh sửa tài khoản học viên       │" );
            System.out.println ( "│         ● 5. Xóa tài khoản học viên             │" );
            System.out.println ( "│         ● 6. Biên lai                           │" );
            System.out.println ( "│                                                 │" );
            System.out.println ( "│ ◌ 7. Đăng xuất                       ◌ 8. Thoát │" );
            System.out.println ( "╘═════════════════════════════════════════════════╛" );
            int choice = AppUtils.retryChoose ( 1,8 );
            switch (choice) {
                case 1:
                    adminView.showStudentList ();
                    break;
                case 2:
                    adminView.singup ();
                    break;
                case 3:
                    adminView.infoStudent ();
                    break;
                case 4:
                    adminView.editStudent ();
                    break;
                case 5:
                    adminView.removeStudent ();
                    break;
                case 6:
                    LaunchReiptView launchReiptView = new LaunchReiptView ();
                    launchReiptView.launch ();
                    break;
                case 7:
                    AppUtils.login ();
                    break;
                case 8:
                    AppUtils.exit ();
                    break;
            }
        }while (true);
    }

    public void editLaunch() {
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
    }

    public void infoStudentView() {
        System.out.println ( "╒═══════════════ THÔNG TIN HỌC VIÊN ═══════════════╕" );
        System.out.println ( "│                                                  │" );
        System.out.println ( "│          ▸ 1. Thêm thông tin chi tiết            │" );
        System.out.println ( "│          ▹ 2. Xem thông tin học viên             │" );
        System.out.println ( "│                                                  │" );
        System.out.println ( "│ ◌ 3. Đăng xuất |  ◌ 4. Quay lại    | ◌ 5. Thoát  │" );
        System.out.println ( "╘══════════════════════════════════════════════════╛" );
    }

}
