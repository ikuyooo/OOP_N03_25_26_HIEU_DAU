import java.util.ArrayList;
import java.util.List;

public class testRoom {
    private List<Room> rooms = new ArrayList<>();

    // Create
    public void addRoom(Room room) {
        rooms.add(room);
    }

    // Read
    public List<Room> getAllRooms() {
        return rooms;
    }

    // Update
    public boolean updateRoom(String roomId, String newType, double newPrice) {
        for (Room r : rooms) {
            if (r.getRoomId().equals(roomId)) {
                r.setType(newType);
                r.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }

    // Delete
    public boolean deleteRoom(String roomId) {
        return rooms.removeIf(r -> r.getRoomId().equals(roomId));
    }
}
