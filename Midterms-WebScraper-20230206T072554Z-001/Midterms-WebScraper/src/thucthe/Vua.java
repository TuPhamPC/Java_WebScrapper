package thucthe;

public class Vua extends ThucThe {

	private String mieuHieu;

	private String thuyHieu;

	private String nienHieu;

	private String tenHuy;

	private String theThu;

	private String triVi;

	public Vua(String ten, String mieuHieu, String thuyHieu,
			String nienHieu, String tenHuy, String theThu,
			String triVi) {
		super(ten);
		this.mieuHieu = mieuHieu;
		this.thuyHieu = thuyHieu;
		this.nienHieu = nienHieu;
		this.tenHuy = tenHuy;
		this.theThu = theThu;
		this.triVi = triVi;
	}
	
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append("Tước hiệu: ");
		ans.append(ten);
		ans.append("\n");
		ans.append("Miếu hiệu: ");
		ans.append(mieuHieu);
		ans.append("\n");
		ans.append("Thùy hiệu: ");
		ans.append(thuyHieu);
		ans.append("\n");
		ans.append("Niên hiệu: ");
		ans.append(nienHieu);
		ans.append("\n");
		ans.append("Tên húy: ");
		ans.append(tenHuy);
		ans.append("\n");
		ans.append("Thế thứ: ");
		ans.append(theThu);
		ans.append("\n");
		ans.append("Trị vì: ");
		ans.append(triVi);
		return ans.toString();
	}

}
