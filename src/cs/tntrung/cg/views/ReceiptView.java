package cs.tntrung.cg.views;

import cs.tntrung.cg.model.Receipt;
import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.services.ReceiptServices;
import cs.tntrung.cg.services.StudentServices;
import cs.tntrung.cg.utils.AppUtils;
import cs.tntrung.cg.utils.InstantUtils;
import cs.tntrung.cg.utils.ReadNumber;
import cs.tntrung.cg.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReceiptView {
    ReceiptServices receiptServices = new ReceiptServices ();
    StudentServices services = new StudentServices ();
    Scanner scanner = new Scanner ( System.in );
    ShowReceipt showReceipt = new ShowReceipt ();

    public ReceiptView() {
    }

    public void addReceipt() {
        do {
            System.out.println ( "\t┌────────────────────────────────┐" );
            System.out.println ( "\t┼ ++++++ Tạo biên lai mới ++++++ ┼" );
            System.out.println ( "\t└────────────────────────────────┘" );
            String code = inputCode ();
            Student student = services.getByCode ( code );
            String name = student.getName ();
            System.out.printf ( "Học viên %s, lớp %s\n", student.getName (), student.getClasses () );
            int money = inputMoney ();
            String readMoney = inputReadMoney ( money );
            System.out.println ( readMoney );
            int countMonth = inputCountMonth ();
            String address = "HUE" + " - " + student.getClasses ();
            String status = "Chưa in";
            Receipt receipt = new Receipt ( code, name, money, readMoney, countMonth, address, status );
            receiptServices.add ( receipt );
            System.out.println ( "Thêm biên lai thành công!" );
            AppUtils.isRetryReceipt ( InputOption.ADD );
        } while (true);
    }

    public void showReceiptList() {
        do {
            showReceipt.showAll ();
            AppUtils.isRetryReceipt ( InputOption.SHOW );
        } while (true);
    }

    public void removeReceiptByCode() {
        showReceipt.showAll ();
        String code;
        do {
            code = inputCodeToAc ();
            List<Receipt> receipts = receiptServices.receiptByCode ( code );
            System.out.println ();
            System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
            System.out.println ( "║                                                                                           ❣❣❣❣❣   DANH SÁCH BIÊN LAI    ❣❣❣❣❣                                                                                                                       ║" );
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
            System.out.println ();
            System.out.println ( "╒═════BẠN CÓ CHẮC MUỐN XÓA═════╕" );
            System.out.println ( "│                              │" );
            System.out.println ( "│        ▫ 1. Xác nhận         │" );
            System.out.println ( "│        ▪ 2. Hủy bỏ           │" );
            System.out.println ( "│                              │" );
            System.out.println ( "╘═════════◦◦◦◦◦◦◦◦◦◦◦◦═════════╛" );
            int option = AppUtils.retryChoose ( 1, 2 );
            if ( option == 1 ) {
                receiptServices.deleteByCode ( code );
                System.out.println ( "!!!!! Đã xóa thành công !!!!!" );
                AppUtils.isRetryReceipt ( InputOption.DELETE1 );
            } else if ( option == 2 ) {
                AppUtils.isRetryReceipt ( InputOption.DELETE1 );
            }
        } while (true);
    }

    public void removeReceiptByReceiptCode() {
        showReceipt.showAll ();
        Long receiptCode;
        do {
            receiptCode = inputReceiptCode ();
            Receipt receipt = receiptServices.getByReceiptCode ( receiptCode );
            System.out.println ();
            System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
            System.out.println ( "║                                                                                           ❣❣❣❣❣   DANH SÁCH BIÊN LAI    ❣❣❣❣❣                                                                                                                       ║" );
            System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );


            System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15s│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    "STT", "Mã học viên", "Tên học viên", "Mã biên lai", "Số tiền", "Số tiền (bằng chữ)", "Đợt", "Địa chỉ", "Trạng thái", "Thời gian tạo", "Thời gian sửa" );
            System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
            System.out.printf ( "║\t%-5d│%-15s│%-25s│%-15d│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    1, receipt.getCode (),
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
            System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
            System.out.println ();
            System.out.println ( "╒═════BẠN CÓ CHẮC MUỐN XÓA═════╕" );
            System.out.println ( "│                              │" );
            System.out.println ( "│        ▫ 1. Xác nhận         │" );
            System.out.println ( "│        ▪ 2. Hủy bỏ           │" );
            System.out.println ( "│                              │" );
            System.out.println ( "╘═════════◦◦◦◦◦◦◦◦◦◦◦◦═════════╛" );
            int option = AppUtils.retryChoose ( 1, 2 );
            if ( option == 1 ) {
                receiptServices.deleteByReceiptCode ( receiptCode );
                System.out.println ( "!!!!! Đã xóa thành công !!!!!" );
                AppUtils.isRetryReceipt ( InputOption.DELETE );
            } else if ( option == 2 ) {
                AppUtils.isRetryReceipt ( InputOption.DELETE );
            }
        } while (true);
    }

    public void updateReceipt() {
        LaunchReiptView launchReiptView = new LaunchReiptView ();
        do {
            showReceipt.showAll ();
            System.out.println ( "✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍" );
            System.out.println ( "✏ ✏ ✏ ✏    CHỈNH SỬA BIÊN LAI    ✏ ✏ ✏ ✏" );
            Long receiptCode;
            receiptCode = inputReceiptCode ();
            Receipt receipt = receiptServices.getByReceiptCode ( receiptCode );
            System.out.println ();
            System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
            System.out.println ( "║                                                                                           ❣❣❣❣❣   THÔNG TIN BIÊN LAI    ❣❣❣❣❣                                                                                                                       ║" );
            System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );


            System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15s│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    "STT", "Mã học viên", "Tên học viên", "Mã biên lai", "Số tiền", "Số tiền (bằng chữ)", "Đợt", "Địa chỉ", "Trạng thái", "Thời gian tạo", "Thời gian sửa" );
            System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
            System.out.printf ( "║\t%-5d│%-15s│%-25s│%-15d│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    1, receipt.getCode (),
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
            System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
            System.out.println ();
            launchReiptView.runUpdate ();
            int choise = AppUtils.retryChoose ( 1, 5 );
            Receipt afterReceipt = new Receipt ();
            afterReceipt.setReceiptCode ( receiptCode );
            Receipt receipt1 = receiptServices.getByReceiptCode ( receiptCode );
            switch (choise) {
                case 1:
                    System.out.println ( "Số tiền hiện tại: " + AppUtils.doubleToVND ( receipt1.getMoney () ) );
                    int newMoney = inputMoney ();
                    afterReceipt.setMoney ( newMoney );
                    receiptServices.update ( afterReceipt );
                    System.out.println ( "✔ THAY ĐỔI SỐ TIỀN THÀNH CÔNG ✔" );
                    AppUtils.isRetryReceipt ( InputOption.UPDATE );
                    break;
                case 2:
                    System.out.println ( "Đợt nộp tiền hiện tại: " + receipt1.getCountMonth () );
                    int newCount = inputCountMonth ();
                    afterReceipt.setCountMonth ( newCount );
                    receiptServices.update ( afterReceipt );
                    System.out.println ( "✔ THAY ĐỔI ĐỢT NỘP TIỀN THÀNH CÔNG ✔" );
                    AppUtils.isRetryReceipt ( InputOption.UPDATE );
                    break;
                case 3:
                    System.out.println ( "Số tiền hiện tại: " + receipt1.getMoney () );
                    int newMoney1 = inputMoney ();
                    afterReceipt.setMoney ( newMoney1 );
                    System.out.println ( "Đợt nộp tiền hiện tại: " + receipt1.getCountMonth () );
                    int newCount1 = inputCountMonth ();
                    afterReceipt.setCountMonth ( newCount1 );
                    receiptServices.update ( afterReceipt );
                    System.out.println ( "✔ THAY ĐỔI THÀNH CÔNG ✔" );
                    AppUtils.isRetryReceipt ( InputOption.UPDATE );
                    break;
                case 4:
                    launchReiptView.launch ();
                    break;
                case 5:
                    AppUtils.login ();
                    break;
                case 6:
                    AppUtils.exit ();
                    break;
            }
        } while (true);
    }
    public void printReceipt() {
        do {
            showReceipt.showAll ();
            System.out.println ( "✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍✍" );
            System.out.println ( "✏ ✏ ✏ ✏    IN BIÊN LAI    ✏ ✏ ✏ ✏" );
            Long receiptCode;
            receiptCode = inputReceiptCode ();
            Receipt receipt = receiptServices.getByReceiptCode ( receiptCode );
            System.out.println ();
            System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
            System.out.println ( "║                                                                                                              THÔNG TIN BIÊN LAI                                                                                                                   ║" );
            System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );
            System.out.printf ( "║\t%-5s│%-15s│%-25s│%-15s│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    "STT", "Mã học viên", "Tên học viên", "Mã biên lai", "Số tiền", "Số tiền (bằng chữ)", "Đợt", "Địa chỉ", "Trạng thái", "Thời gian tạo", "Thời gian sửa" );
            System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
            System.out.printf ( "║\t%-5d│%-15s│%-25s│%-15d│%-15s│%-70s│%-5s│%-20s│%-20s│%-20s│%-20s║\n",
                    1, receipt.getCode (),
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
            System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
            System.out.println ();
            System.out.println ( "╒═══BẠN MUỐN IN BIÊN LAI NÀY═══╕" );
            System.out.println ( "│                              │" );
            System.out.println ( "│        ▫ 1. Xác nhận         │" );
            System.out.println ( "│        ▪ 2. Hủy bỏ           │" );
            System.out.println ( "│                              │" );
            System.out.println ( "╘═════════◦◦◦◦◦◦◦◦◦◦◦◦═════════╛" );
            int option = AppUtils.retryChoose ( 1, 2 );
            if ( option == 1 ) {
                System.out.println ();
                System.out.printf ( "%31s\n","Tầng 4, 28 Nguyễn Tri Phương");
                System.out.printf ( "%32s\n","Phường Phú Hội, thành phố Huế" );
                System.out.printf ( "%20s %95s %d\n","CODEGYM HUẾ", "Mã biên lai:", receipt.getReceiptCode ());
                System.out.println ();
                System.out.printf ( "%70s\n", "PHIẾU THU");
                System.out.println ( );
                System.out.printf ( "%40s %s\n","Mã học viên:",receipt.getCode () );
                System.out.printf ( "%40s %s\n","Tên học viên:", receipt.getName () );
                System.out.printf ( "%40s %s\n","Số tiền:", AppUtils.doubleToVND ( receipt.getMoney () ) );
                System.out.printf ( "%40s %s%s\n","Bằng chữ:", receipt.getReadMoney (),"(đồng)" );
                System.out.printf ( "%40s %s\n","Đợt:", receipt.getCountMonth () );
                System.out.printf ( "%40s %s\n","Địa chỉ:", receipt.getAddress () );
                System.out.println ();
                System.out.println ();
                System.out.println ();

                receipt.setStatus ( "Đã in" );
                receiptServices.update ( receipt );
                System.out.println ( "!!!!! ĐÃ IN BIÊN LAI THÀNH CÔNG !!!!!" );
                System.out.println ();
                AppUtils.isRetryReceipt ( InputOption.PRINT );
            } else if ( option == 2 ) {
                AppUtils.isRetryReceipt ( InputOption.PRINT );
            }
        }while (true);
    }

    public String inputCode() {

        System.out.print ( "❒ Nhập mã học viên: " );
        String code;
        do {
            if ( !ValidateUtils.codeValid ( code = AppUtils.retryString ( "Mã học viên" ) ) ) {
                System.out.print ( code + " không đúng, hãy nhập lại: " );
                continue;
            }
            if ( !services.existsCode ( code ) ) {
                System.out.print ( "Mã học viên không tồn tại, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return code;
    }

    public String inputCodeToAc() {
        System.out.print ( "❒ Nhập mã học viên: " );
        String code;
        do {
            if ( !ValidateUtils.codeValid ( code = AppUtils.retryString ( "Mã học viên" ) ) ) {
                System.out.print ( code + " không đúng, hãy nhập lại: " );
                continue;
            }
            if ( !receiptServices.existsCode ( code ) ) {
                System.out.print ( "Mã học viên không tồn tại, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return code;
    }

    public Long inputReceiptCode() {
        System.out.print ( "❒ Nhập mã biên lai " );
        Long receiptCode;
        do {
            receiptCode = AppUtils.retryParseLong ();
            if ( !receiptServices.existsReceiptCode ( receiptCode ) ) {
                System.out.print ( "Mã biên lai không tồn tại, hãy nhập lại: " );
                continue;
            }
            break;
        } while (true);
        return receiptCode;
    }

    private int inputMoney() {
        int price;
        System.out.print ( "❒ Nhập số tiền: " );
        do {
            price = AppUtils.retryParseInt ();
            if ( price <= 1000 )
                System.out.print ( "Số tiền phải lớn hơn 1,000 VNĐ: " );
            if ( price > 100000000 )
                System.out.print ( "Số tiền không vượt quá 100,000,000 VND: " );
        } while (price <= 1000 || price > 100000000);
        return price;
    }

    public String inputReadMoney(int money) {
        String readMoney = "";
        String moneyStr = Integer.toString ( money );
        ArrayList<String> kq = ReadNumber.readNum ( moneyStr );
        for (String s : kq) {
            readMoney += s + " ";
        }
        return readMoney;
    }

    private int inputCountMonth() {
        int countMonth;
        System.out.print ( "❒ Nhập đợt: " );
        do {
            countMonth = AppUtils.retryParseInt ();
            if ( countMonth <= 0 )
                System.out.print ( "Số đợt phải lớn hơn 0, hãy nhập lại: " );
            if ( countMonth > 6 )
                System.out.print ( "Không vượt quá đợt 6: " );
        } while (countMonth <= 0 || countMonth > 6);
        return countMonth;
    }
}
