package thucthe;

public class LeHoi extends ThucThe {
	
	private String ngayToChuc;
	
	private String tenDiaDiem;
	
	private String nhanVatLienQuan;
	
	private String toChucLanDau;

	public LeHoi(String ten, String ngayToChuc, String tenDiaDiem,
			String nhanVatLienQuan, String toChucLanDau) {
		super(ten);
		this.ngayToChuc = ngayToChuc;
		this.tenDiaDiem = tenDiaDiem;
		this.nhanVatLienQuan = nhanVatLienQuan;
		this.toChucLanDau = toChucLanDau;
	}
	
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append("Lễ hội: ");
		ans.append(ten);
		ans.append("\n");
		ans.append("Ngày tổ chức: ");
		ans.append(ngayToChuc);
		ans.append("\n");
		ans.append("Tên địa điểm: ");
		ans.append(tenDiaDiem);
		ans.append("\n");
		ans.append("Nhân vật liên quan: ");
		ans.append(nhanVatLienQuan);
		ans.append("\n");
		ans.append("Tổ chức lần đầu: ");
		ans.append(toChucLanDau);
		return ans.toString();
	}

}
