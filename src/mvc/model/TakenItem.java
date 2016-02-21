package mvc.model;

import javax.persistence.Column; 
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.Table;

@Entity 
@Table(name = "TakenItems")
public class TakenItem {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "DiskId")
	private Long DiskId;	
	@Column(name = "UserId")
	private Long UserId;	
	
//	@Fetch(FetchMode.JOIN) 
//	@ManyToOne(cascade=CascadeType.PERSIST)
//    @JoinColumn(name="DiskId",nullable = false,referencedColumnName = "id")
//    private Disk disk;
//	public void setDisk (Disk disk)
//	{
//		this.disk = disk;
//	}
//	
//	@Fetch(FetchMode.JOIN)
//	@ManyToOne(cascade=CascadeType.PERSIST)
//    @JoinColumn(name="UserId",nullable = false,referencedColumnName = "id")
//    private User user;
//	public void setUser (User user)
//	{
//		this.user = user;
//	}
	public void setDiskId (Long id)
	{
		this.DiskId = id;
	}
	public void setUserId (Long id)
	{
		this.UserId = id;
	}	
	public TakenItem()
	{
	}
	
}