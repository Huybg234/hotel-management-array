package entity;
import java.io.Serializable;
import java.util.Scanner;

public class Customer extends Person implements Serializable {
    private int id;
    private String roomTypeRent;
    private int roomNumberRent;

    private final static String SINGLE_ROOM = "Phòng đơn";
    private final static String DOUBLE_ROOM = "Phòng đôi";
    private final static String VIP_ROOM = "Phòng vip";

    private static int AUTO_ID = 100;

    public Customer() {
    }

    public Customer(int id, String roomTypeRent, int roomNumberRent) {
        this.id = id;
        this.roomTypeRent = roomTypeRent;
        this.roomNumberRent = roomNumberRent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomTypeRent() {
        return roomTypeRent;
    }

    public void setRoomTypeRent(String roomTypeRent) {
        this.roomTypeRent = roomTypeRent;
    }

    public int getRoomNumberRent() {
        return roomNumberRent;
    }

    public void setRoomNumberRent(int roomNumberRent) {
        this.roomNumberRent = roomNumberRent;
    }

    public static String getVipRoom() {
        return VIP_ROOM;
    }

    public static String getSingleRoom() {
        return SINGLE_ROOM;
    }

    public static String getDoubleRoom() {
        return DOUBLE_ROOM;
    }

    public void inputInfo(){
        this.setId(Customer.AUTO_ID);
        super.inputInfo();
        System.out.println("Nhập loại phòng cần thuê: ");
        System.out.println("1.Phòng đơn");
        System.out.println("2.Phòng đôi");
        System.out.println("3.Phòng vip");
        System.out.println("Nhập sự lựa chọn: ");
        boolean check = true;
        int choice;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập chữ! Nhập lại: ");
                check = false;
                continue;
            }
            if (choice <= 0 || choice > 3) {
                System.out.print("Nhập số từ 1 đến 3! Nhập lại: ");
                check = false;
                continue;
            }
            switch (choice) {
                case 1:
                    this.setRoomTypeRent(Customer.SINGLE_ROOM);
                    System.out.println("Phòng đơn");
                    check = true;
                    break;
                case 2:
                    this.setRoomTypeRent(Customer.DOUBLE_ROOM);
                    System.out.println("Phòng đôi");
                    check = true;
                    break;
                case 3:
                    this.setRoomTypeRent(Customer.VIP_ROOM);
                    System.out.println("Phòng Vip");
                    check = true;
                    break;
                default:
                    System.out.println("Nhập sai! Hãy nhập từ 1 đến 3!");
                    check = false;
                    break;
            }
        } while (!check);
        System.out.println("Nhập số lượng phòng muốn thuê: ");
        do {
            try {
                this.roomNumberRent = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (this.roomNumberRent <= 0 ){
                System.out.println("Số phòng muốn thuê phải lớn hơn 0! Nhập lại:");
                check = false;
            }
        }while (!check);
        Customer.AUTO_ID++;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", roomTypeRent='" + roomTypeRent + '\'' +
                ", roomNumberRent=" + roomNumberRent +
                '}';
    }
}
