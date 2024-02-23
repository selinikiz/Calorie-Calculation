import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        people.getPath("people.txt");
        foods.getPath("food.txt");
        sports.getPath("sport.txt");
        command.commands(args[0]);
    }
}
