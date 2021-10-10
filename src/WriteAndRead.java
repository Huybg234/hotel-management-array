import entity.Customer;
import entity.Room;

import java.io.*;

public class WriteAndRead {
    public void writeFileRoom(Room[] rooms){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("RoomList.txt", true))) {
            outputStream.writeObject(rooms);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readFileRoom(){
        Room[] roomArrayList = new Room[0];
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("RoomList.txt"))) {
            roomArrayList = (Room[]) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        for (Room room : roomArrayList) {
            System.out.println(room);
        }
    }

    public void writeFileCustomer(Customer[] customer){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("CustomerList.txt", true))) {
            outputStream.writeObject(customer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void readFileCustomer(){
        Customer[] customerList = new Customer[0];
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("CustomerList.txt"))) {
            customerList = (Customer[]) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }
}
