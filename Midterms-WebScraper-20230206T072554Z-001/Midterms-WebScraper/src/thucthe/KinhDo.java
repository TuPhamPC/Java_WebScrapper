package thucthe;

public class KinhDo extends ThucThe {
	
	private String thoiKy;
	
	private String thoiGian;

	public KinhDo(String ten, String thoiKy, String thoiGian) {
		super(ten);
		this.thoiKy = thoiKy;
		this.thoiGian = thoiGian;
	}
	
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append("Kinh đô: ");
		ans.append(ten);
		ans.append("\n");
		ans.append("Thời kỳ: ");
		ans.append(thoiKy);
		ans.append("\n");
		ans.append("Thời gian: ");
		ans.append(thoiGian);
		return ans.toString();
	}

}
