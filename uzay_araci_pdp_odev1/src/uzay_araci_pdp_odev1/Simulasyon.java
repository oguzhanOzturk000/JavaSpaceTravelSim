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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulasyon {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// Dosyalardaki veriler aşağıdaki listelere kaydedilir
		DosyaOkuma dosyaOku = new DosyaOkuma();
		List<Gezegen> gezegenler = dosyaOku.GezegenTxtOku();
		List<UzayAraci> uzayAraclari = dosyaOku.UzayAraciTxtOku();
		List<Kisi> kisiler = dosyaOku.KisiTxtOku();
				
		// Her döngüde uygulamanın ne kadar bekletileceği kullanıcıdan alınır
		System.out.print("Kaç milisaniye hızla simüle edilsin : ");
		Scanner o = new Scanner(System.in);
		String cevap = o.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY"); 
		int hiz = 0;
		try {
			hiz = Integer.parseInt(cevap);
		}catch(Exception e) {
			System.out.println("Bir tam sayı girin");
			return;
		}
		
		int sayac = 0; // Simülasyon bittiğinde toplam geçen saati gösterecek sayaç her döngüde 1 arttırılır.
		
		// simulasyonBittiMi fonksiyonunun kontrolünde while döngüsüne girilir . Fonksiyon true döndürünce simülasyon biter
		while(!simulasyonBittiMi(uzayAraclari, kisiler)) {
			
			// Java'da System("CLS") benzeri bir komut bulamadığımdan for döngüsü yardımıyla boşluk bıraktırdım
			for (int i = 0; i < 70; i++) {
	            System.out.println();
	        }
			
			// Listelerdeki nesneler için zaman ilerletilir
			uzayAraclari = Zaman.AracBirSaatGit(kisiler, gezegenler, uzayAraclari);
			gezegenler = Zaman.GezegenBirSaatGit(kisiler, gezegenler, uzayAraclari);
			kisiler= Zaman.KisiBirSaatGit(kisiler, gezegenler, uzayAraclari);
			
			
			// Zamanı güncellenmiş listeler ekranda gösterilir
			ekranaYaz(kisiler, gezegenler, uzayAraclari);
			
			
			System.out.print("\n");
			
			
			// Başta kullanıcıdan alınan değer kadar milisaniye boyunca bekletilir
			try {
				Thread.sleep(hiz);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sayac++;
		}
		
		//Simülsayon bitince sayaç yardımıyla ekrana geçen toplam süre verilir
		System.out.println("\nSimülasyon Bitti");
		System.out.println("Geçen süre : " + sayac);

	}
	
	// Verilen araç listesindeki araçları döngüye sokar durumu bekliyor veya yolda olan bir araç gördüğü an false dödürür ve devamını kontrol etmez
	// Eğer Bekliyor ya da Yolda durumunda hiç biraraç bulamamışsa true verir ve döngü biter.
	private static boolean simulasyonBittiMi(List<UzayAraci> uzayAraclari ,List<Kisi> kisiler) {
		for(UzayAraci a : uzayAraclari) {
			if(a.durumuKontrolEt(kisiler).equals("Bekliyor") || a.durumuKontrolEt(kisiler).equals("Yolda")) {return false;}
		}
		return true;
	}
	
	// Veriler istenen formatta ekrana yazdırılır
	private static void ekranaYaz(List<Kisi> kisiler ,List<Gezegen> gezegenler,List<UzayAraci> uzayAraclari) {
		System.out.println("Gezegenler:");
		System.out.print("                ");
		for(Gezegen g : gezegenler) {
			System.out.print("---"+g.isimAl()+"---         ");
		}
		System.out.println("");
		System.out.print("Tarih          ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY"); 
		for(Gezegen g : gezegenler) {
			System.out.print(g.tarihAl().format(formatter)+"       ");
		}
		System.out.println("");
		System.out.print("Nufus              ");
		for(Gezegen g : gezegenler) {
			System.out.print(g.nufusVer(kisiler,uzayAraclari)+"                ");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		
		System.out.println("Uzay Araclari:");
		System.out.println("Arac Adi        Durum      Cikis    Varis          Hedefe Kalan Saat        Hedefe Varacağı Tarih");
		for(UzayAraci a : uzayAraclari) {
			if(a.durumuKontrolEt(kisiler).equals("Imha")) {
				System.out.println(a.aracIsimVer()+"              "+a.durumuKontrolEt(kisiler)+"        "+a.cikisVer().isimAl()+"       "+a.varisVer().isimAl()+"            "+"---"+"                      "+"---");
			}else if(a.durumuKontrolEt(kisiler) == "Bekliyor") {
				System.out.println(a.aracIsimVer()+"              "+a.durumuKontrolEt(kisiler)+"    "+a.cikisVer().isimAl()+"       "+a.varisVer().isimAl()+"            "+a.kalanSaat()+"                    "+a.VarisTarihiVer().format(formatter));
			}else if(a.durumuKontrolEt(kisiler) == "Yolda"){
				System.out.println(a.aracIsimVer()+"              "+a.durumuKontrolEt(kisiler)+"       "+a.cikisVer().isimAl()+"       "+a.varisVer().isimAl()+"            "+a.kalanSaat()+"                       "+a.VarisTarihiVer().format(formatter));
			}else{
				System.out.println(a.aracIsimVer()+"              "+a.durumuKontrolEt(kisiler)+"       "+a.cikisVer().isimAl()+"       "+a.varisVer().isimAl()+"            "+a.kalanSaat()+"                        "+a.VarisTarihiVer().format(formatter));
			}
		}
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		
	}
	
	
	
	
	
	
	
	
}
