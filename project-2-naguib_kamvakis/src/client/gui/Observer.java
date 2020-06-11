package client.gui;

/**
 * This is an observer interface used for updating GUI's.
 * @param <Subject>
 */
public interface Observer<Subject>  {


    /**
     * This method updates the observer when called.
     * @param subject subject being updated
     */
    void update(Subject subject);
}
