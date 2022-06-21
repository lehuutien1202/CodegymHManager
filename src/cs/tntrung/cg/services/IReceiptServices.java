package cs.tntrung.cg.services;

import cs.tntrung.cg.model.Receipt;
import cs.tntrung.cg.model.Student;

import java.time.Instant;
import java.util.List;
import java.util.Random;

public interface IReceiptServices {
    List<Receipt> findAll();
    void add(Receipt newReceipt);
    void update(Receipt afterReceipt);
    void deleteByCode(String code);
    boolean existsReceiptCode(Long receiptCode);
    void deleteByReceiptCode(Long receiptCode);
    Receipt getByReceiptCode(Long receiptCode);
    boolean existsCode(String code);
    List<Receipt> receiptByCode(String code);
}
