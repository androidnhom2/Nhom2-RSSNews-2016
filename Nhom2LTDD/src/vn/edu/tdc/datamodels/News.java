package vn.edu.tdc.datamodels;

public class News {
	private int id;

	private String strTitle;
	private String strImage;
	private String strDate;
	private String strLink;
	private String strDesctiption;
	
	public News() {
		
	}

	public News(String strTitle, String strImage, String strDate,
			String strLink, String strDesctiption) {
		super();
		this.strTitle = strTitle;
		this.strImage = strImage;
		this.strDate = strDate;
		this.strLink = strLink;
		this.strDesctiption = strDesctiption;
	}

	public News(String strTitle, String strImage, String strDate, String strLink) {
		super();
		this.strTitle = strTitle;
		this.strImage = strImage;
		this.strDate = strDate;
		this.strLink = strLink;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public String getStrImage() {
		return strImage;
	}

	public void setStrImage(String strImage) {
		this.strImage = strImage;
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getStrLink() {
		return strLink;
	}

	public void setStrLink(String strLink) {
		this.strLink = strLink;
	}

	public String getStrDesctiption() {
		return strDesctiption;
	}

	public void setStrDesctiption(String strDesctiption) {
		this.strDesctiption = strDesctiption;
	}

}
