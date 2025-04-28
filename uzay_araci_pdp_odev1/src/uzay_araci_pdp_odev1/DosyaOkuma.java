/**
*
* @author Oğuzhan Öztürk oguzhan.ozturk15@ogr.sakarya.edu.tr
* @since 21.04.2025
* <p>
* 2. Sınıf 1C
* </p>
*/


package uzay_araci_pdp_odev1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DosyaOkuma {

	private String AdresBase;
	
	public DosyaOkuma() {
		// anlık çalışan dosyanın adresini verir uzay.jar dist'te çalıştığı için dist adresi elde edilir
		// Txt dosyaları da dist'tedir.
		AdresBase = System.getProperty("user.dir"); 
	}
	
	public List<Kisi> KisiTxtOku () throws NumberFormatException, IOException {
		List<Kisi> kisiListesi = new ArrayList<>(); // sonda geri döndürülecek olan liste
		String kisiAdres = AdresBase + "\\Kisiler.txt";
		String satir;
		// Kişiler sınıfınfa kişinin bulunduğu araç da tutulduğu için araçlar listesi tekrar alınır ve eşleşen araç kişi constructor'una verilir.
		List<UzayAraci> araclar = UzayAraciTxtOku(); 
		UzayAraci arac = null ;
		try {
			BufferedReader okuyucu = new BufferedReader(new FileReader(kisiAdres));
			// dosya bitene kadar döngü devam eder
			while((satir = okuyucu.readLine()) != null) {
				if(!satir.isEmpty()) {
					// split fonksiyonu #'ler ile ayrılan verileri dizi şeklinde ayırır
					String[] okunanlar = satir.split("#");
					
					// Kişinin bulunduğu uzay aracının ismiyle okunan araçlardan uyuşan bulunur
					for(UzayAraci a : araclar ) {
						if(a.aracIsimVer().equals(okunanlar[3]) ) {
							arac = a;
						}
					}
					
					// yeni kişi nesnesi oluşturulur ve listeye eklenir
					Kisi yeniKisi = new Kisi(okunanlar[0],Integer.parseInt(okunanlar[1]), Integer.parseInt(okunanlar[2]),arac);
					kisiListesi.add(yeniKisi);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Kisiler.txt dosyası bulunamadı!");
		}catch (IOException e) {
			System.out.println("Kisiler okunamadı!");
		}
		
		// liste döner
		return kisiListesi;
	}
	
	// üsttekiyle benzer işlemler yapılır
	public List<UzayAraci> UzayAraciTxtOku() throws NumberFormatException, IOException {
		List<UzayAraci> aracListesi = new ArrayList<>();
		String aracAdres = AdresBase + "\\Araclar.txt";
		String satir;
		List<Gezegen> gezegenler = GezegenTxtOku();
		Gezegen cikisgezegeni = null;
		Gezegen varisgezegeni = null;
		try {
			BufferedReader okuyucu = new BufferedReader(new FileReader(aracAdres));
			while((satir = okuyucu.readLine()) != null ) {
				if(!satir.isEmpty()) {
					String[] okunanlar = satir.split("#");
				
					
					// varış ve çıkış gezegenleri isminden bulunup eşleştirilir ve sonrasında constructor'da yerine koyulur.
					for(Gezegen g : gezegenler) {
						if(g.isimAl().equals(okunanlar[1])) {
							// Aynı gezegene birden fazla araç gelip gidebileceği ve varış çıkış gezegenlerinin saatleri araçlardan güncellendiği için
							// Kopyası oluşturup kaydedilir
							cikisgezegeni = g.kopyala(); 
						}
						if(g.isimAl().equals(okunanlar[2])) {
							varisgezegeni = g.kopyala();
						}
					}
					
					UzayAraci yeniArac = new UzayAraci(okunanlar[0],cikisgezegeni,varisgezegeni,okunanlar[3], Integer.parseInt(okunanlar[4]));
					aracListesi.add(yeniArac);
				}
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("Araclar.txt dosyası bulunamadı!");
		}catch (IOException e) {
			System.out.println("Araclar okunamadı!");
		}
		
		
		return aracListesi;
	}
	
	// Araç ve kişinin okunmasından daha basittir çünkü diğer iki sınıfta üyesi yok
	public List<Gezegen> GezegenTxtOku() throws NumberFormatException, IOException {
		List<Gezegen> gezegenListesi = new ArrayList<>();
		String gezegenAdres = AdresBase + "\\Gezegenler.txt";
		String satir;
		BufferedReader okuyucu = null;
		try {
			okuyucu = new BufferedReader(new FileReader(gezegenAdres));
		} catch (FileNotFoundException e) {
			System.out.println("Gezegenler.txt dosyası bulunamadı!");
		}catch (IOException e) {
			System.out.println("Gezegenler okunamadı!");
		}
		while((satir = okuyucu.readLine()) != null ) {
			if(!satir.isEmpty()) {
				// split yardımıyla veriler alınır ve gerekli dönüşümler yapıldıktan sonra nesne oluşturulup dönecek olan listeye eklenir
				String[] okunanlar = satir.split("#");
				String gezegenAd = okunanlar[0];
				int gunKacSaat= Integer.parseInt(okunanlar[1]);
				String tarih = okunanlar[2];
				Gezegen yeniGezegen = new Gezegen(gezegenAd,gunKacSaat,tarih);
				gezegenListesi.add(yeniGezegen);
			}		
		}
		return gezegenListesi; // liste döner
	}
	
}
