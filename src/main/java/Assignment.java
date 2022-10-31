public class Assignment extends Task{
    public Assignment(String title) {
        super(title);
    }
    public Assignment(String title, int priority) {
        super(title, priority);
    }

    protected boolean delete() {
        return true;
    }
    protected boolean save() {
        return true;
    }
    protected void edit() {

    }
}
