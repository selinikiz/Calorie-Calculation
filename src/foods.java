import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class foods {
    private final String foodID;
    private final String foodName;
    private final int foodCalorie;
    static ArrayList<foods> foodList = new ArrayList<>();

    public static void getPath(String pathname) throws IOException { // create food objects from food.txt lines
        File file = new File(pathname);
        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            String[] lines = read.nextLine().split("\t");
            foodList.add(new foods(lines[0],lines[1],lines[2])); // foodList will be used in command class to detect given food
        }
    }

    foods(String id,String name,String calorie ) {
        this.foodID = id;
        this.foodName = name ;
        this.foodCalorie = Integer.parseInt(calorie);
    }

    public String getFoodID() {
        return foodID;
    }

    public int getFoodCalorie(String portion) {
        return foodCalorie * Integer.parseInt(portion);
    }

    public String getFoodName() {
        return foodName;
    }

}
