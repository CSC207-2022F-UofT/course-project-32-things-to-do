public class Test extends Task{
    public Test(String title) {
        super(title);
    }
    public Test(String title, int priority) {
        super(title, priority);
    }
    /**
     * Delete a Test by moving it to the user's archive
     * @return - whether the Test has been successfully deleted
     */
    protected boolean delete() {
        return true;
    }

    /**
     * Save a Test to the user's data
     * @return - whether the Test has been successfully saved
     */
    protected boolean save() {
        return true;
    }

    /**
     * Edit the features of the Test
     */
    protected void edit() {

    }
}
