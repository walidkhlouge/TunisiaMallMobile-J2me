package tunisiamall.entites;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class PhoneNumber {

    public static final int HOME = 0;
    public static final int WORK = 1;
    public static final int MOBILE = 2;

    private int type;
    private String number;

    public void setType(String type) {
        if (type.equals("home")) {
            this.type = HOME;
        } else if (type.equals("work")) {
            this.type = WORK;
        } else if (type.equals("mobile")) {
            this.type = MOBILE;
        } else {
            throw new IllegalArgumentException("unknown PhoneNumber type: " + type);
        }
    }

    public int getType() {
        return type;
    }

    public void setNumber(String number) {
        if (number.startsWith("+")) {
            this.number = number;
        } else {
            throw new IllegalArgumentException("PhoneNumber not in international format");
        }
    }

    public String getNumber() {
        return number;
    }
}
