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
        int bound = group.size();
        Random random = new Random();
        int index = random.nextInt(bound);
        Person victim = group.get(index);
        group.remove(index);
        return victim;
    }

    public Mail getMail() {
        return mail;
    }




}
