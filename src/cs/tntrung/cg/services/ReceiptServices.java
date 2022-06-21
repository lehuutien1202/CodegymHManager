package cs.tntrung.cg.services;

import cs.tntrung.cg.model.Receipt;
import cs.tntrung.cg.model.Student;
import cs.tntrung.cg.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ReceiptServices implements IReceiptServices {
    public final static String PATH_RECEIPT = "data\\receipt.csv";
    private static ReceiptServices instance;

    public ReceiptServices() {
    }

    public static ReceiptServices getInstance() {
        if ( instance == null ) instance = new ReceiptServices ();
        return instance;
    }
    @Override
    public List<Receipt> findAll() {
        List<String> records = CSVUtils.read ( PATH_RECEIPT );
        List<Receipt> students = new ArrayList<> ();
        for (String record : records) {
            students.add ( Receipt.parseStudent ( record ) );
        }
        return students;
    }
// Tạo phiếu thu mới
    @Override
    public void add(Receipt newReceipt) {
        Long receiptCode = System.currentTimeMillis() / 1000;
        newReceipt.setReceiptCode ( receiptCode );
        newReceipt.setCreatAt ( Instant.now () );
        List<Receipt> receipts = findAll ();
        receipts.add ( newReceipt );
        CSVUtils.write ( PATH_RECEIPT, receipts );
    }

    @Override
    public void update(Receipt afterReceipt) {
        List<Receipt> receipts = findAll ();
        for (Receipt receipt : receipts) {
            if ( receipt.getReceiptCode ().equals ( afterReceipt.getReceiptCode () ) ) {
                int money = afterReceipt.getMoney ();
                if ( money != 0 ) {
                    receipt.setMoney ( money );
                }
                int countMonth = afterReceipt.getCountMonth ();
                if ( countMonth != 0 ) {
                    receipt.setCountMonth ( countMonth );
                }
                String status = afterReceipt.getStatus ();
                if ( status != null && !status.isEmpty ()  )
                    receipt.setStatus ( status );
                receipt.setUpdateAt ( Instant.now () );
                CSVUtils.write ( PATH_RECEIPT, receipts );
                break;
            }
        }
    }

    @Override
    public void deleteByCode(String code) {
        List<Receipt> receipts = findAll ();
        receipts.removeIf ( new Predicate<Receipt> () {
            @Override
            public boolean test(Receipt receipt) {
                return receipt.getCode ().equals ( code );
            }
        } );
        CSVUtils.write ( PATH_RECEIPT, receipts );
    }

    @Override
    public void deleteByReceiptCode(Long receiptCode) {
        List<Receipt> receipts = findAll ();
        receipts.removeIf ( new Predicate<Receipt> () {
            @Override
            public boolean test(Receipt receipt) {
                return Objects.equals ( receipt.getReceiptCode (), receiptCode );
            }
        } );
        CSVUtils.write ( PATH_RECEIPT, receipts );
    }

    @Override
    public Receipt getByReceiptCode(Long receiptCode) {
        for (Receipt receipt : findAll ()) {
            if ( receipt.getReceiptCode ().equals ( receiptCode ) )
                return receipt;
        }
        return null;
    }

    @Override
    public List<Receipt> receiptByCode(String code) {
        List<Receipt> receipts = findAll ();
        List<Receipt> receiptByCode = new ArrayList<> ();
        for (Receipt receipt : receipts) {
            if ( receipt.getCode ().equals ( code ) ){
                receiptByCode.add ( receipt );
            }
        }
        return receiptByCode;
    }

    @Override
    public boolean existsReceiptCode(Long receiptCode) {
        List<Receipt> receipts = findAll ();
        for (Receipt receipt : receipts) {
            if ( receipt.getReceiptCode ().equals ( receiptCode ) ) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsCode(String code) {
        List<Receipt> receipts = findAll ();
        for (Receipt receipt : receipts) {
            if ( receipt.getCode ().equals ( code ) ) {
                return true;
            }
        }
        return false;
    }
}
