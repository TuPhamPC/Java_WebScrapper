package thucthe;


public abstract class ThucThe {
	
	protected String ten;

	public String getTen() {
		return ten;
	}

	public ThucThe(String ten) {
		this.ten = ten;
	}
	
	public boolean isMatch(String ten) {
		String curTitle = this.ten.toLowerCase();
		String cmpTitle = ten.toLowerCase();
		return curTitle.contains(cmpTitle);
	}

}
