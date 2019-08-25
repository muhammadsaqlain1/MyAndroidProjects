package com.example.usman.virtualclinic;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONStringer;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddingNewPatientFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddingNewPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddingNewPatientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView tvname,tvDOD,tvcnic,tvcontact;
    Spinner sp_gender;
    Button btnaddpatient;
    ImageView btnuploadpic;
    String name,dob,cnic,contact,pic,gender;
    ArrayList<String> alGender;
    ArrayAdapter<String> genderadopter;
   // JSONStringer stringer;


    public static final int RequestPermissionCode = 1;

    ImageView img;
    String wcf;

    public Bitmap bitmap;
    public static final int PICK_IMAGE = 1;


    private OnFragmentInteractionListener mListener;

    public AddingNewPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddingNewPatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddingNewPatientFragment newInstance(String param1, String param2) {
        AddingNewPatientFragment fragment = new AddingNewPatientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public static String cemail;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_adding_new_patient, container, false);



        tvname=(TextView)view.findViewById(R.id.add_new_patient_name);
        tvDOD=(TextView)view.findViewById(R.id.add_new_patient_DOB);
        tvcnic=(TextView)view.findViewById(R.id.add_new_patient_cnic);
        tvcontact=(TextView)view.findViewById(R.id.add_new_patient_contact);
        sp_gender=(Spinner)view.findViewById(R.id.add_new_patient_gender);


        img=(ImageView)view.findViewById(R.id.add_new_patient_uploadimage);
        btnaddpatient=(Button)view.findViewById(R.id.add_new_patient_btn_addpatient);
        btnuploadpic=(ImageView) view.findViewById(R.id.add_new_patient_image);

        alGender=new ArrayList<String>();
        GenderAdopter();


        btnuploadpic.setOnClickListener(new View.OnClickListener() {
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


        btnaddpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=tvname.getText().toString();
                contact=tvcontact.getText().toString();
                cnic=tvcnic.getText().toString();
                dob=tvDOD.getText().toString();
                pic=SaveImage(bitmap);

                gender=sp_gender.getSelectedItem().toString();

                JSONStringer stringer;
                stringer=new JSONStringer();
                try {
                    stringer.object().key("pnamee").value(name);
                    stringer.key("dobb").value(dob);
                    stringer.key("cnicc").value(cnic);
                    stringer.key("contactt").value(contact);
                    stringer.key("imag").value(pic);
                    stringer.key("genderr").value(gender);
                    stringer.key("cemaill").value(cemail);

                    stringer.endObject();
                    String functionname="addpatient";

                    wcf = WCFHandler.PostJsonResult(functionname, stringer);
                    // wcfrep = WCFHandler.GetJsonResult(functionCall + "/" +encodeimage);
                    Toast.makeText(getActivity(),wcf,Toast.LENGTH_LONG).show();


                } catch (Exception e) {

                }



            }
        });



        return view;
    }



    public void GenderAdopter() {
        alGender.add("Select Gender");
        alGender.add("Male");
        alGender.add("Female");
        genderadopter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, alGender);
        sp_gender.setAdapter(genderadopter);
    }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK
                && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};


            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
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

    public String SaveImage(Bitmap biitmap) {

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
        ActivityCompat.requestPermissions(getActivity(), new
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
                        Toast.makeText(getActivity(), "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(),"Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getContext(),RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
