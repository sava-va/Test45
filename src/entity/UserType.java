
package entity;

public enum UserType {
    ADMIN(1), INSTRUCTOR(2),CLIENT(3);

    private final int id;

    private UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    
    
}
