/**
*
* @author Oğuzhan Öztürk oguzhan.ozturk15@ogr.sakarya.edu.tr
* @since 21.04.2025
* <p>
* 2. Sınıf 1C
* </p>
*/

package uzay_araci_pdp_odev1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UzayAraci {
	
	private  int GecenToplamSaat;
	private String Uzay_araci_adi;
	private Gezegen cikis_gezegeni;
	private Gezegen varis_gezegeni;
	private LocalDate cikis_tarihi;
	private LocalDate varis_tarihi;
	private int mesafe_saat_olarak;
	private String durum;
	
	
	public UzayAraci(String aracAdi, Gezegen cikisgezegeni, Gezegen varisgezegeni, String cikistarihi, int mesafesaat) {
		this.cikis_gezegeni = cikisgezegeni;
		this.mesafe_saat_olarak = mesafesaat;
		this.Uzay_araci_adi = aracAdi;
		this.varis_gezegeni = varisgezegeni;
		this.GecenToplamSaat = 0;

		//String olarak aldığı tarihi düzenleyip LocalDate olarak kaydeder.
		String[] tarih = cikistarihi.split("\\.");
		this.cikis_tarihi = LocalDate.of(Integer.parseInt(tarih[2]),Integer.parseInt(tarih[1]) ,Integer.parseInt(tarih[0]));	
		
		
		// Varış anında varış gezegenindeki tarih hesaplanır ve atanır
		int cikisaKadarGun = (int) ChronoUnit.DAYS.between(cikisgezegeni.tarihAl(), cikis_tarihi);
		int varisGezegenindeGecenGun = (cikisaKadarGun*cikis_gezegeni.gunSaatiAl() + mesafe_saat_olarak) / varis_gezegeni.gunSaatiAl();
		this.varis_tarihi =varis_gezegeni.tarihAl().plusDays(varisGezegenindeGecenGun);
	}
	
	// toplam saati arttırır çıkıi ve varış gezegenlerini de bir saat ilerletir
	// eğer yolda ise 1 saatlik mesafeyi azaltır
	public void BirSaatIlerle() {
		GecenToplamSaat++;
		varis_gezegeni.BirSaatIlerle();
		cikis_gezegeni.BirSaatIlerle();
		if(this.durum.equals("Yolda")) {mesafe_saat_olarak--;}
	}
	
	public String aracIsimVer() {
		return Uzay_araci_adi;
	}
	
	// Parametre olarak aldığı kişi listesinden bu araçta olan ve yaşayanları toplayıp araçtaki kişi sayısını hesaplar
	// Gezegenlerin nufusunu bulmakta ve imha durumunu tespit etmekte kullanılır
	public int aractakiKisiSayisi(List<Kisi> kisiler) {
		int sayi = 0;
		for(Kisi k : kisiler) {
			if(k.aracVer().aracIsimVer().equals(Uzay_araci_adi) && k.yasiyorMu()) {
				sayi++;
			}
		}
		return sayi;
	}
	
	
	// 
	public String durumuKontrolEt(List<Kisi> kisiler) {
		durum = "";
	    
		if (aractakiKisiSayisi(kisiler) == 0) {
	    	durum = "Imha";
	    }
		// Kalan saatin 0 olması ve varış tarihinin varış gezegeninin anlık tarihine eşit veya daha geç olmasını kontrol eder
		// ikisi de doğruysa durum vardıdır
		// sadece kalan saati kullansak da işlem doğru olur ancak saatlerin doğru ilerlediğini kontrol amaçlı tarih kontrolleri de eklenmiştir
		else if (kalanSaat() == 0 && (varis_gezegeni.tarihAl().isAfter(varis_tarihi) || varis_gezegeni.tarihAl().isEqual(varis_tarihi))) {
	    	durum = "Vardi";
	    }
	    // Çıkış gezegeninde çıkış tarihi gelmemişse bekliyordur
	    else if (cikis_gezegeni.tarihAl().isBefore(CikisTarihiVer())) {
	    	durum = "Bekliyor";
	    }
	    // Çıkış gezegeninde çıkış tarihi geçmiş ise ve durum vardı değil ise 
	    else if (cikis_gezegeni.tarihAl().isAfter(CikisTarihiVer()) || cikis_gezegeni.tarihAl().isEqual(CikisTarihiVer())) {
	        durum = "Yolda";
	    }
	    // araçta yaşayan kişi yoksa diğer durumlar farketmeksizin durum imha olur
	    
	    return durum;
	}
	
	public Gezegen cikisVer() {
		return cikis_gezegeni;
	}
	
	public Gezegen varisVer() {
		return varis_gezegeni;
	}
	
	public int kalanSaat() {
		return mesafe_saat_olarak;
	}
	
	public LocalDate CikisTarihiVer() {
		return cikis_tarihi;
	}
	
	public LocalDate VarisTarihiVer() {
		return varis_tarihi;
	}
	
	public int gecenSaatVer() {
		return GecenToplamSaat;
	}
}
