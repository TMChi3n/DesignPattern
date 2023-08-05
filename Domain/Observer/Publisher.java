package Domain.Observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private List<Subcriber> subcribers;

    public Publisher() {
        subcribers = new ArrayList<>();
    }

    public void addObserver(Subcriber subcriber) {
        subcribers.add(subcriber);
    }

    public void removeObserver(Subcriber subcriber) {
        subcribers.remove(subcriber);
    }

    public void notifyObserver() {
        for (Subcriber subcriber : subcribers) {
            subcriber.update();
        }
    }
}
