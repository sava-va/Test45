
package entity;

public enum Gender {
    Male(1),Female;
    
    private final int id;
    
    private Gender(int id){
        this.id = id;
    }
    private Gender(){
        id = 0;
    }
    public int getId(){
        return this.id;
    }
   
}
