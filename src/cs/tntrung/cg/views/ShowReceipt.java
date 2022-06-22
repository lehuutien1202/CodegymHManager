package cs.tntrung.cg.views;

import cs.tntrung.cg.model.Receipt;
import cs.tntrung.cg.services.ReceiptServices;
import cs.tntrung.cg.utils.AppUtils;
import cs.tntrung.cg.utils.InstantUtils;

import java.util.List;

public class ShowReceipt {
    ReceiptServices receiptServices = new ReceiptServices ();

    public void showAll() {
        List<Receipt> receipts = receiptServices.findAll ();
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                                                               DANH SÁCH BIÊN LAI                                                                                                                  ║" );
        System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );


        System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15s│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                "STT", "Mã học viên", "Tên học viên", "Mã biên lai", "Số tiền", "Số tiền (bằng chữ)", "Đợt", "Địa chỉ", "Trạng thái", "Thời gian tạo", "Thời gian sửa" );
        System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        int i = 1;
        for (Receipt receipt : receipts) {
            System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15d│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    i, receipt.getCode (),
                    receipt.getName (),
                    receipt.getReceiptCode (),
                    AppUtils.doubleToVND ( receipt.getMoney () ),
                    receipt.getReadMoney (),
                    receipt.getCountMonth (),
                    receipt.getAddress (),
                    receipt.getStatus (),
                    InstantUtils.instantToStringDayTime ( receipt.getCreatAt () ),
                    receipt.getUpdateAt () == null ? "" : InstantUtils.instantToStringDayTime ( receipt.getUpdateAt () )
            );
            System.out.println ( "╟−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−-−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−╢" );
            i++;
        }
        System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
        System.out.println ( "Nhấn (1) để sắp xếp theo số tiền tăng dần ||  (2) để mở tùy chọn" );
        int choises_11 = AppUtils.retryChoose ( 1, 2 );
        switch (choises_11) {
            case 1:
                showByMoneyADC ();
                break;
            case 2:
                AppUtils.isRetryReceipt ( InputOption.SHOW );
                break;
        }
    }

    public void showByMoneyADC() {
        List<Receipt> receipts = receiptServices.findAll ();
        List<Receipt> receiptList = receiptServices.sortMoneyADC ( receipts );
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                                                  DANH SÁCH BIÊN LAI THEO SỐ TIỀN TĂNG DẦN                                                                                                         ║" );
        System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );


        System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15s│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                "STT", "Mã học viên", "Tên học viên", "Mã biên lai", "Số tiền", "Số tiền (bằng chữ)", "Đợt", "Địa chỉ", "Trạng thái", "Thời gian tạo", "Thời gian sửa" );
        System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        int i = 1;
        for (Receipt receipt : receiptList) {
            System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15d│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    i, receipt.getCode (),
                    receipt.getName (),
                    receipt.getReceiptCode (),
                    AppUtils.doubleToVND ( receipt.getMoney () ),
                    receipt.getReadMoney (),
                    receipt.getCountMonth (),
                    receipt.getAddress (),
                    receipt.getStatus (),
                    InstantUtils.instantToStringDayTime ( receipt.getCreatAt () ),
                    receipt.getUpdateAt () == null ? "" : InstantUtils.instantToStringDayTime ( receipt.getUpdateAt () )
            );
            System.out.println ( "╟−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−-−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−╢" );
            i++;
        }
        System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
        System.out.println ( "Nhấn (1) để sắp xếp theo số tiền giảm dần ||  (2) để mở tùy chọn" );
        int choises_11 = AppUtils.retryChoose ( 1, 2 );
        switch (choises_11) {
            case 1:
                showByMoneyDEC ();
                break;
            case 2:
                AppUtils.isRetryReceipt ( InputOption.SHOW );
                break;
        }
    }
    public void showByMoneyDEC() {
        List<Receipt> receipts = receiptServices.findAll ();
        List<Receipt> receiptList = receiptServices.sortMoneyDEC ( receipts );
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                                                DANH SÁCH BIÊN LAI THEO SỐ TIỀN GIẢM DẦN                                                                                                      ║" );
        System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );


        System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15s│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                "STT", "Mã học viên", "Tên học viên", "Mã biên lai", "Số tiền", "Số tiền (bằng chữ)", "Đợt", "Địa chỉ", "Trạng thái", "Thời gian tạo", "Thời gian sửa" );
        System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        int i = 1;
        for (Receipt receipt : receiptList) {
            System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15d│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    i, receipt.getCode (),
                    receipt.getName (),
                    receipt.getReceiptCode (),
                    AppUtils.doubleToVND ( receipt.getMoney () ),
                    receipt.getReadMoney (),
                    receipt.getCountMonth (),
                    receipt.getAddress (),
                    receipt.getStatus (),
                    InstantUtils.instantToStringDayTime ( receipt.getCreatAt () ),
                    receipt.getUpdateAt () == null ? "" : InstantUtils.instantToStringDayTime ( receipt.getUpdateAt () )
            );
            System.out.println ( "╟−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−-−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−╢" );
            i++;
        }
        System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
        System.out.println ( "Nhấn (1) để sắp xếp theo số tiền tăng dần ||  (2) để mở tùy chọn" );
        int choises_11 = AppUtils.retryChoose ( 1, 2 );
        switch (choises_11) {
            case 1:
                showByMoneyADC ();
                break;
            case 2:
                AppUtils.isRetryReceipt ( InputOption.SHOW );
                break;
        }
    }
    public void showAllToAction() {
        List<Receipt> receipts = receiptServices.findAll ();
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                                                               DANH SÁCH BIÊN LAI                                                                                                                  ║" );
        System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );


        System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15s│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                "STT", "Mã học viên", "Tên học viên", "Mã biên lai", "Số tiền", "Số tiền (bằng chữ)", "Đợt", "Địa chỉ", "Trạng thái", "Thời gian tạo", "Thời gian sửa" );
        System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        int i = 1;
        for (Receipt receipt : receipts) {
            System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15d│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    i, receipt.getCode (),
                    receipt.getName (),
                    receipt.getReceiptCode (),
                    AppUtils.doubleToVND ( receipt.getMoney () ),
                    receipt.getReadMoney (),
                    receipt.getCountMonth (),
                    receipt.getAddress (),
                    receipt.getStatus (),
                    InstantUtils.instantToStringDayTime ( receipt.getCreatAt () ),
                    receipt.getUpdateAt () == null ? "" : InstantUtils.instantToStringDayTime ( receipt.getUpdateAt () )
            );
            System.out.println ( "╟−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−-−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−╢" );
            i++;
        }
        System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
    }
}
