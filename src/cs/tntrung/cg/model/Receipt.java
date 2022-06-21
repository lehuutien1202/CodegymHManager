package cs.tntrung.cg.model;

import java.time.Instant;

public class Receipt {
    private String code;
    private String name;
    private Long receiptCode;
    private int money;
    private String readMoney;
    private int countMonth;
    private String address;
    private String status;
    private Instant creatAt;

    private Instant updateAt;
    public Receipt() {

    }

    public Receipt(String code, String name, int money, String readMoney, int countMonth, String address, String status) {
        this.code = code;
        this.name = name;
        this.money = money;
        this.readMoney = readMoney;
        this.countMonth = countMonth;
        this.address = address;
        this.status = status;
    }


    public static Receipt parseStudent ( String row){
        Receipt receipt = new Receipt ();
        String[] fields = row.split ( "," );
        receipt.code = fields[0];
        receipt.name = fields[1];
        receipt.receiptCode = Long.valueOf ( fields[2] );
        receipt.money = Integer.parseInt ( fields[3] );
        receipt.readMoney = fields[4];
        receipt.countMonth = Integer.parseInt ( fields[5] );
        receipt.address = fields[6];
        receipt.status = fields[7];
        receipt.creatAt = Instant.parse ( fields[8] );
        String temp = fields[9];
        if ( temp != null && !temp.equals ( "null" ) )
            receipt.updateAt = Instant.parse ( temp );
        return receipt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getReceiptCode() {
        return receiptCode;
    }

    public void setReceiptCode(Long receiptCode) {
        this.receiptCode = receiptCode;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReadMoney() {
        return readMoney;
    }

    public void setReadMoney(String readMoney) {
        this.readMoney = readMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Instant creatAt) {
        this.creatAt = creatAt;
    }

    public int getCountMonth() {
        return countMonth;
    }

    public void setCountMonth(int countMonth) {
        this.countMonth = countMonth;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return code + "," +
                name + "," +
                receiptCode + "," +
                money + "," +
                readMoney + "," +
                countMonth + "," +
                address + "," +
                status + "," +
                creatAt + "," +
                updateAt;
    }
}
