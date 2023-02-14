package scraper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.gson.Gson;

import thucthe.DiTich;

public class DiTichScraper implements Scraper {

	@Override
	public void scrape() throws IOException {
		Document doc=  Jsoup.connect("https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam").get();
		int i=0;
		doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
		
		ArrayList<DiTich> listDiaDiem=new ArrayList<DiTich>();
		
		for(Element row : doc.select("table.wikitable.sortable tr")) {
			i++;
			if(i==1) continue;
			else {
				
				if(row.select("td:nth-of-type(1)").text().equals("")) continue;
				else {
					if(row.select("td:nth-of-type(1)").text().length()<=3) {
						String name=row.select("td:nth-of-type(2)").text();
						String viTri=row.select("td:nth-of-type(3)").text();
						
						
							listDiaDiem.add(new DiTich(name,viTri));
							System.out.println(name+" "+ viTri +" " );
					}
					else {
						String name=row.select("td:nth-of-type(1)").text();
						String viTri=row.select("td:nth-of-type(2)").text();
						
						
							listDiaDiem.add(new DiTich(name,viTri));
							
					}
					
				}
			}
		}
		Gson gson=new Gson();
		String json=gson.toJson(listDiaDiem);
		FileOutputStream fos=null;
		fos=new FileOutputStream("DiTich.json");
		byte[] data =json.getBytes("utf8");
		fos.write(data);
		fos.close();
	}

}
