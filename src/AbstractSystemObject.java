/**
 * Super class of all system objects that contains an id.
 * It contains an id as field and compareTo method. 
 */
public abstract class AbstractSystemObject implements Comparable<AbstractSystemObject>
{
    // Constants
    public static final int NUMBER_OF_SYSTEM_OBJECTS = 6;
    public static final int ID_LENGTH = 5;
    protected int id;

    // Constructors
    protected AbstractSystemObject() 
    {
        id = -1;
    }
    protected AbstractSystemObject(int id) 
    { 
        this.id = id; 
    }


    protected int getId() {
        return id;
    }
    
    protected void setId(int id) {
        this.id = id;
    }
    
    @Override
    public int compareTo(AbstractSystemObject other) 
    {
        return Integer.valueOf(id).compareTo(other.id);
    }

    @Override
    public String toString() {
        return "id: " + id;
    }
}
