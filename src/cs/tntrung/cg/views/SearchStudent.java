package cs.tntrung.cg.views;

import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.services.StudentServices;
import cs.tntrung.cg.utils.AppUtils;
import cs.tntrung.cg.utils.InstantUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchStudent {
    Scanner scanner = new Scanner ( System.in );
    AdminView adminView = new AdminView ();
    StudentServices studentServices = new StudentServices ();

    public void searchStudent() {
        do {
            System.out.println ( "╒══════════════════ TÌM HỌC VIÊN ══════════════════╕" );
            System.out.println ( "│                                                  │" );
            System.out.println ( "│            ▸ 1. Theo mã học viên                 │" );
            System.out.println ( "│            ▹ 2. Theo tên                         │" );
            System.out.println ( "│            ▹ 3. Theo số điện thoại               │" );
            System.out.println ( "│                                                  │" );
            System.out.println ( "│ ◌ 4: Đăng xuất |   ◌ 5: Quay lại   |    6: Thoát │" );
            System.out.println ( "╘══════════════════════════════════════════════════╛" );
            SearchStudent student = new SearchStudent ();
            int choise_2 = AppUtils.retryChoose ( 1, 6 );
            switch (choise_2) {
                case 1:
                    student.searchByCode ();
                    AppUtils.isRetryAdmin ( InputOption.SEARCH );
                    break;
                case 2:
                    student.searchByName ();
                    AppUtils.isRetryAdmin ( InputOption.SEARCH );
                    break;
                case 3:
                    student.searchByPhone ();
                    AppUtils.isRetryAdmin ( InputOption.SEARCH );
                    break;
                case 4:
                    AppUtils.login ();
                    break;
                case 5:
                    adminView.showStudentList ();
                    break;
                case 6:
                    AppUtils.exit ();
                    break;
            }
        } while (true);
    }

    public void searchByCode() {
        System.out.println ( "❐ Tìm học viên theo mã học viên ❐" );
        System.out.println ( "✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩" );
        System.out.print ( "Nhập mã học viên cần tìm: " );
        String code = adminView.inputCode ( InputOption.SEARCH );
        Student student = studentServices.getByCode ( code );
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                            ❣❣❣❣❣   HỌC VIÊN    ❣❣❣❣❣                                                                                                                ║" );
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
//        Scanner scanner = new Scanner ( System.in );
        StudentServices services = new StudentServices ();
        List<Student> students = services.findAll ();
        List<Student> findStudenList = new ArrayList<> ();
        String nameSearch = inputSearchName ();
        for (Student student : students) {
            boolean check = student.getName ().toLowerCase ().contains ( nameSearch.toLowerCase () );
            if ( check ) {
                findStudenList.add ( student );
            }
        }
        int i = 1;
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                            ❣❣❣❣❣   DANH SÁCH HỌC VIÊN THEO TÊN    ❣❣❣❣❣                                                                                             ║" );
        System.out.println ( "╠═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣" );
        System.out.printf ( "║\t%-5s│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                "STT", "Mã Học Viên", "Lớp", "Họ và tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email", "Ngày tạo", "Ngày chỉnh sửa" );
        System.out.println ( "╟───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╢" );
        for (Student studentFind : findStudenList) {
            System.out.printf ( "║\t%-5d│%-20s│%-20s│%-30s│%-20s│%-20s│%-20s│%-30s│%-20s│%-20s║\n",
                    i, studentFind.getCode (),
                    studentFind.getClasses (),
                    studentFind.getName (),
                    studentFind.getGender (),
                    studentFind.getDateOfBirth (),
                    studentFind.getPhoneNumber (),
                    studentFind.getEmail (),
                    InstantUtils.instantToStringDayTime ( studentFind.getAddedAt () ),
                    studentFind.getUpdatedAt () == null ? "" : InstantUtils.instantToStringDayTime ( studentFind.getUpdatedAt () )
            );
            System.out.println ( "╟−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−│−−−−−−−−−−−−−−−−−−−−╢" );
            i++;
        }
        System.out.println ( "╚═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝" );
    }

    public void searchByPhone() {
        System.out.println ( "❐ Tìm học viên theo số điện thoại ❐" );
        System.out.println ( "✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩✩" );
        System.out.println ();
        System.out.print ( "Nhập số điện thoại cần tìm: " );
        String phoneNum = adminView.inputPhoneNum ( InputOption.SEARCH );
        Student student = studentServices.getByPhone ( phoneNum );
        System.out.println ( "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗" );
        System.out.println ( "║                                                                                         ❣❣❣❣❣   HỌC VIÊN    ❣❣❣❣❣                                                                                                   ║" );
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
