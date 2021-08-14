package com.bryanhusna.golek;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HasilPencarianAdapter extends RecyclerView.Adapter<HasilPencarianAdapter.ViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public HasilPencarianAdapter(Context context, Cursor cursor){
        this.mContext = context;
        this.mCursor = cursor;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        private TextView hasilPencarianTextView;
        private ImageView ikonBahasa;
        private String[] columnSemua = {"_ID", DaftarKataSQLiteHelper.COLUMN_INDONESIA, DaftarKataSQLiteHelper.COLUMN_NGOKO, DaftarKataSQLiteHelper.COLUMN_KRAMA, DaftarKataSQLiteHelper.COLUMN_KRAMA_INGGIL};
        private SQLiteDatabase database = new DaftarKataSQLiteHelper(mContext).getReadableDatabase();


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hasilPencarianTextView = itemView.findViewById(R.id.hasil_pencarian_textview);
            ikonBahasa = itemView.findViewById(R.id.ikon_bahasa_listview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String kriteria;
            Cursor cursorCari;

            Intent intent = new Intent(mContext, DetailHasilPencarianActivity.class);

            mCursor.moveToPosition(getAdapterPosition());
            int tipe = mCursor.getInt(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_TIPE));
            int urutan = mCursor.getInt(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_URUTAN));

            String where = ("_ID = ?");
            String[] seleksiArgs = {Integer.toString(urutan)};
            Cursor cursor = database.query(DaftarKataSQLiteHelper.TABLE_NAME, columnSemua, where, seleksiArgs, null, null, null);
            cursor.moveToFirst();
            String indonesia = cursor.getString(cursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_INDONESIA));;
            String ngoko = cursor.getString(cursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_NGOKO));
            String krama = cursor.getString(cursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_KRAMA));
            String kramaInggil = cursor.getString(cursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_KRAMA_INGGIL));

            intent.putExtra("indonesia", indonesia);
            intent.putExtra("ngoko", ngoko);
            intent.putExtra("krama", krama);
            intent.putExtra("krama_inggil", kramaInggil);
            intent.putExtra("tipe", tipe);
            intent.putExtra("urutan", urutan);
            mContext.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public HasilPencarianAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hasil_pencarian_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HasilPencarianAdapter.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.hasilPencarianTextView.setText(mCursor.getString(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_SEMUA)));
        switch(mCursor.getInt(mCursor.getColumnIndexOrThrow(DaftarKataSQLiteHelper.COLUMN_TIPE))){
            case 1:
                holder.ikonBahasa.setImageResource(R.drawable.ic_ikonindonesia);
                break;
            case 2:
                holder.ikonBahasa.setImageResource(R.drawable.ic_ikonngoko);
                break;
            case 3:
                holder.ikonBahasa.setImageResource(R.drawable.ic_ikonkrama);
                break;
            case 4:
                holder.ikonBahasa.setImageResource(R.drawable.ic_ikonkramainggil);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}
