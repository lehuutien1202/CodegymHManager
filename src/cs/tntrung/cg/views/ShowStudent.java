package cs.tntrung.cg.views;


import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.services.StudentServices;
import cs.tntrung.cg.utils.AppUtils;
import cs.tntrung.cg.utils.InstantUtils;

import java.util.List;
import java.util.Scanner;

public class ShowStudent {
    Scanner input = new Scanner ( System.in );

    public void showStudentList() {
        System.out.println ( "╒═══════════════ DANH SÁCH HỌC VIÊN ═══════════════╕" );
        System.out.println ( "│       ▸ 1. Danh sách toàn bộ học viên            │" );
        System.out.println ( "│       ▹ 2. Danh sách theo lớp                    │" );
        System.out.println ( "│       ▹ 3. Tìm kiếm                              │" );
        System.out.println ( "│ ◌ 4: Đăng xuất |   ◌ 5: Quay lại   |  ◌ 6: Thoát │" );
        System.out.println ( "╘══════════════════════════════════════════════════╛" );

    }

    public void showAllStudent() {
        do {

            System.out.println ( "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
            System.out.println ( "║                                                                                ❣❣❣❣❣   DANH SÁCH HỌC VIÊN    ❣❣❣❣❣                                                                                                        ║" );
            System.out.println ( "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );

            System.out.printf ( "║\t%-5s│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                    "STT", "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
            System.out.println ( "╟─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
            StudentServices studentServices = new StudentServices ();
            List<Student> students = studentServices.findAll ();
            int i = 1;
            for (Student student : students) {
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
            System.out.println ( "Nhấn (1) để xem danh sách sắp xếp theo tên  ||  (2) để mở tùy chọn" );
            System.out.print ( "☞☞☞ " );
            int choises_11 = AppUtils.retryChoose ( 1, 2 );
            switch (choises_11) {
                case 1:
                    StudentServices services = new StudentServices ();
                    services.sortFullName ( students );
                    break;
                case 2:
                    AppUtils.isRetryAdmin ( InputOption.SHOW );
                    break;
            }
        }while (true);
    }

    public void showToAction() {
        System.out.println ( "╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                                ❣❣❣❣❣   DANH SÁCH HỌC VIÊN    ❣❣❣❣❣                                                                                                        ║" );
        System.out.println ( "╠═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );

        System.out.printf ( "║\t%-5s│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                "STT", "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
        System.out.println ( "╟─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        StudentServices studentServices = new StudentServices ();
        List<Student> students = studentServices.findAll ();
        int i = 1;
        for (Student student : students) {
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
    }

    public void showInfoStudent() {
        ShowStudent list = new ShowStudent ();
        StudentServices services = new StudentServices ();
        list.showToAction ();
        System.out.print ( "Nhập mã học viên cần xem thông tin: " );
        String code;
        do {
            code = input.nextLine ();
            if ( !services.existsCode ( code ) ) {
                System.out.print ( "Mã học viên không tồn tại, hãy nhập lại: " );
                continue;
            }
            Student student = services.getByCode ( code );
            System.out.println ( "➧➧➧➧➧➧➧ Thông tin học viên cần chỉnh sửa: " );
            System.out.printf ( "Mã học viên: %s\nLớp học: %s\nHọ tên: %s\nGiới tính: %s\nNgày sinh: %s\nSố điện thoại: %s\nEmail: %s\nTrạng thái: %s\nCCCD | CMND: %s\nNgày cấp: %s\nNơi cấp: %s\nNgười thân: %s\nSĐT người thân: %s\nNgày nhập học: %s\nNgày dự kiến kết thúc: %s\n",
                    student.getCode (), student.getClasses (), student.getName (),
                    student.getGender (), student.getDateOfBirth (), student.getPhoneNumber (),
                    student.getEmail (), student.getStatus (), student.getCitizenNum (),
                    student.getDateRange (), student.getIssuedBy (), student.getRelative (),
                    student.getRelativePhoneNum (), student.getDayAdmission (), student.getEstimatedEndDate () );
            System.out.println ( "Nhấn (1) để xem tiếp  ||  (2) để mở tùy chọn" );
            int choises = AppUtils.retryChoose ( 1, 2 );
            switch (choises) {
                case 1:
                    showInfoStudent ();
                    break;
                case 2:
                    AppUtils.isRetryAdmin ( InputOption.INFO );
                    break;
                default:
                    System.out.print ( "Nhập sai, hãy nhập lại: " );
                    break;
            }
        }
        while (!services.existsCode ( code ));
    }
}
