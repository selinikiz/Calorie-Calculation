import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class command {
    static String output="";
    static ArrayList<people> commandPeople = new ArrayList<>(); // it will be used for adding people in commands, and use them later for printWarn and printList commands
    static String star ="***************";

    public static void commands(String pathname) throws IOException {
        File file = new File(pathname);
        Scanner read = new Scanner(file);
        PrintWriter writer = new PrintWriter("monitoring.txt");

        while (read.hasNext()) {
            String[] lines = read.nextLine().split("\t");
            if (!read.hasNext()) {
                star = ""; // to avoid stars at the end of file
            }

            if (lines.length == 3) { // this part contains commands other than print- commands
                for (people p: people.personList) {
                    if (p.getPersonID().equals(lines[0])) {
                        if (lines[1].startsWith("1")) { // to detect foods
                            for (foods food : foods.foodList) {
                                if (food.getFoodID().equals(lines[1])) {
                                    p.setCalorieTaken(food.getFoodCalorie(lines[2])); // getFoodCalorie takes portion as argument
                                    output = output.concat(p.getPersonID()+"\thas\ttaken\t"+food.getFoodCalorie(lines[2])+"kcal\tfrom\t"+food.getFoodName()+"\n");
                                    if (!(commandPeople.contains(p))) {
                                        commandPeople.add(p);
                                    }
                                }
                            }
                        }
                        else if (lines[1].startsWith("2")) { // to detect sports
                            for (sports sport : sports.sportList) {
                                if (sport.getSportID().equals(lines[1])) {
                                    p.setCalorieBurned(sport.getSportCalorie(lines[2])); // getSportCalorie takes duration as argument
                                    output = output.concat(p.getPersonID()+"\thas\tburned\t"+sport.getSportCalorie(lines[2])+"kcal\tthanks to\t"+sport.getSportName()+"\n");
                                    if (!(commandPeople.contains(p))) {
                                        commandPeople.add(p);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            else if (lines.length == 1) { //this part contains print- commands
                int peoplewarn =0;
                if (lines[0].equals("printList")) {
                    for (people p:commandPeople) {
                        output = output.concat(people.personinfo(p)+"\n"); // personinfo returns given person's information(name,age,calorieneed,...)
                    }
                }
                else if (lines[0].equals("printWarn")) {
                    for (people p:commandPeople) {
                        if (p.getResult()>0) {
                            output = output.concat(people.personinfo(p)+"\n");
                            peoplewarn++;
                        }
                    }
                    if (peoplewarn==0) {
                        output = output.concat("there\tis\tno\tsuch\tperson\n");
                    }
                }
                else {
                    for (people p:people.personList) {
                        if (p.getPersonID().equals(lines[0].substring(6,11))) {
                            output = output.concat(people.personinfo(p)+"\n");
                        }
                    }
                }
            }
            output =output.concat(star+"\n"); //print stars after every command
        }
        writer.print(output.substring(0,output.length()-2)); //to get rid of '\n' at the end of file
        writer.close();
    }
}

