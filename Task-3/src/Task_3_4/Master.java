package Task_3_4;

public class Master {
    private String name;
    private Boolean status;

    public Master(String name) {
        this.name = name;
        this.status = true;
    }

    public String getName() {
        return name;
    }
    public Boolean getStatus(){return status;}
    public void setStatus(Boolean status) {this.status = status;}

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", status=" + status;
    }
}
