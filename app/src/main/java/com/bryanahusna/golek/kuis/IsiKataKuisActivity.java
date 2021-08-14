package com.bryanahusna.golek.kuis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bryanahusna.golek.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class IsiKataKuisActivity extends AppCompatActivity {
    private int skorIsiKata = 0;
    private int statusKlikJawaban = 0;
    HashMap<String, String> kumpulanPertanyaan;
    ArrayList pertanyaanKini = new ArrayList(5);
    ArrayList jawabanKini;
    int noPertanyaan = 1;
    int statusNo = 0;
    TextView pertanyaan;
    TextView jawaban1;
    TextView jawaban2;
    TextView jawaban3;
    TextView jawaban4;
    CardView lanjutIsi;
    View backgroundIsiKata;
    TextView hasilKebenaran;
    TextView jawabanAsli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_kata_kuis);
        lanjutIsi = findViewById(R.id.lanjut_isiKata);
        backgroundIsiKata = findViewById(R.id.background_hasil_isiKata);
        hasilKebenaran = findViewById(R.id.hasil_pilihan_isiKata);
        jawabanAsli = findViewById(R.id.jawaban_asli_isiKata);

        Intent intent = getIntent();
        pertanyaan = findViewById(R.id.pertanyaan);
        jawaban1 = findViewById(R.id.jawaban1);
        jawaban2 = findViewById(R.id.jawaban2);
        jawaban3 = findViewById(R.id.jawaban3);
        jawaban4 = findViewById(R.id.jawaban4);
        if (savedInstanceState == null) {
            kumpulanPertanyaan = new HashMap<String, String>();
            switch (intent.getIntExtra("level", 0)) {
                case 1:
                    level1();
                    shufflePertanyaan();
                    noPertanyaan = Integer.parseInt(pertanyaanKini.get(statusNo).toString());
                    shuffleJawaban();
                    setPertanyaan();
                    break;
                case 2:
                    level2();
                    shufflePertanyaan();
                    noPertanyaan = Integer.parseInt(pertanyaanKini.get(statusNo).toString());
                    shuffleJawaban();
                    setPertanyaan();
                    break;
                case 3:
                    level3();
                    shufflePertanyaan();
                    noPertanyaan = Integer.parseInt(pertanyaanKini.get(statusNo).toString());
                    shuffleJawaban();
                    setPertanyaan();
                    break;
                case 4:
                    level4();
                    shufflePertanyaan();
                    noPertanyaan = Integer.parseInt(pertanyaanKini.get(statusNo).toString());
                    shuffleJawaban();
                    setPertanyaan();
                    break;
            }
        } else {
            skorIsiKata = savedInstanceState.getInt("skorSekarang");
            kumpulanPertanyaan = (HashMap<String, String>) savedInstanceState.getSerializable("kumpulanPertanyaan");
            pertanyaanKini = savedInstanceState.getIntegerArrayList("urutanPertanyaan");
            statusNo = savedInstanceState.getInt("nomorSekarang");

            jawabanKini = savedInstanceState.getStringArrayList("jawabanKini");
            noPertanyaan = Integer.parseInt(pertanyaanKini.get(statusNo).toString());
            setPertanyaan();
        }

    }

    private void level1() {
        kumpulanPertanyaan.put("pertanyaan1", "Aku .... gudeg karo kanca-kancaku");
        kumpulanPertanyaan.put("benar1", "mangan");
        kumpulanPertanyaan.put("salahA1", "ngombe");
        kumpulanPertanyaan.put("salahB1", "mlaku");
        kumpulanPertanyaan.put("salahC1", "turu");

        kumpulanPertanyaan.put("pertanyaan2", "Budi .... susu sadurunge sekolah");
        kumpulanPertanyaan.put("benar2", "ngombe");
        kumpulanPertanyaan.put("salahA2", "nguncal");
        kumpulanPertanyaan.put("salahB2", "leren");
        kumpulanPertanyaan.put("salahC2", "mlayu");

        kumpulanPertanyaan.put("pertanyaan3", "Ani .... sekolah bareng kancane");
        kumpulanPertanyaan.put("benar3", "mangkat");
        kumpulanPertanyaan.put("salahA3", "njukuk");
        kumpulanPertanyaan.put("salahB3", "ngenehake");
        kumpulanPertanyaan.put("salahC3", "numpak");

        kumpulanPertanyaan.put("pertanyaan4", "Siswadi nganggo klambi warnane ....");
        kumpulanPertanyaan.put("benar4", "ijo");
        kumpulanPertanyaan.put("salahA4", "sikil");
        kumpulanPertanyaan.put("salahB4", "tangan");
        kumpulanPertanyaan.put("salahC4", "weteng");

        kumpulanPertanyaan.put("pertanyaan5", "Basuki ngombe .... supaya sehat");
        kumpulanPertanyaan.put("benar5", "jamu");
        kumpulanPertanyaan.put("salahA5", "bakmi");
        kumpulanPertanyaan.put("salahB5", "tahu");
        kumpulanPertanyaan.put("salahC5", "gudeg");
    }

    private void level2() {
        kumpulanPertanyaan.put("pertanyaan1", "Bapak wis .... sego durung?");
        kumpulanPertanyaan.put("benar1", "dhahar");
        kumpulanPertanyaan.put("salahA1", "sare");
        kumpulanPertanyaan.put("salahB1", "mlampah");
        kumpulanPertanyaan.put("salahC1", "nitih");

        kumpulanPertanyaan.put("pertanyaan2", "Panjenengan arep .... ing kamar ora?");
        kumpulanPertanyaan.put("benar2", "sare");
        kumpulanPertanyaan.put("salahA2", "kondur");
        kumpulanPertanyaan.put("salahB2", "maringi");
        kumpulanPertanyaan.put("salahC2", "nyuwun");

        kumpulanPertanyaan.put("pertanyaan3", "Panjenengan .... bakso ora?");
        kumpulanPertanyaan.put("benar3", "mundhut");
        kumpulanPertanyaan.put("salahA3", "cariyos");
        kumpulanPertanyaan.put("salahB3", "ngapunten");
        kumpulanPertanyaan.put("salahC3", "maos");

        kumpulanPertanyaan.put("pertanyaan4", "Bapak .... ing rapat ora?");
        kumpulanPertanyaan.put("benar4", "rawuh");
        kumpulanPertanyaan.put("salahA4", "nanem");
        kumpulanPertanyaan.put("salahB4", "wungu");
        kumpulanPertanyaan.put("salahC4", "nyuwun");

        kumpulanPertanyaan.put("pertanyaan5", "Bapak kagungan klambi warnane ....");
        kumpulanPertanyaan.put("benar5", "ijo");
        kumpulanPertanyaan.put("salahA5", "ijem");
        kumpulanPertanyaan.put("salahB5", "abrit");
        kumpulanPertanyaan.put("salahC5", "cemeng");
    }

    private void level3() {
        kumpulanPertanyaan.put("pertanyaan1", "Kala wingi sampeyan .... wonten pundi?");
        kumpulanPertanyaan.put("benar1", "kesah");
        kumpulanPertanyaan.put("salahA1", "tindak");
        kumpulanPertanyaan.put("salahB1", "lunga");
        kumpulanPertanyaan.put("salahC1", "miyos");

        kumpulanPertanyaan.put("pertanyaan2", ".... kula wonten ing Jogja");
        kumpulanPertanyaan.put("benar2", "griya");
        kumpulanPertanyaan.put("salahA2", "dalem");
        kumpulanPertanyaan.put("salahB2", "omah");
        kumpulanPertanyaan.put("salahC2", "suku");

        kumpulanPertanyaan.put("pertanyaan3", "Budi .... dhateng sekolah");
        kumpulanPertanyaan.put("benar3", "mlampah");
        kumpulanPertanyaan.put("salahA3", "mlaku");
        kumpulanPertanyaan.put("salahB3", "sare");
        kumpulanPertanyaan.put("salahC3", "neda");

        kumpulanPertanyaan.put("pertanyaan4", "Sampeyan .... oleh-oleh boten?");
        kumpulanPertanyaan.put("benar4", "tumbas");
        kumpulanPertanyaan.put("salahA4", "mundhut");
        kumpulanPertanyaan.put("salahB4", "tuku");
        kumpulanPertanyaan.put("salahC4", "dhahar");

        kumpulanPertanyaan.put("pertanyaan5", "Paijo .... ayam kalih");
        kumpulanPertanyaan.put("benar5", "gadhah");
        kumpulanPertanyaan.put("salahA5", "kagungan");
        kumpulanPertanyaan.put("salahB5", "duwe");
        kumpulanPertanyaan.put("salahC5", "mlampah");
    }

    private void level4(){
        kumpulanPertanyaan.put("pertanyaan1", "Paijo .... dhateng sekolah");
        kumpulanPertanyaan.put("benar1", "tindak");
        kumpulanPertanyaan.put("salahA1", "lunga");
        kumpulanPertanyaan.put("salahB1", "kesah");
        kumpulanPertanyaan.put("salahC1", "dhahar");

        kumpulanPertanyaan.put("pertanyaan2", ".... panjenengan wonten pundi?");
        kumpulanPertanyaan.put("benar2", "dalem");
        kumpulanPertanyaan.put("salahA2", "omah");
        kumpulanPertanyaan.put("salahB2", "griya");
        kumpulanPertanyaan.put("salahC2", "panggonan");

        kumpulanPertanyaan.put("pertanyaan3", "Bapak tindak dhateng kantor .... sepedha motor");
        kumpulanPertanyaan.put("benar3", "nitih");
        kumpulanPertanyaan.put("salahA3", "mlaku");
        kumpulanPertanyaan.put("salahB3", "numpak");
        kumpulanPertanyaan.put("salahC3", "dhahar");

        kumpulanPertanyaan.put("pertanyaan4", "Joko .... Pak Guru tugas Matematika");
        kumpulanPertanyaan.put("benar4", "nyaosi");
        kumpulanPertanyaan.put("salahA4", "menehi");
        kumpulanPertanyaan.put("salahB4", "ngomong");
        kumpulanPertanyaan.put("salahC4", "takon");

        kumpulanPertanyaan.put("pertanyaan5", "Bapak kagungan sepatu werninipun ....");
        kumpulanPertanyaan.put("benar5", "ijem");
        kumpulanPertanyaan.put("salahA5", "ijo");
        kumpulanPertanyaan.put("salahB5", "abang");
        kumpulanPertanyaan.put("salahC5", "ireng");
    }

    private void shufflePertanyaan() {
        jawabanKini = new ArrayList();
        pertanyaanKini.add(1);
        pertanyaanKini.add(2);
        pertanyaanKini.add(3);
        pertanyaanKini.add(4);
        pertanyaanKini.add(5);
        Collections.shuffle(pertanyaanKini);
    }

    private void shuffleJawaban() {
        jawabanKini = new ArrayList(5);
        jawabanKini.add(kumpulanPertanyaan.get("benar" + noPertanyaan));
        jawabanKini.add(kumpulanPertanyaan.get("salahA" + noPertanyaan));
        jawabanKini.add(kumpulanPertanyaan.get("salahB" + noPertanyaan));
        jawabanKini.add(kumpulanPertanyaan.get("salahC" + noPertanyaan));
        Collections.shuffle(jawabanKini);
    }

    private void setPertanyaan() {
        pertanyaan.setText(kumpulanPertanyaan.get("pertanyaan" + noPertanyaan));
        jawaban1.setText(jawabanKini.get(0).toString());
        jawaban2.setText(jawabanKini.get(1).toString());
        jawaban3.setText(jawabanKini.get(2).toString());
        jawaban4.setText(jawabanKini.get(3).toString());

        if (jawaban1.getText() == kumpulanPertanyaan.get("benar" + noPertanyaan)) {
            jawaban1.setTag("Benar");
        } else jawaban1.setTag("Salah");

        if (jawaban2.getText() == kumpulanPertanyaan.get("benar" + noPertanyaan)) {
            jawaban2.setTag("Benar");
        } else jawaban2.setTag("Salah");

        if (jawaban3.getText() == kumpulanPertanyaan.get("benar" + noPertanyaan)) {
            jawaban3.setTag("Benar");
        } else jawaban3.setTag("Salah");

        if (jawaban4.getText() == kumpulanPertanyaan.get("benar" + noPertanyaan)) {
            jawaban4.setTag("Benar");
        } else jawaban4.setTag("Salah");
    }

    public void onJawaban1Click(View view) {
        if(statusKlikJawaban == 0){
        if (view.getTag() == "Benar") {
            statusNo++;
            if (statusNo > 4) {
                skorIsiKata++;
                Intent intent = new Intent(this, PenutupIsiKataKuisActivity.class);
                intent.putExtra("skorIsiKata", skorIsiKata);
                startActivity(intent);
                finish();
            } else {
                skorIsiKata++;
                backgroundIsiKata.setVisibility(View.VISIBLE);
                backgroundIsiKata.setBackgroundColor(ContextCompat.getColor(this, R.color.primaryLightColor));
                lanjutIsi.setVisibility(View.VISIBLE);
                lanjutIsi.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
                hasilKebenaran.setVisibility(View.VISIBLE);
                hasilKebenaran.setText("BENAR");
                hasilKebenaran.setTextColor(ContextCompat.getColor(this, R.color.secondaryTextColor));
            }
        } else {
            backgroundIsiKata.setVisibility(View.VISIBLE);
            backgroundIsiKata.setBackgroundColor(ContextCompat.getColor(this, R.color.secondaryLightColor));
            lanjutIsi.setVisibility(View.VISIBLE);
            lanjutIsi.setCardBackgroundColor(ContextCompat.getColor(this, R.color.secondaryDarkColor));
            hasilKebenaran.setVisibility(View.VISIBLE);
            hasilKebenaran.setText("KURANG TEPAT");
            hasilKebenaran.setTextColor(ContextCompat.getColor(this, R.color.secondaryTextColor));
            jawabanAsli.setVisibility(View.VISIBLE);
            String jawaban = "Jawaban yang Benar " + kumpulanPertanyaan.get("benar" + noPertanyaan);
            jawabanAsli.setText(jawaban);
            statusNo++;
            if (statusNo > 4) {
                Intent intent = new Intent(this, PenutupIsiKataKuisActivity.class);
                intent.putExtra("skorIsiKata", skorIsiKata);
                startActivity(intent);
                finish();
            }
        } }
        statusKlikJawaban = 1;
    }

    public void lanjutIsiKata(View view) {
        noPertanyaan = Integer.parseInt(pertanyaanKini.get(statusNo).toString());
        shuffleJawaban();
        setPertanyaan();
        lanjutIsi.setVisibility(View.INVISIBLE);
        jawabanAsli.setVisibility(View.INVISIBLE);
        backgroundIsiKata.setVisibility(View.INVISIBLE);
        hasilKebenaran.setVisibility(View.INVISIBLE);
        statusKlikJawaban = 0;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("nomorSekarang", statusNo);
        outState.putIntegerArrayList("urutanPertanyaan", pertanyaanKini);
        outState.putStringArrayList("jawabanKini", jawabanKini);
        outState.putSerializable("kumpulanPertanyaan", kumpulanPertanyaan);
        outState.putInt("skorSekarang", skorIsiKata);
    }
}
