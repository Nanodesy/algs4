import java.security.SecureRandom;
import java.util.LinkedList;

/**
 * Question 2.
 */
class ShufflingALinkedList {
    private static SecureRandom random = new SecureRandom();

    public static void shuffle(LinkedList list) {
        if (list.size() == 1) {
            return;
        }

        int listHalfSize = list.size() / 2;
        LinkedList leftSubList = new LinkedList();
        LinkedList rightSubList = new LinkedList();

        while (!list.isEmpty()) {
            if (list.size() > listHalfSize) {
                leftSubList.addFirst(list.removeFirst());
            } else {
                rightSubList.addFirst(list.removeFirst());
            }
        }

        shuffle(leftSubList);
        shuffle(rightSubList);

        while (!leftSubList.isEmpty() && !rightSubList.isEmpty()) {
            if (random.nextBoolean()) {
                list.addFirst(leftSubList.removeFirst());
            } else {
                list.addFirst(rightSubList.removeFirst());
            }
        }

        while (!leftSubList.isEmpty()) {
            list.addFirst(leftSubList.removeFirst());
        }

        while (!rightSubList.isEmpty()) {
            list.addFirst(rightSubList.removeFirst());
        }
    }
}
