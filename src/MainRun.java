import entity.Customer;
import entity.Room;
import service.RoomChoice;

import java.util.Scanner;

public class MainRun {
    private static int countCustomer;
    private static Customer[] customers;
    private static Room[] rooms;
    private static RoomChoice[] roomChoices;
    private static WriteAndRead writeAndRead = new WriteAndRead();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    createNewRoom();
                    break;
                case 2:
                    createNewCustomer();
                    break;
                case 3:
                    roomArrange();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.exit(0);
            }

        } while (true);
    }

    public static boolean isValidCustomerAndRoom() {
        return customers != null && rooms != null && customers.length != 0 && rooms.length != 0;
    }

    private static boolean roomArrange() {
        if (!isValidCustomerAndRoom()) {
            System.out.println("Cần nhập thông tin phòng và khách hàng trước khi sắp xếp phòng: ");
            return false;
        }
        roomChoices = new RoomChoice[countCustomer];
        boolean check = true;
        System.out.println("Nhập id khách hàng muốn sắp xếp: ");
        int customerId;
        Room room;
        Customer customer;
        do {
            try {
                customerId = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            customer = searchCustomer(customerId);
            int retry = 1;
            if (customer != null && customer.getId() == customerId) {
                System.out.println("Loại phòng khách hàng " + customer.getName() + " thuê là: ");
                System.out.println(customer.getRoomTypeRent());
                System.out.println("Số phòng loại " + customer.getRoomTypeRent() + " khách hàng muốn thuê là: ");
                System.out.println(customer.getRoomNumberRent());

                room = searchRoomType(customer.getRoomTypeRent());
                int roomRest = 0;
                assert room != null;
                System.out.println("Số phòng " + room.getRoomType() + " hiện có:");
                System.out.println(room.getRoomNumber());
                if (room.getRoomNumber() >= customer.getRoomNumberRent()) {
                    roomRest = room.getRoomNumber() - customer.getRoomNumberRent();
                    room.setRoomNumber(roomRest);
                } else {
                    return suggestOtherRoom(customer, room, retry);
                }
                break;
            }
            System.out.print("Không có khách hàng nào có ID vừa nhập, vui lòng nhập lại: ");
        } while (true);
        System.out.println(customer);
        System.out.println(room);
        return true;
    }

    public static boolean suggestOtherRoom(Customer customer, Room room, int retry) {
        if (retry == 2) {
            System.out.println("Thue phong that bai");
            return false;
        }
        retry++;
        boolean check = true;
        System.out.println("Không đủ phòng khách hàng muốn thuê, khách hàng có lựa chọn: ");
        System.out.println("1. Giảm số phòng muốn thuê");
        System.out.println("2. Thuê loại phòng khác");
        do {
            int choice = 0;
            try {
                choice = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.print("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (choice <= 0 || choice > 2) {
                System.out.print("Chọn 1 hoặc 2! Nhập lại: ");
                check = false;
                continue;
            }
            switch (choice) {
                case 1:
                    if (room.getRoomNumber() == 0) {
                        System.out.println("Loại phòng này đã hết, chọn loại khác");
                        return reduceRoomType(customer, room, retry);
                    } else {
                        reduceRoomNumber(customer, room);
                        return true;
                    }
                case 2:
                    return reduceRoomType(customer, room, retry);
                default:
                    System.out.println("Nhập sai! Hãy chọn 1 hoặc 2!");
                    check = false;
                    break;
            }
        } while (!check);
        return false;
    }

    public static void reduceRoomNumber(Customer customer, Room room){
        int reselect = 0;
        boolean check = true;
        do {
            try {
                System.out.println("Nhập số lượng phòng chọn lại: ");
                reselect = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.print("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (reselect <= 0 || reselect > room.getRoomNumber()) {
                System.out.println("Số phòng lớn hơn 0 va nhỏ hơn tổng số phòng hiện có! Nhập lại");
                check = false;
            } else {
                int roomRest = room.getRoomNumber() - reselect;
                room.setRoomNumber(roomRest);
                customer.setRoomNumberRent(reselect);
            }
        } while (!check);
    }

    private static boolean reduceRoomType(Customer customer, Room room, int retry){
        System.out.println("Chọn loại phòng: ");
        System.out.println(room.getRoomType().equals(Room.SINGLE_ROOM) ? "" : "1.Phòng đơn");
        System.out.println(room.getRoomType().equals(Room.DOUBLE_ROOM) ? "" : "2.Phòng đôi");
        System.out.println(room.getRoomType().equals(Room.VIP_ROOM) ? "" : "3.Phòng Vip");
        boolean check = true;
        do {
            int choiceRoomType = new Scanner(System.in).nextInt();
            if (choiceRoomType <= 0 || choiceRoomType > 3) {
                System.out.print("Nhập số từ 1 đến 3! Nhập lại: ");
                check = false;
                continue;
            }
            switch (choiceRoomType) {
                case 1:
                    customer.setRoomTypeRent(Room.SINGLE_ROOM);
                    check = true;
                    break;
                case 2:
                    customer.setRoomTypeRent(Room.DOUBLE_ROOM);
                    check = true;
                    break;
                case 3:
                    customer.setRoomTypeRent(Room.VIP_ROOM);
                    check = true;
                    break;
                default:
                    System.out.println("Nhập sai! Hãy nhập từ 1 đến 3!");
                    check = false;
                    break;
            }
        } while (!check);
        System.out.println("Nhập số phòng khách muốn thuê lại: ");
        do {
            int reChoiceRoomNumber;
            try {
                reChoiceRoomNumber = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.print("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (reChoiceRoomNumber <= 0) {
                System.out.println("Số lượng phòng phải lớn hơn 0! Nhập lại:");
                check = false;
            }
        } while (!check);
        room = searchRoomType(customer.getRoomTypeRent());
        int roomRest = 0;
        assert room != null;
        System.out.println("Số phòng " + room.getRoomType() + " hiện có:");
        System.out.println(room.getRoomNumber());
        if (room.getRoomNumber() >= customer.getRoomNumberRent()) {
            roomRest = room.getRoomNumber() - customer.getRoomNumberRent();
            room.setRoomNumber(roomRest);
            return true;
        } else {
            return suggestOtherRoom(customer, room, retry);
        }
    }

    private static Room searchRoomType(String roomType) {
        for (Room room : rooms) {
            if (room.getRoomType().equals(roomType)) {
                return room;
            }
        }
        return null;
    }

    private static Customer searchCustomer(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private static void createNewRoom() {
        System.out.println("Nhập số lượng phòng muốn thêm: ");
        int countRoom = new Scanner(System.in).nextInt();
        rooms = new Room[countRoom];
        for (int i = 0; i < rooms.length; i++) {
            Room room = new Room();
            room.inputRoomInfo();
            rooms[i] = room;
        }
        writeAndRead.writeFileRoom(rooms);
        writeAndRead.readFileRoom();
    }

    private static void createNewCustomer() {
        System.out.println("Nhập số lượng khách hàng muốn thêm: ");
        countCustomer = new Scanner(System.in).nextInt();
        customers = new Customer[countCustomer];
        for (int i = 0; i < customers.length; i++) {
            Customer customer = new Customer();
            customer.inputInfo();
            customers[i] = customer;
        }
        writeAndRead.writeFileCustomer(customers);
        writeAndRead.readFileCustomer();
    }

    private static int functionChoice() {
        System.out.println("--------Quản lý phòng khách sạn--------");
        System.out.println("1.Nhập danh sách phòng");
        System.out.println("2.Nhập danh sách khách sạn");
        System.out.println("3.Sắp xếp phòng cho mỗi khách hàng");
        System.out.println("4.Sắp xếp danh sách xếp phòng");
        System.out.println("5.Tính toán hóa đơn cho mỗi khách hàng");
        System.out.println("6.Thoát");
        int functionChoice;
        do {
            functionChoice = new Scanner(System.in).nextInt();
            if (functionChoice >= 1 && functionChoice <= 6) {
                break;
            }
            System.out.print("Chức năng chọn không hợp lệ, vui lòng chọn lại: ");
        } while (true);
        return functionChoice;
    }
}
