import java.util.Map;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Closet closet = new Closet();
        Clothing c1 = new Clothing("T-Shirt", "Green");
        Clothing c2 = new Clothing("Shoes", "Black");
        Clothing c3 = new Clothing("Jeans", "Blue");
        Clothing c4 = new Clothing("Cap", "Green");
        Clothing c5 = new Clothing("Jeans", "Black");
        closet.add(c1);
        closet.add(c2);
        closet.add(c3);
        closet.add(c4);
        closet.add(c5);
        System.out.println(closet.getClothes("Black"));
        Map<String, String> daysOfColor = Map.of("Wednesday", "Pink", "Friday", "Green");
        String currentDay = "Friday";
        System.out.println(closet.getItemsByDay(daysOfColor, currentDay));
        closet.dyeClothes(List.of("Green"), "Black");
        System.out.println(closet.getColorMap());
    }

}
