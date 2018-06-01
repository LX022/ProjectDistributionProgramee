import java.util.ArrayList;
import java.util.ListIterator;

public class usersList {
	ArrayList<user> usersList = new ArrayList<user>();

	synchronized public void add(user u) {
		usersList.add(u);
	}

	synchronized public void remove(String id) {
		int index = 0;
		ListIterator<user> iu = usersList.listIterator();
		while (iu.hasNext()) {
			if (iu.next().getId().equals(id)) {
				index = iu.nextIndex();
			}
		}
		usersList.remove(index);
	}

}
