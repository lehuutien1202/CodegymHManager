package cs.tntrung.cg.views;

import cs.tntrung.cg.utils.AppUtils;

public class LaunchReiptView {
    ReceiptView receiptView = new ReceiptView ();

    public void launch() {
        do {
            System.out.println ( "                   CODEGYM HUẾ                   " );
            System.out.println ( "∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙" );
            System.out.println ( "      ➤  KỶ LUẬT - NĂNG ĐỘNG - CẦU TIẾN  ➤       " );
            System.out.println ( "❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤" );
            System.out.println ( "╒═══════════════════ BIÊN LAI ═══════════════════╕" );
            System.out.println ( "│                                                │" );
            System.out.println ( "│              ● 1. Thêm biên lai                │" );
            System.out.println ( "│              ● 2. Danh sách biên lai           │" );
            System.out.println ( "│              ● 3. Sửa biên lai                 │" );
            System.out.println ( "│              ● 4. Xóa biên lai                 │" );
            System.out.println ( "│              ● 5. In biên lai                  │" );
            System.out.println ( "│                                                │" );
            System.out.println ( "│ ◌ 6. Quay lại  | ◌ 7. Đăng xuất   | ◌ 8. Thoát │" );
            System.out.println ( "╘════════════════════════════════════════════════╛" );
            int choose = AppUtils.retryChoose ( 1, 8 );
            switch (choose) {
                case 1:
                    receiptView.addReceipt ();
                    break;
                case 2:
                    receiptView.showReceiptList ();
                    break;
                case 3:
                    receiptView.updateReceipt ();
                    break;
                case 4:
                    runRemove ();
                    break;
                case 5:
                    receiptView.printReceipt ();
                    break;
                case 6:
                    LaunchAdminView launchAdminView = new LaunchAdminView ();
                    launchAdminView.launch ();
                    break;
                case 7:
                    AppUtils.login ();
                    break;
                case 8:
                    AppUtils.exit ();
                    break;
            }
        } while (true);
    }

    public void runRemove() {
        do {
            System.out.println ( "                  CODEGYM HUẾ                   " );
            System.out.println ( "∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙∙" );
            System.out.println ( "      ➤  KỶ LUẬT - NĂNG ĐỘNG - CẦU TIẾN  ➤      " );
            System.out.println ( "❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤❤" );
            System.out.println ( "╒═════════════════ XÓA BIÊN LAI ═══════════════╕" );
            System.out.println ( "│                                              │" );
            System.out.println ( "│      ● 1. Xóa hết biên lai của học viên      │" );
            System.out.println ( "│      ● 2. Xóa theo mã biên lai               │" );
            System.out.println ( "│                                              │" );
            System.out.println ( "│ ◌ 3. Quay lại | ◌ 4. Đăng xuất  | ◌ 5. Thoát │" );
            System.out.println ( "╘══════════════════════════════════════════════╛" );
            int choise = AppUtils.retryChoose ( 1, 5 );
            switch (choise) {
                case 1:
                    receiptView.removeReceiptByCode ();
                    break;
                case 2:
                    receiptView.removeReceiptByReceiptCode ();
                    break;
                case 3:
                    launch ();
                    break;
                case 4:
                    AppUtils.login ();
                    break;
                case 5:
                    AppUtils.exit ();
                    break;
            }
        } while (true);
    }
}
