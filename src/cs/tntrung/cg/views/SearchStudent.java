package cs.tntrung.cg.views;

import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.services.StudentServices;
import cs.tntrung.cg.utils.InstantUtils;

import java.util.List;
import java.util.Scanner;

public class SearchStudent {
    Scanner scanner = new Scanner ( System.in );
    AdminView adminView = new AdminView ();
    StudentServices studentServices = new StudentServices ();
    public void searchStudent(){
        System.out.println ( "╒══════════════════ TÌM HỌC VIÊN ══════════════════╕" );
        System.out.println ( "│       ▸ 1. Theo mã học viên                      │" );
        System.out.println ( "│       ▹ 2. Theo tên                              │" );
        System.out.println ( "│       ▹ 3. Theo số điện thoại                    │" );
        System.out.println ( "│ ◌ 4: Đăng xuất |   ◌ 5: Quay lại   |    6: Thoát │" );
        System.out.println ( "╘══════════════════════════════════════════════════╛" );
    }

    public void searchByCode() {
        System.out.println ( "❐ Tìm học viên theo mã học viên ❐" );
        System.out.println ( "✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩" );
        System.out.print ( "Nhập mã học viên cần tìm: " );
        String code = adminView.inputCode ( InputOption.SEARCH );
        Student student = studentServices.getByCode ( code );
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                            ❣❣❣❣❣   DANH SÁCH HỌC VIÊN    ❣❣❣❣❣                                                                                                      ║" );
        System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );

        System.out.printf ( "║\t%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
        System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        System.out.printf ( "║\t%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                student.getCode (),
                student.getClasses (),
                student.getName (),
                student.getGender (),
                student.getDateOfBirth (),
                student.getPhoneNumber (),
                student.getEmail (),
                InstantUtils.instantToStringDayTime ( student.getAddedAt () ),
                student.getUpdatedAt () == null ? "" : InstantUtils.instantToStringDayTime ( student.getUpdatedAt () )
        );
        System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
    }

    public void searchByName() {
        Scanner scanner = new Scanner ( System.in );
        StudentServices services = new StudentServices ();
        List<Student> students = services.findAll ();
//        System.out.print ( "Nhâp tên cần tìm: " );
        String nameSearch = inputSearchName ();
        int i = 1;

        for (Student student : students) {
            boolean check = student.getName ().toLowerCase ().contains ( nameSearch.toLowerCase () );
            if ( check ) {
                System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
                System.out.println ( "║                                                                            ❣❣❣❣❣   DANH SÁCH HỌC VIÊN    ❣❣❣❣❣                                                                                                      ║" );
                System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );
                System.out.printf ( "║\t%-5s│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                        "STT", "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
                System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
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
                System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
                i++;
            }
        }

    }
    public void searchByPhone() {
        System.out.println ( "❐ Tìm học viên theo mã học viên ❐" );
        System.out.println ( "✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩" );
        System.out.print ( "Nhập mã học viên cần tìm: " );
        String code = adminView.inputPhoneNum ( InputOption.SEARCH );
        Student student = studentServices.getByPhone ( code );
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                            ❣❣❣❣❣   DANH SÁCH HỌC VIÊN    ❣❣❣❣❣                                                                                                      ║" );
        System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );

        System.out.printf ( "║\t%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
        System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        System.out.printf ( "║\t%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                student.getCode (),
                student.getClasses (),
                student.getName (),
                student.getGender (),
                student.getDateOfBirth (),
                student.getPhoneNumber (),
                student.getEmail (),
                InstantUtils.instantToStringDayTime ( student.getAddedAt () ),
                student.getUpdatedAt () == null ? "" : InstantUtils.instantToStringDayTime ( student.getUpdatedAt () )
        );
        System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
    }

    public String inputSearchName() {
        StudentServices services = new StudentServices ();
        System.out.print ( "Nhâp tên cần tìm: " );
        String searchName = scanner.nextLine ();
        boolean check = services.checkNameStudent ( searchName );
        do {
            if ( !check ) {
                System.out.println ( "Không tìm thấy!" );
            }
            break;
        } while (true);
        return searchName;
    }
}
