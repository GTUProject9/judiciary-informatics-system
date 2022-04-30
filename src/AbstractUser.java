
/**
 * Super class for all users.
 * Includes id, password, firstName, lastName, email, phone.
 */
public abstract class AbstractUser extends AbstractSystemObject
{
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    protected AbstractUser() 
    {
        super();
        password = "";
        firstName = "";
        lastName = "";
        email = "";
        phone = "";
    }
    
    protected AbstractUser(int id, String password, String firstName, String lastName, String email, String phone) 
    {
        super(id);
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    protected String getPassword() {
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
        sb.append("password: ").append(password).append("\n");
        sb.append("firstName: ").append(firstName).append("\n");
        sb.append("lastName: ").append(lastName).append("\n");
        sb.append("email: ").append(email).append("\n");
        sb.append("phone: ").append(phone).append("\n");

        return sb.toString();
    }

}
