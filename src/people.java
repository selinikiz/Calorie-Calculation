import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class people {
    private final String personID;
    private final String personName;
    private final String gender;
    private final int weight;
    private final int height;
    private final int age;
    private long calorieNeed;
    private int calorieTaken;
    private int calorieBurned;
    private int result;
    static ArrayList<people> personList = new ArrayList<>();

    public static void getPath(String pathname) throws IOException { // creates person objects from people.txt lines
        File file = new File(pathname);
        Scanner read = new Scanner(file);
        while (read.hasNext()) {
            String[] lines = read.nextLine().split("\t");
            personList.add(new people(lines[0],lines[1],lines[2],lines[3],lines[4],lines[5])); // personList will be used in command class to detect people
        }
    }

    public people(String id,String name,String gender,String weight,String height,String age) {
        this.personID = id;
        this.personName = name ;
        this.gender = gender;
        this.weight = Integer.parseInt(weight);
        this.height = Integer.parseInt(height);
        this.age = 2022 - Integer.parseInt(age);
        if (this.gender.equals("male")) {
            this.calorieNeed = Math.round((66 + (13.75*this.weight) + (5*this.height) - (6.8*this.age)));
        }
        if (this.gender.equals("female")) {
            this.calorieNeed = Math.round((665 + (9.6*this.weight) + (1.7*this.height) - (4.7*this.age)));
        }
    }

    public static String personinfo(people p) {
        return p.getPersonName()+"\t"+p.getAge()+"\t"+p.getCalorieNeed()+p.getCalorieTaken()+p.getCalorieBurned()+p.getStringResult();
    }

    public int getResult() {
        this.result= Math.round((this.calorieTaken-this.calorieBurned)-this.calorieNeed);
        return result;
    }

    public String getStringResult() { // to add + sign to result if it is positive
        getResult();
        if (this.result>0) {
            return "+"+this.result+"kcal";
        }
        else {
            return (this.result)+"kcal";
        }
    }

    public String getPersonID() {
        return personID;
    }

    public String getPersonName() {
        return personName;
    }

    public int getAge() {
        return age;
    }

    public String getCalorieNeed() {
        return calorieNeed+"kcal\t";
    }

    public String getCalorieTaken() {
        return calorieTaken+"kcal\t";
    }

    public void setCalorieTaken(int calorietaken) { //add calories whenever a person eats something
        this.calorieTaken += calorietaken;
    }

    public String getCalorieBurned() {
        return calorieBurned+"kcal\t";
    }

    public void setCalorieBurned(int calorieburnt) { //add calories as burned whenever a person does sport
        this.calorieBurned += calorieburnt;
    }
}
