package thucthe;

public class DiTich extends ThucThe{
	
	private String viTri;

	public DiTich(String ten, String viTri) {
		super(ten);
		this.viTri = viTri;
	}
	
	@Override
	public String toString() {
		StringBuilder ans = new StringBuilder();
		ans.append("Tên di tích: ");
		ans.append(ten);
		ans.append("\n");
		ans.append("Vị trí: ");
		ans.append(viTri);
		return ans.toString();
	}

}
