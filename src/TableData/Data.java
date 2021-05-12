/*
Data class needed for setting the table's data
 */

package TableData;

public class Data{
    private Integer monthNumber;
    private String sum;
    private String payment;
    private String percent;
    private String leftToPay;

    public Data(Integer monthNumber, String sum, String payment, String percent, String leftToPay){
        super();
        this.monthNumber = monthNumber;
        this.sum = sum;
        this.payment = payment;
        this.percent = percent;
        this.leftToPay = leftToPay;
    }

    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getLeftToPay() {
        return leftToPay;
    }

    public void setLeftToPay(String leftToPay) {
        this.leftToPay = leftToPay;
    }
}