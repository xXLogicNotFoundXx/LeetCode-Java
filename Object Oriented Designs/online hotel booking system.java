class Hotel{
  int hotelId;
  String hotelName;
  Adress adress;
  List<Room> rooms; // hotel contains the list of rooms
  float rating;
  Facilities facilities;
}
class Facilities{
  bool provides_lift;
  bool provides_hot_water;
  bool provides_breakfast_free;
}

// For the room in any hotel
class Room{
  int roomId;
  int hotelId;
  RoomType roomType;
  RoomStatus roomStatus;
}
public enum RoomStatus {
    EMPTY
    CLEANING
    NOT_EMPTY;
}
public enum RoomType{
    SINGLE,
    DOUBLE;
}

class User{
  int userId;
  String name;
  Date dateOfBirth;
  String mobNo;
  String emailId;
}

// a new booking is created for each booking done by any user
class Booking{
    int bookingId;
    int userId;
    int hotelId; 
    List<Rooms> bookedRooms; 
    int amount;
    PaymentStatus status_of_payment;
    Time bookingTime;
    Duration duration;
}

public enum PaymentStatus {
    PAID,
    UNPAID;
}

class Duration{
  Date from;
  Date to;
}

