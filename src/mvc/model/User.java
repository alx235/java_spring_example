package mvc.model;

import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id;
import javax.persistence.Table; 

@Entity 
@Table(name = "Users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private Long id;
	@Column(name = "UserName", length = 100, nullable = false) 
	private String UserName;
	@Column(name = "password", length = 100, nullable = false) 
	private String password;
	
//	@OneToMany(fetch=FetchType.LAZY,mappedBy = "user")
//    private List<TakenItem> takenItems = new ArrayList<TakenItem>();
//	
//	public void addTakenItem(TakenItem takenItem)
//	{
//		takenItem.setUser(this);
//		this.takenItems.add(takenItem);
//	}
	
	public void SetUserName(String UserName)
	{
		this.UserName = UserName;
	}
	
	public void Setpassword(String password)
	{
		this.password = password;
	}	
	
	public Long GetId()
	{
		return id;
	}
	
	public User () 
	{
	}
	
}