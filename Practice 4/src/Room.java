public class Room {
    private String roomId;
    private String type;
    private double price;

    public Room() {}

    public Room(String roomId, String type, double price) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
    }

    // Getter & Setter
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
