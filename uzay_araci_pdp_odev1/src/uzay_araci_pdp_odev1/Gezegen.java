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
import java.util.List;

public class Gezegen {
	
	private int GecenToplamSaat;
	private String Gezegen_Adi;
	private int nufus;
	private int Gunun_kac_saat_oldugu;
	private LocalDate Gezegendeki_tarih;
	
	//String olarak verilen tarihi içerisinde düzenleyip atayan Constructor
	public Gezegen(String gezegenAd, int gununSaati, String gezegenTarih) {
		this.Gezegen_Adi = gezegenAd;
		this.GecenToplamSaat = 0;
		this.Gunun_kac_saat_oldugu = gununSaati;
		
		String[] tarih = gezegenTarih.split("\\.");		
		this.Gezegendeki_tarih = LocalDate.of(Integer.parseInt(tarih[2]),Integer.parseInt(tarih[1]) ,Integer.parseInt(tarih[0]));
	}
	
	// Tarih bilgisini direkt LocalDate olarak alan Constructor
	public Gezegen(String gezegenAd, int gununSaati, LocalDate gezegenTarih) {
		this.Gezegen_Adi = gezegenAd;
		this.GecenToplamSaat = 0;
		this.Gunun_kac_saat_oldugu = gununSaati;
		this.Gezegendeki_tarih = gezegenTarih;
	}
	
	
	// Parametre olarak aldığı araçlar listesinden varış gezegeni bu gezegen olanların durumu Vardı ise veya çıkış gezegeni bu olanların durumu Bekliyor ise aracın nufusunu nufus değişkenine ekler
	public int nufusVer(List<Kisi> kisiler ,List<UzayAraci> uzayAraclari) {
		int Nufus=0;
		for(UzayAraci a : uzayAraclari) {
			if(a.durumuKontrolEt(kisiler).equals("Vardi") && a.varisVer().Gezegen_Adi.equals(this.Gezegen_Adi)) {
				Nufus += a.aractakiKisiSayisi(kisiler);
			}
			if(a.durumuKontrolEt(kisiler).equals("Bekliyor")  &&  a.cikisVer().Gezegen_Adi.equals(this.Gezegen_Adi)) {
				Nufus += a.aractakiKisiSayisi(kisiler);
			}
		}
		this.nufus = Nufus;
		return Nufus;
	}
	
	// Geçen toplam saat 1 arttırılır
	//Geçen toplam saat gün sayısının herhangi bir katına eşitse bir gün olmuş demektir plusDays fonksiyonu ile tarih 1 gün arttırılır
	public void BirSaatIlerle() {
		GecenToplamSaat++;
		if(GecenToplamSaat % Gunun_kac_saat_oldugu == 0) {
			Gezegendeki_tarih = Gezegendeki_tarih.plusDays(1);
		}
	}
	
	// Bir gezegenden birden fazla gelip giden araç olduğu ve bu gezegenlerin araç sınıfndan güncellendiği için saatlerin doğru güncellenmesi için kopyalama fonksiyonuna ihtiyaç vardır
	public Gezegen kopyala() {
		return new Gezegen(Gezegen_Adi,Gunun_kac_saat_oldugu,Gezegendeki_tarih);
	}
	
	public String isimAl() {
		return Gezegen_Adi;
	}

	public int gunSaatiAl() {
		return Gunun_kac_saat_oldugu;
	}
	
	public LocalDate tarihAl() {
		return Gezegendeki_tarih;
	}
	
	public int gecenSaatVer() {
		return GecenToplamSaat;
	}
	
}
