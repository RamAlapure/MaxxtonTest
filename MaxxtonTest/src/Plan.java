import java.util.Date;

public class Plan {

	private Long id;
	private Date startDate;
	private Date endDate;
	
	public Plan() {
		// TODO Auto-generated constructor stub
	}

	public Plan(Long id, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Plan [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
