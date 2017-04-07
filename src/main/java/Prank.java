import java.util.List;
import java.util.Random;

/**
 * Class Prank
 *
 * This class generates a new Prank
 * This has a mails and a superGroup
 * In a superGroup, someone will be chosen to send the email to the others
 */
public class Prank {


    private static final int GROUP_SIZE = 3;
    private static final int MIN_VICTIMS_PER_GROUP = 3;

    private Mail[] mails;
    private Group superGroup;

    public Prank() {
        superGroup = new Group();

        mails = new Mail[GROUP_SIZE];

        if (superGroup.size() / GROUP_SIZE < MIN_VICTIMS_PER_GROUP) {
            throw new IllegalArgumentException("Not enough victims !");
        }

        int length = superGroup.size() / GROUP_SIZE;
        Group subgroup;
        for (int i = 0; i < GROUP_SIZE - 1; ++i) {
            subgroup = new Group(superGroup.take(i * length,(i + 1) * length));
            Person victim = chooseSender(subgroup);
            mails[i] = new Mail(victim, subgroup);
        }

        subgroup = new Group(superGroup.take((GROUP_SIZE - 1) * length, superGroup.size() - 1));
        Person victim = chooseSender(subgroup);
        mails[GROUP_SIZE - 1] = new Mail(victim, subgroup);


    }


    public Person chooseSender(Group group) {

        List<Person> listPeople = group.getGroup();

        int bound = group.size();
        Random random = new Random();
        int index = random.nextInt(bound);
        Person victim = listPeople.get(index);
        group.remove(index);
        return victim;
    }

    public Mail[] getMails() {
        return mails;
    }




}
