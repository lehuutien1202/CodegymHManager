package cs.tntrung.cg;

import cs.tntrung.cg.model.Receipt;
import cs.tntrung.cg.services.ReceiptServices;
import cs.tntrung.cg.utils.InstantUtils;
import cs.tntrung.cg.views.LaunchStudentView;
import cs.tntrung.cg.views.Login;
import cs.tntrung.cg.views.ReceiptView;
import cs.tntrung.cg.views.StudentView;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        ReceiptServices receiptServices = new ReceiptServices ();
        Scanner scanner = new Scanner ( System.in );
        Login.login ();
//        ReceiptView receiptView = new ReceiptView ();
//        receiptView.printReceipt ();

    }
}

