package com.example.usman.virtualclinic;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DocProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DocProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String wcf;
    ImageView imageView;
    TextView tvname,tvcity,tvcontact,tvliscence,tvstate,tvcnic,tvgender,tvrating,tveducation;

    String uname,city,contact,liscence,state,img,cnic,gender,experience,dob,education,rating;

    Button btnlogout;
    JSONObject jsonObject;


    private OnFragmentInteractionListener mListener;

    public DocProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DocProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DocProfileFragment newInstance(String param1, String param2) {
        DocProfileFragment fragment = new DocProfileFragment();
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

    public static String demail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doc_profile, container, false);


        imageView=view.findViewById(R.id.Doc_profile_picture);
        tvname=view.findViewById(R.id.Doc_profile_name);
        tvcity=view.findViewById(R.id.Doc_profile_city);
        tvliscence=view.findViewById(R.id.Doc_profile_liscence);
        tvstate=view.findViewById(R.id.Doc_profile_state);
        tvcontact=view.findViewById(R.id.Doc_profile_contact);
        tvcnic=view.findViewById(R.id.Doc_profile_cnic);
        tvgender=view.findViewById(R.id.Doc_profile_gender);
        tveducation=view.findViewById(R.id.Doc_profile_education);
        tvrating=view.findViewById(R.id.Doc_profile_rating);

        btnlogout = view.findViewById(R.id.btn_Doc_Profile_logout);


        String funnction="Docprofile"+"/"+demail;


        wcf=WCFHandler.GetJsonResult(funnction);


        jsonObject=null;

        try {
            jsonObject = new JSONObject(wcf);

            education=null;
            rating=null;

            uname=jsonObject.getString("name");
            city=jsonObject.getString("city");
            state=jsonObject.getString("state");
            contact=jsonObject.getString("contactno");
            img=jsonObject.getString("pic");
            liscence=jsonObject.getString("liscenceno");
            cnic=jsonObject.getString("cnic");
            gender=jsonObject.getString("gender");
            dob=jsonObject.getString("dob");
            //experience=jsonObject.getString("experience");
            education=jsonObject.getString("education");
            rating=jsonObject.getString("rating");




        } catch (JSONException e) {
            e.printStackTrace();
        }

        byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        tvname.setText(uname);
        tvgender.setText(gender);
        tvcnic.setText(cnic);
        tvcity.setText(city);
        tvstate.setText(state);
        tvcontact.setText(contact);
        tvliscence.setText(liscence);
        tveducation.setText(education);
        tvrating.setText(rating);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(),decodedByte);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
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
