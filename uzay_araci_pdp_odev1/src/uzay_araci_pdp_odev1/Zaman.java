/**
*
* @author Oğuzhan Öztürk oguzhan.ozturk15@ogr.sakarya.edu.tr
* @since 21.04.2025
* <p>
* 2. Sınıf 1C
* </p>
*/


package uzay_araci_pdp_odev1;

import java.util.List;

public class Zaman {

	public Zaman() {
	}
	
	// Döngü yardımıyla gezegen listesindeki her nesneyi bir saat ilerleten fonksiyon ile ilerletir.
	public static List<Gezegen> GezegenBirSaatGit(List<Kisi> kisiler, List<Gezegen> gezegenler,List<UzayAraci> uzayAraclari) {
		for(Gezegen g : gezegenler) {
			g.BirSaatIlerle();
		}
		return gezegenler;
	}
	
	// Döngü yardımıyla kişi listesindeki her nesneyi bir saat ilerleten fonksiyon ile ilerletir.
	public static List<Kisi> KisiBirSaatGit(List<Kisi> kisiler, List<Gezegen> gezegenler,List<UzayAraci> uzayAraclari) {
		for(Kisi k : kisiler) {
			k.BirSaatIlerle();
		}
		return kisiler;
	}
	
	// Döngü yardımıyla araç listesindeki her nesnenin durumunu günceller ve bir saat ilerleten fonksiyon ile ilerletir.
	public static List<UzayAraci> AracBirSaatGit(List<Kisi> kisiler, List<Gezegen> gezegenler,List<UzayAraci> uzayAraclari) {
		for(UzayAraci a : uzayAraclari) {
			a.durumuKontrolEt(kisiler);
			a.BirSaatIlerle();
		}
		return uzayAraclari;
	}
	
	
}
