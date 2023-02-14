package thucthe;

public class SuKien extends ThucThe{
	
	private String thoiGianDienRa;
	
	public SuKien(String tenSukien, String thoiGianDienRa) {
		super(tenSukien);
		this.thoiGianDienRa = thoiGianDienRa;
	}
	
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append("Sự kiện: ");
		ans.append(ten);
		ans.append("\n");
		ans.append("Thời gian diễn ra: ");
		ans.append(thoiGianDienRa);
		return ans.toString();
	}
}
