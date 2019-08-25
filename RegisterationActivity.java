package com.example.usman.virtualclinic;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONStringer;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static java.lang.System.in;

public class RegisterationActivity extends AppCompatActivity {

    String functionname;
    EditText etuname, etpassword, etemail, etliscenceno, etlocation, etphoneno, etDOB;

    String uname, password, email, liscenceno, location, phoneno, regtype, state, city, gender, education, dob, wcf;
    Button btnregister;
    ImageView btnimg;
    Spinner sp_Regtype, sp_state, sp_city, sp_gender, sp_education;
    LinearLayout linearLayout;
    ArrayList<String> type;
    ArrayAdapter<String> typeadopter;

    public static final int RequestPermissionCode = 1;

    ImageView img;

    private Bitmap bitmap;
    private static final int PICK_IMAGE = 1;

    ArrayList<String> alGender;
    ArrayAdapter<String> genderadopter;

    ArrayList<String> alexperience;
    ArrayAdapter<String> experienceadopter;

    ArrayList<String> alstates;
    ArrayAdapter<String> statesadopter;

    Hashtable<String, String> ht;
    ArrayList<String> alcities;
    ArrayAdapter<String> cityadopter;


    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        etuname = (EditText) findViewById(R.id.Regetusername);
        etpassword = (EditText) findViewById(R.id.Regetpassword);
        etemail = (EditText) findViewById(R.id.regetemail);
        etliscenceno = (EditText) findViewById(R.id.RegetLiscencee);
        etlocation = (EditText) findViewById(R.id.RegLocation);
        etphoneno = (EditText) findViewById(R.id.Regphoneno);
        etDOB = (EditText) findViewById(R.id.regDob);


        img = (ImageView) findViewById(R.id.imgreg);

        btnregister = (Button) findViewById(R.id.btnsignupReg);
        btnimg = (ImageView) findViewById(R.id.btnimgReg);

        sp_Regtype = (Spinner) findViewById(R.id.Regtypespinner);
        sp_state = (Spinner) findViewById(R.id.Regstate);
        sp_city = (Spinner) findViewById(R.id.Regcity);
        sp_gender = (Spinner) findViewById(R.id.RegGender);
        sp_education = (Spinner) findViewById(R.id.RegExperience);

        linearLayout = (LinearLayout) findViewById(R.id.fordoctor);

        type = new ArrayList<String>();
        spinnertype();
        alGender = new ArrayList<String>();
        GenderAdopter();

        alexperience = new ArrayList<String>();
        ExperienceAdopter();

        alstates = new ArrayList<String>();
        Addstates();

        AddinHashtable();


        pd= new ProgressDialog(RegisterationActivity.this);
        pd.setMessage("Please Wait...");
        pd.setTitle("Registeration is being process");

