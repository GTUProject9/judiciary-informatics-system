
/**
 * Every system object has an ID length of 5 and extends this class.
 */
public abstract class AbstractSystemObject implements Comparable<AbstractSystemObject>
{
    // Static variables
    public static final int NUMBER_OF_SYSTEM_OBJECTS = 6;
    public static final int ID_LENGTH = 5;
    
    // Data field
    protected int id;

    // Constructor
    protected AbstractSystemObject() 
    {
        id = -1;
    }

    // Constructor with parameter
    protected AbstractSystemObject(int id) 
    { 
        this.id = id; 
    }

    /**
     * This function returns the value of the id variable.
     * 
     * @return The id of the object.
     */
    protected int getId() {
        return id;
    }
    
    /**
     * This function sets the id of the object to the id passed in as a parameter.
     * 
     * @param id The id of the item.
     */
    protected void setId(int id) {
        this.id = id;
    }
    
    /**
     * The compareTo function compares the id of the current object to the id of the object passed in
     * as a parameter
     * 
     * @param other The other object to compare to.
     * @return The id of the object.
     */
    @Override
    public int compareTo(AbstractSystemObject other) 
    {
        return Integer.valueOf(id).compareTo(other.id);
    }

    /**
     * The toString method returns a string representation of abstract system object
     * 
     * @return The id of the object.
     */
    @Override
    public String toString() {
        return "ID: " + id;
    }
}
