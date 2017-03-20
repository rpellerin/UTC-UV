package beans;

/**
 * The User class is used to create new user
 */
public class User {

	private String email;
	private String password;
	private String name;
	private String company;
	private String phoneNumber;
	private String creation;
	private boolean isActive;
	private boolean isAdmin;

	public User() {
		
	}
	
	public User(String email,
				String password,
				String name,
				String company,
				String phoneNumber,
				String creation,
				Boolean isActive,
				Boolean isAdmin) {
		
		this.email 		 = email;
		this.password    = password;
		this.name 		 = name;
		this.company 	 = company;
		this.phoneNumber = phoneNumber;
		this.creation    = creation;
		
		if (isActive != null) this.isActive = isActive.booleanValue();
		if (isAdmin != null)  this.isAdmin  = isAdmin.booleanValue();
	}
	
	public boolean isValid() {
		return !(email == null || password == null || email.length() == 0 || password.length() == 0);
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCreation() {
		return creation;
	}

	public void setCreation(String creation) {
		this.creation = creation;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", company=" + company + ", phoneNumber=" + phoneNumber
				+ ", isAdmin=" + isAdmin + "]";
	}
}
