package cs.tntrung.cg.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String CODE = "^(CGH[0-9]{6})$";
    public static final String CLASS = "^(C(0([1-9])|1([1-2]))(22)([GHIK]1))$";
    public static final String FULLNAME = "^([A-Z]+[a-z]+[ ]?)+$";
    public static final String DATE = "^(?:31(-)(?:0?[13578]|1[02])\\1|(?:29|30)(-.)(?:0?[13-9]|1[0-2])\\2)(?:1[6-9]|[2-9]\\d)?\\d{2}$|^29(-)0?2\\3(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$|^(?:0?[1-9]|1\\d|2[0-8])(-)(?:0?[1-9]|1[0-2])\\4(?:1[6-9]|[2-9]\\d)?\\d{2}$";
    public static final String PHONENUM = "^[0][1-9][0-9]{8}$";
    public static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String PASSWORD = "^(([A-Z])[a-zA-Z0-9.*@_-]{8,15})$";

    public static final String WORDANDNUM = "^(([A-Z]|[a-z]|[0-9])+[,]?[ ]?)+$";

    public static final String CITIZEN_NUM = "^((0[1-9][0-9]{10})|(0[1-9][0-9]{7})|(1[0-9]{8}))$";
    public static final String YEAR = "^(19[6-9][0-9])|(200[0-3])$";

//    public static final String WORD = "^([A-Za-z]+[ ]?)+$";
    public String newCode() {
        long numCode = System.currentTimeMillis () % 100000;
        return String.format ( "CGH%06d", numCode );
    }
    public static boolean codeValid(String code) {
        return Pattern.compile ( CODE ).matcher ( code ).matches ();
    }
    public static boolean fullNameValid(String name) {
        return Pattern.compile ( FULLNAME ).matcher ( name ).matches ();
    }

    public static boolean classValid(String classes) {
        return Pattern.compile ( CLASS ).matcher ( classes ).matches ();
    }
    public static boolean dateValid(String date) {
        return Pattern.compile ( DATE ).matcher ( date ).matches ();
    }

    public static boolean phoneNumValid(String phoneNumber) {
        return Pattern.compile ( PHONENUM ).matcher ( phoneNumber ).matches ();
    }

    public static boolean emailValid(String email) {
        return Pattern.compile ( EMAIL ).matcher ( email ).matches ();
    }

    public static boolean passwordValid(String password) {
        return Pattern.compile ( PASSWORD ).matcher ( password ).matches ();
    }
    public static boolean citizenNumValid(String citizenNum) {
        return Pattern.compile ( CITIZEN_NUM ).matcher ( citizenNum ).matches ();
    }
    public static boolean issuedByValid(String issuedBy) {
        return Pattern.compile ( WORDANDNUM ).matcher ( issuedBy ).matches ();
    }
    public static boolean addressValid(String address) {
        return Pattern.compile ( WORDANDNUM ).matcher ( address ).matches ();
    }
    public static boolean yearValid(String year) {
        return Pattern.compile ( YEAR ).matcher ( year ).matches ();
    }
//    public static boolean wordValid(String word) {
//        return Pattern.compile ( WORD ).matcher ( word ).matches ();
//    }
}
