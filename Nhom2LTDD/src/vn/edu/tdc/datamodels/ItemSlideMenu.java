package vn.edu.tdc.datamodels;

public class ItemSlideMenu {

	private int icon;
	private String title;

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ItemSlideMenu(int icon, String title) {
		super();
		this.icon = icon;
		this.title = title;
	}

}
