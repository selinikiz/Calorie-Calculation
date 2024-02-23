import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class sports {
    private final String sportID;
    private final String sportName;
    private final int sportCalorie;
    static ArrayList<sports> sportList = new ArrayList<>();

    public static void getPath(String pathname) throws IOException { //create sport objects from sport.txt lines
        File file = new File(pathname);
        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            String[] lines = read.nextLine().split("\t");
            sportList.add(new sports(lines[0],lines[1],lines[2])); // sportList will be used in command class to detect given sport
        }
    }

    public sports(String id,String name,String calorie ) {
        this.sportID = id;
        this.sportName = name ;
        this.sportCalorie = Integer.parseInt(calorie);
    }

    public String getSportID() {
        return sportID;
    }

    public int getSportCalorie(String duration) {
        return (int) (sportCalorie*(Double.parseDouble(duration)/(double) 60));
    }

    public String getSportName() {
        return sportName;
    }
}
