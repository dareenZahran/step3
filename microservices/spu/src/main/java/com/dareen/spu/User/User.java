 package com.dareen.spu.User;
// import java.util.List;
// import java.util.Objects;

// import java.util.List;

// import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import javax.persistence.OneToMany;
// import com.dareen.spu.Category.Size;
// @Entity
// public class User {
// import com.dareen.spu.Order.Order;

//     private @Id @GeneratedValue Long id;
//     @NotBlank(message = "first name name is required")
//     @Size(min = 2, max = 20, message = "first name name must be between 2 and 50 characters")
//     private String firstName;
//     private String lastName;
//     @NotBlank(message = "email  is required")
   
//     private String email;
//     @NotBlank(message = "password  is required")
//     @Size(min = 2, max = 50, message = "password name must be between 2 and 50 characters")
//     private String password;
//     private String address;
//     @NotBlank(message = "phoneNumber  is required")
//     @Size(min =9, max = 9, message = "phoneNumber  must be between 2 and 50 characters")
//     private String phoneNumber;

    

	
//     @JsonManagedReference(value="cOrder")
//     @OneToMany(cascade = CascadeType.ALL)
//     private List<Order> orders;

// 	// @JoinColumn(name ="userNumber")
// 	// private List<Review> reviews;

//     public User(){ }

//     public User( String firstName, String lastName, String email, String password, String address,
//             String phoneNumber) {
      
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.email = email;
//         this.password = password;
//         this.address = address;
//         this.phoneNumber = phoneNumber;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getFirstName() {
//         return firstName;
//     }

//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }

//     public String getLastName() {
//         return lastName;
//     }

//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public String getAddress() {
//         return address;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }

//     public String getPhoneNumber() {
//         return phoneNumber;
//     }

//     public void setPhoneNumber(String phoneNumber) {
//         this.phoneNumber = phoneNumber;
//     }

    
    
//     @Override
//     public int hashCode() {
//         return Objects.hash(this.id, this.firstName, this.lastName, this.address, this.email, this.password,
//                 this.phoneNumber);
//     }

//     @Override
//     public String toString() {
//         return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
//                 + '\''
//                 + ", address='" + this.address + '\''
//                 + ", email='" + this.email + '\''
//                 + ", password='" + this.password + '\''
//                 + ", phoneNumber='" + this.phoneNumber + '\''

//                 + '}';
//     }

// }






import javax.persistence.GenerationType;



@Entity
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames="username")})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20, message = "", min = 0)
  private String username;

  @NotBlank
  @Size(max = 50, message = "", min = 0)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120, message = "", min = 0)
  private String password;

    // @JsonManagedReference(value="Order")
    // @OneToMany(cascade = CascadeType.ALL)
    // private List<Order> orders;

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  // public Set<Role> getRoles() {
  //   return roles;
  // }

  // public void setRoles(Set<Role> roles) {
  //   this.roles = roles;
  // }
}

