package thucthe;

public class TrieuDai extends ThucThe {
	
	private String nguoiSangLap;
	
	private String queHuong;
	
	private String kinhDo;

	public TrieuDai(String ten, String nguoiSangLap,
			String queHuong, String kinhDo) {
		super(ten);
		this.nguoiSangLap = nguoiSangLap;
		this.queHuong = queHuong;
		this.kinhDo = kinhDo;
	}
	
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append("Triều đại: ");
		ans.append(ten);
		ans.append("\n");
		ans.append("Người sáng lập: ");
		ans.append(nguoiSangLap);
		ans.append("\n");
		ans.append("Quê hương: ");
		ans.append(queHuong);
		ans.append("\n");
		ans.append("Kinh đô: ");
		ans.append(kinhDo);
		return ans.toString();
	}
}
