package cs.tntrung.cg.services;

import cs.tntrung.cg.model.Student;

import java.util.List;

public interface IStudentService {
    List<Student> findAll();

    Student studentLogin(String username, String password);

    boolean existsPhoneNumber(String phoneNumber);

    Student getByCode(String code);
    Student getByEmail(String email);
    Student getByPhone(String code);
    void deleteByCode(String code);
    void studentByClass();
    boolean existsCode(String code);
    boolean existsEmail(String email);
    boolean existsCitizenNum(String email);
    boolean existsClass(String classes);
    void add(Student newStudent);
    void edit(Student beforeStudent);
    List<Student> sortFullNameADC(List<Student> studentList);
    List<Student> sortFullNameDEC(List<Student> studentList);
    boolean checkNameStudent(String name);
}
