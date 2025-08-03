
package entity;

public enum Status {
    ACTIVE(1),INACTIVE(2),BLOCK(3);
    
    private final int id;
    private Status(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
}
