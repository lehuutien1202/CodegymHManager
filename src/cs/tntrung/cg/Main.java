package cs.tntrung.cg;


import cs.tntrung.cg.model.Receipt;
import cs.tntrung.cg.services.ReceiptServices;

import cs.tntrung.cg.views.*;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        ReceiptServices receiptServices = new ReceiptServices ();
        Scanner scanner = new Scanner ( System.in );
        Login.login ();
    }
}

