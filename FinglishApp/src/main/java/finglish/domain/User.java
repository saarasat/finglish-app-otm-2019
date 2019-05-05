
package finglish.domain;

public class User {
    private String username;
    private String password;
    private int id;
    private int adminStatus;
    
    public User(String username, String password, int adminStatus) {
        this.username = username;
        this.password = password;      
        this.adminStatus = adminStatus;
    }
       
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setAdmin(){
        this.adminStatus = 1;
    }
    
    public void removeAdmin(){
        this.adminStatus = 0;
    }
    
    public int getAdmin() {
        return this.adminStatus;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }   
}
