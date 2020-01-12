package RussiaRT.news;


import javax.persistence.*;



@Entity
@Table(name = "RTnews")
public class news {
	 
	private long id;
	private String header;
	private String time;
	
	
	
	@Id
    @GeneratedValue
    @Column(name = "Id")
	public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    @Column(name = "header")
    public String getHeader() { return header; }
    public void setHeader(String header) { this.header = header; }
    
    @Column(name = "time")
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}

