import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Group
 *
 * This class represents a Group of people
 * People are read from a file
 */
public class Group {

    private final ArrayList<Person> listOfPersons;

    public Group() {
        listOfPersons = new ArrayList<>();
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("Error while reading file..." +
                    "Group empty !");
        }
    }


    public  Group(List<Person> list) {
        listOfPersons = new ArrayList<>(list);
    }

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/data/victims.utf8"));
        String line;

        while ((line = reader.readLine()) != null) {
            listOfPersons.add(new Person(line));
        }

    }

    public ArrayList<Person> take(int from, int to) {
        return new ArrayList<>(listOfPersons.subList(from, to));
    }

    public List<Person> getGroup() {
        return new ArrayList<>(listOfPersons);
    }

    public void remove(int index) {
        listOfPersons.remove(index);
    }

    public Group group() {
        return this;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < listOfPersons.size(); ++i) {
            s += listOfPersons.get(i);
            if (i != listOfPersons.size() - 1) {
                s += ", ";
            }
        }
        return s;
    }


    public int size() {
        return listOfPersons.size();
    }

}
