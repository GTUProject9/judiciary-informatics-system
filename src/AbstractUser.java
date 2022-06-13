/**
 * Super class for all users.
 * Includes password, firstName, lastName, email, phone
 * and extend AbstractSystemObject class which keeps the ID.
 */
public abstract class AbstractUser extends AbstractSystemObject
{
    // Data fields
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;

    // Constructor
    protected AbstractUser() 
    {
        super();
        password = "";
        firstName = "";
        lastName = "";
        email = "";
        phone = "";
    }
    
    // Constructor with parameters
    protected AbstractUser(int id, String password, String firstName, String lastName, String email, String phone) 
    {
        super(id);
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    // Getter and setters

    public String getPassword() {
        return password;
    }
    
    protected void setPassword(String password) {
        this.password = password;
    }
    
    protected String getFirstName() {
        return firstName;
    }
    
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    protected String getLastName() {
        return lastName;
    }
    
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    protected String getEmail() {
        return email;
    }
    
    protected void setEmail(String email) {
        this.email = email;
    }
    
    protected String getPhone() {
        return phone;
    }
    
    protected void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString() + "\n");
        sb.append("First Name: ").append(firstName).append("\n");
        sb.append("Last Name: ").append(lastName).append("\n");
        sb.append("E-mail: ").append(email).append("\n");
        sb.append("Phone: ").append(phone).append("\n");
        return sb.toString();
    }

    // Every user has a menu
    /**
     * This function is used to display the menu for the user to select from.
     * 
     * @param systemObjectRef The object of the class that extends SystemClass.
     */
    protected abstract void menu(SystemClass systemObjectRef);
}
