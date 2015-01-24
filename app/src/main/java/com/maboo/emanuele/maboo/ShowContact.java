package com.maboo.emanuele.maboo;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ShowContact extends Activity implements OnClickListener {
    private Button btnContatti;
    private Button provabott;
    private final int PICK = 1;
    public Contatto c;

    ArrayList<Contatto> contatti = new ArrayList<Contatto>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        btnContatti = (Button) findViewById(R.id.importaContatti);
        btnContatti.setOnClickListener(this);
        provabott= (Button) findViewById(R.id.provabott);
        provabott.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String j="";
        if (v.equals(provabott))
        {
            j=" "+"i";
            Toast.makeText(getApplicationContext(), "provabott", Toast.LENGTH_SHORT).show();
            generateNoteOnSD("mioFileRubrica", j);
        }
        else
        {
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            phones.moveToFirst();
            while (phones.moveToNext()) {
                String i = "1";
                String id = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                c = new Contatto(id, name, phoneNumber);
                contatti.add(c);
                Toast.makeText(getApplicationContext(), c.nome(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "ho importato:" + contatti.size() + " contatti", Toast.LENGTH_SHORT).show();
                String contact = id + " " + name + " " + phoneNumber;
                generateNoteOnSD("mioFileRubrica", contact);
            }
            phones.close();

        }
    }





    public void generateNoteOnSD(String sFileName, String sBody)
    {

         File sd = Environment.getExternalStorageDirectory();
         File f = new File(sd, sFileName);
        String path=sd.getPath().toString();
       // Toast.makeText(getApplicationContext(),path , Toast.LENGTH_SHORT).show();

            FileWriter fw = null;
            BufferedWriter bw = null;
            try{
                fw = new FileWriter(f, true);
                bw = new BufferedWriter(fw);
                bw.write(sBody+'\n');
                bw.close();
                fw.close();
                bw.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
    }

}
