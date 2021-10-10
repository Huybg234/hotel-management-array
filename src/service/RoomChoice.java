package service;

import entity.Customer;
import entity.Room;
import java.util.Arrays;

public class RoomChoice {
    private Customer customer;
    private Room[] rooms;
    private int roomQuantity;
    private int dayNumber;

    public RoomChoice() {
    }

    public RoomChoice(Customer customer, Room[] rooms, int roomQuantity, int dayNumber) {
        this.customer = customer;
        this.rooms = rooms;
        this.roomQuantity = roomQuantity;
        this.dayNumber = dayNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    @Override
    public String toString() {
        return "ChooseRoom{" +
                "customer=" + customer +
                ", rooms=" + Arrays.toString(rooms) +
                ", roomQuantity=" + roomQuantity +
                ", dayNumber=" + dayNumber +
                '}';
    }
}
