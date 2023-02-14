package scraper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

import thucthe.LeHoi;

public class LeHoiScraper implements Scraper {

	@Override
	public void scrape() throws IOException {
		Document doc=  Jsoup.connect("https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam").get();
		int i=0;
		doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
		
		ArrayList<LeHoi> listLehoi=new ArrayList<LeHoi>();
		

		for(Element row : doc.select("table.prettytable.wikitable tr")) {
			i++;
			if(i==1) continue;
			else {
				if(row.select("td:nth-of-type(1)").text().equals("")) continue;
				else {
					String ngayBD=row.select("td:nth-of-type(1)").text(); ngayBD=ngayBD.replaceAll("\\[\\S*\\]","");
					String viTri=row.select("td:nth-of-type(2)").text();  viTri=viTri.replaceAll("\\[\\S*\\]","");
					String tenLH=row.select("td:nth-of-type(3)").text();   tenLH=tenLH.replaceAll("\\[\\S*\\]","");
					String toChucLan1=row.select("td:nth-of-type(4)").text(); toChucLan1=toChucLan1.replaceAll("\\[\\S*\\]","");
					String nhanVatLQ=row.select("td:nth-of-type(5)").text();  nhanVatLQ=nhanVatLQ.replaceAll("\\[\\S*\\]","");
					listLehoi.add(new LeHoi(tenLH,ngayBD,viTri,nhanVatLQ,toChucLan1));
					
				}
			}
		}
		Gson gson=new Gson();
		String json=gson.toJson(listLehoi);
		FileOutputStream fos=null;
		fos=new FileOutputStream("LeHoi.json");
		byte[] data =json.getBytes("utf8");
		fos.write(data);
		fos.close();
	}

}
