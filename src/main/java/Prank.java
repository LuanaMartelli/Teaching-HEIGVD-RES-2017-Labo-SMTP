import java.util.List;
import java.util.Random;

/**
 * Class Prank
 *
 * This class generates a new Prank
 * This has a mail and a group
 * In a group, someone will be chosen to send the email to the others
 */
public class Prank {

    private Mail mail;
    private Group group;

    public Prank() {
        group = new Group();
        Person victim = chooseVictim();
        mail = new Mail(victim, group);
    }


    public Person chooseVictim() {

        List<Person> listPeople = group.getGroup();

        int bound;
        Random random = new Random();

        int index;
        while (listPeople.size() > (group.size() / group.NB_GROUP)) {
            bound = listPeople.size();
            index = random.nextInt(bound);
            listPeople.remove(index);
        }

        if (listPeople.size() < 3) {
            System.out.println("Not enough people in group !!!");
            System.exit(0);
        }

        bound = listPeople.size();

        index = random.nextInt(bound);
        Person victim = listPeople.get(index);
        listPeople.remove(index);
        group.setGroup(listPeople);
        return victim;
    }

    public Mail getMail() {
        return mail;
    }




}
