/**
*
* @author Oğuzhan Öztürk oguzhan.ozturk15@ogr.sakarya.edu.tr
* @since 21.04.2025
* <p>
* 2. Sınıf 1C
* </p>
*/


package uzay_araci_pdp_odev1;

public class Kisi {
	
	private int GecenToplamSaat = 0;

	private String isim;
	private int yas;
	private int kalan_omur;
	private UzayAraci bulundugu_uzay_aracı_adi;
	
	public Kisi(String isim, int yas, int kalanomur, UzayAraci bulunduguArac) {
		this.bulundugu_uzay_aracı_adi = bulunduguArac;
		this.isim = isim;
		this.kalan_omur = kalanomur;
		this.yas = yas;
		this.GecenToplamSaat = 0;
	}
	
	// Gecen toplam saati arttırır ve kişi yaşıyorsa kalan ömrünü azatır
	public void BirSaatIlerle() {
		GecenToplamSaat++;	
		if(yasiyorMu()) {kalan_omur = kalan_omur-1;}
	}
	
	// kalan omur 0 değilse 1 döndürür
	public boolean yasiyorMu() {
		return kalanomurver() != 0;
	}
	
	public String isimVer() {
		return isim;
	}
	
	public UzayAraci aracVer() {
		return bulundugu_uzay_aracı_adi;
	}
	
	public int kalanomurver() {return kalan_omur;}
	
	public int gecenSaatVer() {
		return GecenToplamSaat;
	}
	
}
