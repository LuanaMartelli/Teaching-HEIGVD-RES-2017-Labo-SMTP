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
    public static final int NB_GROUP = 2;

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
        while ((line = reader.readLine()) != null) {
            listOfPersons.add(new Person(line));
        }
    }

    public List<Person> getGroup() {
        return new ArrayList<>(listOfPersons);
    }

    public void setGroup(List<Person> list) {
        this.listOfPersons = list;
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
