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

    /**
     * This function returns the password of the user
     * 
     * @return The password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * This function sets the password of the user
     * 
     * @param password The password to use for the connection.
     */
    protected void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * This function returns the first name of the person
     * 
     * @return The first name of the person.
     */
    protected String getFirstName() {
        return firstName;
    }
    
    /**
     * This function sets the first name of the person
     * 
     * @param firstName The first name of the person.
     */
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * This function returns the last name of the person
     * 
     * @return The last name of the person.
     */
    protected String getLastName() {
        return lastName;
    }
    
    /**
     * This function sets the last name of the person
     * 
     * @param lastName The last name of the person.
     */
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * It returns the email of the user.
     * 
     * @return The email address of the user.
     */
    protected String getEmail() {
        return email;
    }
    
    /**
     * This function sets the email of the user
     * 
     * @param email The email address of the user.
     */
    protected void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * This function returns the phone number of the user
     * 
     * @return The phone number.
     */
    protected String getPhone() {
        return phone;
    }
    
    /**
     * This function sets the phone number of the user
     * 
     * @param phone The phone number to send the message to.
     */
    protected void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * The toString method returns a string representation of the 
     * the abstract user object
     * 
     * @return The toString method is being returned.
     */
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