        sp_Regtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = sp_Regtype.getItemAtPosition(i).toString();
                if (s == "Doctor") {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = sp_state.getItemAtPosition(i).toString();
                if (s != "Select State") {
                    alcities = new ArrayList<String>();
                    Addcities(s);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                pd.show();
                uname = etuname.getText().toString();
                password = etpassword.getText().toString();
                liscenceno = etliscenceno.getText().toString();
                location = etlocation.getText().toString();
                email = etemail.getText().toString();
                phoneno = etphoneno.getText().toString();

                city = sp_city.getSelectedItem().toString();
                state = sp_state.getSelectedItem().toString();
                if (uname != "" && password != "" && liscenceno != "" && email != "" && phoneno != ""
                        && city != "" && state != "Select State") {


                    JSONStringer stringer = new JSONStringer();

                    if (sp_Regtype.getSelectedItem().toString() != "Select Reg type") {

                        if (sp_Regtype.getSelectedItem().toString() == "Doctor" && etDOB.getText().toString() != "") {
                            gender = sp_gender.getSelectedItem().toString();
                            education = sp_education.getSelectedItem().toString();
                            dob = etDOB.getText().toString();
                            functionname = "registernewDoctor";


                        } else {
                            functionname = "registerClinic";
                        }
                        String image= new String();
                        if(bitmap!=null)
                        image=SaveImage(bitmap);
                        else
                        {
                            Toast.makeText(RegisterationActivity.this,"No image Uploaded",Toast.LENGTH_LONG).show();
                        }


                        try {
                            stringer.object().key("uname").value(uname);
                            stringer.key("emaiil").value(email);
                            stringer.key("pass").value(password);
                            stringer.key("liscno").value(liscenceno);
                            if(sp_Regtype.getSelectedItem().toString() == "Doctor") {
                                stringer.key("Dob").value(dob);
                                stringer.key("educationn").value(education);
                                stringer.key("genderr").value(gender);
                            }
                            stringer.key("statee").value(state);
                            stringer.key("cityy").value(city);
                            stringer.key("locationn").value(location);
                            stringer.key("phonenoo").value(phoneno);
                            stringer.key("imag").value(image);


                            stringer.endObject();

                            wcf = WCFHandler.PostJsonResult(functionname, stringer);
                            // wcfrep = WCFHandler.GetJsonResult(functionCall + "/" +encodeimage);

                            pd.dismiss();
                            Toast.makeText(RegisterationActivity.this,wcf,Toast.LENGTH_LONG).show();

                            //Intent intent  = new Intent(RegisterationActivity.this,LoginActivity.class);
                            //startActivity(intent);

                        } catch (Exception e) {

                        }


                    } else {
                        Toast.makeText(RegisterationActivity.this, "Please select Registeration type", Toast.LENGTH_LONG).show();

                    }


                }


               // SaveImage(bitmap);
            }
        });

        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    selectImageFromGallery();
                } else {
                    requestPermission();
                    selectImageFromGallery();
                }


            }

        });


        //getSupportActionBar().setTitle("Registration");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // final TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayoutReg);
        //  tabLayout.addTab(tabLayout.newTab().setText("Doctor"));
        //tabLayout.addTab(tabLayout.newTab().setText("Clinic"));


        //final ViewPager viewPager = (ViewPager)findViewById(R.id.pagerReg);

        //  final PagerAdopterRegisteration adopter = new PagerAdopterRegisteration(getSupportFragmentManager(),tabLayout.getTabCount());

        //viewPager.setAdapter(adopter);

        //viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
        //   @Override
        //  public void onTabSelected(TabLayout.Tab tab) {
        //    viewPager.setCurrentItem(tab.getPosition());

        //}

        // @Override
        //public void onTabUnselected(TabLayout.Tab tab) {

        //}

        //@Override
        //public void onTabReselected(TabLayout.Tab tab) {

        //}
        //});
    }

    public void spinnertype() {
        type.add("Select Reg Type");
        type.add("Doctor");
        type.add("Clinic");
        typeadopter = new ArrayAdapter<String>(RegisterationActivity.this, R.layout.support_simple_spinner_dropdown_item, type);
        sp_Regtype.setAdapter(typeadopter);
    }

    public void ExperienceAdopter() {
        alexperience.add("Education");
        alexperience.add("MBBS");
        alexperience.add("BMBS");
        alexperience.add("MBChB");
        alexperience.add("MBBCh");

        experienceadopter = new ArrayAdapter<String>(RegisterationActivity.this, R.layout.support_simple_spinner_dropdown_item, alexperience);
        sp_education.setAdapter(experienceadopter);
    }

    public void GenderAdopter() {
        alGender.add("Select Gender");
        alGender.add("Male");
        alGender.add("Female");
        genderadopter = new ArrayAdapter<String>(RegisterationActivity.this, R.layout.support_simple_spinner_dropdown_item, alGender);
        sp_gender.setAdapter(genderadopter);
    }

    public void Addstates() {
        alstates.add("Select States");
        alstates.add("Punjab");
        alstates.add("Sindh");
        alstates.add("NWFP");
        alstates.add("Balochistan");
        statesadopter = new ArrayAdapter<String>(RegisterationActivity.this, R.layout.support_simple_spinner_dropdown_item, alstates);
        sp_state.setAdapter(statesadopter);
    }

    public void AddinHashtable() {

        ht = new Hashtable<>();
        ht.put("Rawalpindi", "Punjab");
        ht.put("Jehlum", "Punjab");
        ht.put("Lahore", "Punjab");
        ht.put("Peshawar", "NWFP");
        ht.put("Nowshera", "NWFP");
        ht.put("Karachi", "Sindh");
        ht.put("FaisalAbad", "Punjab");


    }

    public void Addcities(String s) {


        Set<String> ss = ht.keySet();

        for (String aa : ss) {

            if (ht.get(aa) == s) {
                alcities.add(aa);
            }

        }
        cityadopter = new ArrayAdapter<String>(RegisterationActivity.this, R.layout.support_simple_spinner_dropdown_item, alcities);
        sp_city.setAdapter(cityadopter);


    }

    //@Override
    //public void onFragmentInteraction(Uri uri) {

    //}

    public void selectImageFromGallery() {
        //Intent intent = new Intent();
        //intent.setType("image/*");
        //intent.setAction(Intent.ACTION_PICK);
        // startActivityForResult(Intent.createChooser(intent, "Select Picture"),PICK_IMAGE);
        // startActivityForResult(intent,PICK_IMAGE);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)

                && !Environment.getExternalStorageState().equals(

                Environment.MEDIA_CHECKING)) {

            Intent intent = new Intent(Intent.ACTION_PICK);

            intent.setType("image/*");

            startActivityForResult(intent, 1);


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            Log.i("picturePath", "picturePath: " + picturePath);
            cursor.close();

            img.setVisibility(View.VISIBLE);
            decodeFile(picturePath);


        }
    }

    /**
     * The method decodes the image file to avoid out of memory issues. Sets the
     * selected image in to the ImageView.
     *
     * @param filePath
     */
    public void decodeFile(String filePath) {
        // Decode image size


        BitmapFactory.Options o1 = new BitmapFactory.Options();
        o1.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o1);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o1.outWidth, height_tmp = o1.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, o2);
        // bm =BitmapFactory.decodeFile(filePath,o2);

        //ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG,80,baos);
        //byte[] b = baos.toByteArray();
        //Bitmap bitmap2 = BitmapFactory.decodeByteArray(b,0,b.length);

        img.setImageBitmap(bitmap);

    }

    /**
     * The class connects with server and uploads the photo
     */


    String SaveImage(Bitmap biitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        biitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodeimage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodeimage;

        //txt.setText(encodeimage);
        // final int pass = 123;
        //String JasonString = WCFHandler.GetJsonResult("images" + "/" + encodeimage);

        //String wcfrep = "";
        //JSONStringer stringer = new JSONStringer();
        //try {
        //  stringer.object().key("imagee").value(encodeimage);

        //stringer.endObject();
       // String functionCall = "saveimage";
        //   wcfrep = WCFHandler.PostJsonResult(functionCall, stringer);
        //Toast.makeText(getActivity().getApplicationContext(), "my incomming in chats call", Toast.LENGTH_LONG).show();
        //} catch (Exception e) {
        //  Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }






    private void requestPermission() {
        ActivityCompat.requestPermissions(RegisterationActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==  PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(RegisterationActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegisterationActivity.this,"Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }
}
