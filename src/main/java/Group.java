import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Group
 *
 * This class represents a Group of people
 * People are read from a file
 */
public class Group {

    public static int group_size = 3;

    private List<Person> listOfPersons;

    public Group() {
        listOfPersons = new ArrayList<>();
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("Error while reading file..." +
                    "Group empty !");
        }
    }

    public void readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/data/victims.utf8"));
        String line;
        int index = 0;
        while (index < group_size && (line = reader.readLine()) != null) {
            listOfPersons.add(new Person(line));
            ++index;
        }
    }

    public List<Person> getGroup() {
        return new ArrayList<>(listOfPersons);
    }

    public void remove(int index) {
        listOfPersons.remove(index);
    }

    public int size() {
        return listOfPersons.size();
    }

    public Person get(int index) {
        return listOfPersons.get(index);
    }
}
