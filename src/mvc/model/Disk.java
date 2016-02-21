package mvc.model;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity 
@Table(name = "Disks")
public class Disk {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "UserId")
	private Long UserId;	
	@Column(name = "DiskName", length = 100, nullable = false) 
	private String DiskName;
	
//	@OneToMany(fetch=FetchType.LAZY,mappedBy = "disk"
//			)
//    private List<TakenItem> takenItems = new ArrayList<TakenItem>();
//	public void addTakenItem(TakenItem takenItem)
//	{
//		takenItem.setDisk(this);
//		this.takenItems.add(takenItem);
//	}
	
	public Disk()
	{
	}
	
	public void SetDisk(String DiskName)
	{
		this.DiskName = DiskName;
	}
	
	public void SetUserId(Long id)
	{
		this.UserId = id;
	}
	
}
