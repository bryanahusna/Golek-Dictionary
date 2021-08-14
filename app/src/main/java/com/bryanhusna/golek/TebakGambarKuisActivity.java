package com.bryanhusna.golek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TebakGambarKuisActivity extends AppCompatActivity {
    private int skorIsiKata = 0;
    private int statusKlikJawaban = 0;
    HashMap<String, String> kumpulanPertanyaan;
    ArrayList pertanyaanKini = new ArrayList(5);
    ArrayList jawabanKini;
    int noPertanyaan = 1;
    int statusNo = 0;
    private ImageView gambarPertanyaan;
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
        setContentView(R.layout.activity_tebak_gambar_kuis);
        lanjutIsi = findViewById(R.id.lanjut_isiKata);
        backgroundIsiKata = findViewById(R.id.background_hasil_isiKata);
        hasilKebenaran = findViewById(R.id.hasil_pilihan_isiKata);
        jawabanAsli = findViewById(R.id.jawaban_asli_isiKata);

        Intent intent = getIntent();
        gambarPertanyaan = findViewById(R.id.pertanyaan);
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
        kumpulanPertanyaan.put("pertanyaan1", "ic_nulis1");
        kumpulanPertanyaan.put("benar1", "nulis");
        kumpulanPertanyaan.put("salahA1", "ngapura");
        kumpulanPertanyaan.put("salahB1", "nyekel");
        kumpulanPertanyaan.put("salahC1", "golek");

        kumpulanPertanyaan.put("pertanyaan2", "ic_golek2");
        kumpulanPertanyaan.put("benar2", "golek");
        kumpulanPertanyaan.put("salahA2", "tuku");
        kumpulanPertanyaan.put("salahB2", "numpak");
        kumpulanPertanyaan.put("salahC2", "budhal");

        kumpulanPertanyaan.put("pertanyaan3", "ic_3tuku");
        kumpulanPertanyaan.put("benar3", "tuku");
        kumpulanPertanyaan.put("salahA3", "njaluk");
        kumpulanPertanyaan.put("salahB3", "ngenehake");
        kumpulanPertanyaan.put("salahC3", "takon");

        kumpulanPertanyaan.put("pertanyaan4", "ic_4turu");
        kumpulanPertanyaan.put("benar4", "turu");
        kumpulanPertanyaan.put("salahA4", "tuku");
        kumpulanPertanyaan.put("salahB4", "mangkat");
        kumpulanPertanyaan.put("salahC4", "mangan");

        kumpulanPertanyaan.put("pertanyaan5", "ic_5ijo");
        kumpulanPertanyaan.put("benar5", "ijo");
        kumpulanPertanyaan.put("salahA5", "abang");
        kumpulanPertanyaan.put("salahB5", "kuning");
        kumpulanPertanyaan.put("salahC5", "ireng");
    }

    private void level2() {
        kumpulanPertanyaan.put("pertanyaan1", "ic_1mlampah");
        kumpulanPertanyaan.put("benar1", "mlampah");
        kumpulanPertanyaan.put("salahA1", "mlayu");
        kumpulanPertanyaan.put("salahB1", "nedha");
        kumpulanPertanyaan.put("salahC1", "tilem");

        kumpulanPertanyaan.put("pertanyaan2", "ic_2siyang");
        kumpulanPertanyaan.put("benar2", "siyang");
        kumpulanPertanyaan.put("salahA2", "dalu");
        kumpulanPertanyaan.put("salahB2", "wingi");
        kumpulanPertanyaan.put("salahC2", "bengi");

        kumpulanPertanyaan.put("pertanyaan3", "ic_3remen");
        kumpulanPertanyaan.put("benar3", "remen");
        kumpulanPertanyaan.put("salahA3", "seneng");
        kumpulanPertanyaan.put("salahB3", "tumbas");
        kumpulanPertanyaan.put("salahC3", "mlayu");

        kumpulanPertanyaan.put("pertanyaan4", "ic_4griya");
        kumpulanPertanyaan.put("benar4", "griya");
        kumpulanPertanyaan.put("salahA4", "sekolah");
        kumpulanPertanyaan.put("salahB4", "omah");
        kumpulanPertanyaan.put("salahC4", "dalem");

        kumpulanPertanyaan.put("pertanyaan5", "ic_5cemeng");
        kumpulanPertanyaan.put("benar5", "cemeng");
        kumpulanPertanyaan.put("salahA5", "ijem");
        kumpulanPertanyaan.put("salahB5", "pethak");
        kumpulanPertanyaan.put("salahC5", "jene");
    }

    private void level3() {
        kumpulanPertanyaan.put("pertanyaan1", "ic_1pinarak");
        kumpulanPertanyaan.put("benar1", "pinarak");
        kumpulanPertanyaan.put("salahA1", "tindak");
        kumpulanPertanyaan.put("salahB1", "nitih");
        kumpulanPertanyaan.put("salahC1", "nyuwun");

        kumpulanPertanyaan.put("pertanyaan2", "ic_2paningal");
        kumpulanPertanyaan.put("benar2", "paningal");
        kumpulanPertanyaan.put("salahA2", "mustaka");
        kumpulanPertanyaan.put("salahB2", "talingan");
        kumpulanPertanyaan.put("salahC2", "lathi");

        kumpulanPertanyaan.put("pertanyaan3", "ic_3siram");
        kumpulanPertanyaan.put("benar3", "siram");
        kumpulanPertanyaan.put("salahA3", "mundhut");
        kumpulanPertanyaan.put("salahB3", "mlampah");
        kumpulanPertanyaan.put("salahC3", "ngasta");

        kumpulanPertanyaan.put("pertanyaan4", "ic_4arta");
        kumpulanPertanyaan.put("benar4", "arta");
        kumpulanPertanyaan.put("salahA4", "dhuwit");
        kumpulanPertanyaan.put("salahB4", "tiyang");
        kumpulanPertanyaan.put("salahC4", "rawuh");

        kumpulanPertanyaan.put("pertanyaan5", "ic_5sare");
        kumpulanPertanyaan.put("benar5", "sare");
        kumpulanPertanyaan.put("salahA5", "mlampah");
        kumpulanPertanyaan.put("salahB5", "mundhut");
        kumpulanPertanyaan.put("salahC5", "nitih");
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
        int resourceId = this.getResources().getIdentifier( kumpulanPertanyaan.get("pertanyaan" + noPertanyaan), "drawable", getPackageName());
        gambarPertanyaan.setImageResource(resourceId);
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
