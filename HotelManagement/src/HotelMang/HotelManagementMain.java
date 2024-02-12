package HotelMang;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class HotelManagement{
    private int hotelId;
    
    private String hotelName;
    private String hotelLocation;
    private int noOfRooms;
    private float costPerRoom;

  static HashMap<Integer,HotelManagement> hotelData=new HashMap<>();
    static int CountId=1;
    public int getHotelId() {
        return hotelId;
    }
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
    public String getHotelName() {
        return hotelName;
    }
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getHotelLocation() {
        return hotelLocation;
    }
    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }
    public int getNoOfRooms() {
        return noOfRooms;
    }
    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }
    public float getCostPerRoom() {
        return costPerRoom;
    }
    public void setCostPerRoom(float costPerRoom) {
        this.costPerRoom = costPerRoom;
    }
}


public class HotelManagementMain {
    private static void printMenu(){
        System.out.println("\t\t\t Welcome to Hotel Management Project");
        System.out.println("1. To create hotel Id");
        System.out.println("2. To Remove hotel Id");
        System.out.println("3. To update hotel Id");
        System.out.println("4. To display hotel Id");
        System.out.println("5. To search hotel Id");
        System.out.println("6. To exit hotel Id");
        System.out.println("Enter the number");
    }
    public static void toCreateHotelId(){
        Scanner s=new Scanner(System.in);
        HotelManagement obj=new HotelManagement();

        System.out.println("Enter the Hotel Name");
        obj.setHotelName(s.next());
        System.out.println("Enter the Hotel Location");
        obj.setHotelLocation(s.next());
        System.out.println("Enter the number of rooms");
        obj.setNoOfRooms(s.nextInt());
        System.out.println("Enter the cost per room");
        obj.setCostPerRoom(s.nextFloat());
        DbOperations.toAddHotelData(obj);

//        toDisplayHotelId();
    }
    public static void toRemoveHotelId(){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the id to be removed");
        int id=s.nextInt();

        DbOperations.toRemoveAHoteldata(id);
        toDisplayHotelId();
    }
    public static void toUpdateHotelId(){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the id to be removed");
        int id=s.nextInt();
        if(DbOperations.hotelExits(id)){
            System.out.println("1. To Update Hotel Name");
            System.out.println("2. To Update Hotel Location");
            System.out.println("3. To Update Hotel Number of Rooms");
            System.out.println("4. To Update Hotel Cost of rooms");
            System.out.println("Enter what to be updated");
            int choice=s.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Name to be Updated");
                    String name=s.next();
//                    HotelManagement.hotelData.get(id).setHotelName(s.next());
                    DbOperations.toUpdateHotelName(name, id);
                    break;
                case 2:
                    System.out.println("Enter Location Name to be Updated");
//                    HotelManagement.hotelData.get(id).setHotelLocation(s.next());
                    String location=s.next();
                    DbOperations.toUpdateHotelLocation(location, id);
                    break;
                case 3:
                    System.out.println("Enter Number of Rooms to be Updated");
//                    HotelManagement.hotelData.get(id).setNoOfRooms(s.nextInt());
                    int noOfRooms=s.nextInt();
                    DbOperations.toUpdateHotelNoOfRooms(noOfRooms, id);
                    break;
                case 4:
                    System.out.println("Enter Cost of room to be Updated");
//                    HotelManagement.hotelData.get(id).setCostPerRoom(s.nextFloat());
                    float costPerRoom=s.nextFloat();
                    DbOperations.toUpdateHotelCostOfRooms(costPerRoom, id);
                    break;
            
                default:
                System.out.println("Invalid choice");
                    break;
            }
            System.out.println("Successfully Updated!!\n");
            toDisplayHotelId(); 
        }else{
            System.out.println("This Id does not Exists!");
        }
       
    }
    public static void toDisplayHotelId(){
    	HashMap<Integer,HotelManagement> hotelData1=DbOperations.toDisplayHotelId();
       
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("HotelID \t\t HotelName \t\t HotelLocation \t\t HotelNoOfRooms \t\t CostPer Room");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        for(Map.Entry<Integer,HotelManagement> obj: hotelData1.entrySet()){
            System.out.print(obj.getValue().getHotelId()+"\t\t\t");
            System.out.print(obj.getValue().getHotelName()+"\t\t\t");
            System.out.print(obj.getValue().getHotelLocation()+"\t\t\t");
            System.out.print(obj.getValue().getNoOfRooms()+"\t\t\t");
            System.out.println(obj.getValue().getCostPerRoom()+"\t\t\t");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");

            
        }
        System.out.println("Successfully displayed!!\n"); 
    }
    public static void toSearchHotelId(){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the id to be removed");
        int id=s.nextInt();
        if(DbOperations.hotelExits(id)){
        	HotelManagement hotelData=DbOperations.toGetHotelData(id);
            System.out.println("Hotel Name is: "+hotelData.getHotelName());
            System.out.println("Hotel Location is: "+hotelData.getHotelLocation());
            System.out.println("Hotel No of rooms is: "+hotelData.getNoOfRooms());
            System.out.println("Hotel Cost per room is: "+hotelData.getCostPerRoom());
            System.out.println("Successfully Searched!!\n");
        }else{
            System.err.println("This Id does not Exists!");
        }
        
    }
    public static void main(String[] args) {
        while(true){
            Scanner s=new Scanner(System.in);
            printMenu();
            int choice=s.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Welcome To create Hotel ID");
                    toCreateHotelId();
                    break;
                case 2:
                    System.out.println("Welcome To Remove Hotel ID");
                    toRemoveHotelId();
                    break;
                case 3:
                    System.out.println("Welcome To Update Hotel ID");
                    toUpdateHotelId();;
                    break;
                case 4:
                    System.out.println("Welcome To Display Hotel ID");
                    toDisplayHotelId();;
                    break;
                case 5:
                    System.out.println("Welcome To Search Hotel ID");
                    toSearchHotelId();;
                    break;
                case 6:
                    System.exit(choice);
                    break;
            
                default:
                System.out.println("Invalid Choice !");
                System.out.println("Thank You!!!");
                    break;
            }
        }
    }
}
