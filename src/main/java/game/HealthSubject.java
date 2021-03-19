package game;

public interface HealthSubject {
    void attach(HealthObserver observer);

    void detatch(HealthObserver observer);

    void notifyObservers();
}
