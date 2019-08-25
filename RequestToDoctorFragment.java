package com.example.usman.virtualclinic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestToDoctorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestToDoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestToDoctorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    ListView lis;

    String wcf;
    JSONArray jsonArray;

    private OnFragmentInteractionListener mListener;

    public RequestToDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestToDoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestToDoctorFragment newInstance(String param1, String param2) {
        RequestToDoctorFragment fragment = new RequestToDoctorFragment();
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

    int count=0;
    String docemail=null;
    ReqToDocAdopter myListAdopter;
    public static String demail;
    Thread t;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_request_to_doctor, container, false);

        lis=view.findViewById(R.id.Req_to_patientslisttt);



        docemail="ali@";

        if(count==0) {
            wcf = WCFHandler.GetJsonResult("GetReqfordoc/" + demail);

            jsonArray = null;

            try {
                jsonArray = new JSONArray(wcf);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            myListAdopter = new ReqToDocAdopter(getActivity(), jsonArray);
            lis.setAdapter(myListAdopter);
            count++;
        }

//
//        t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//               getnewReq();
//            }
//        });
//        t.start();



        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView t=(TextView)view.findViewById(R.id.llreqToDoc_patient_name);
                TextView tt=(TextView)view.findViewById(R.id.llreq_to_Doc_Problem_Description);
                ImageView ii = (ImageView)view.findViewById(R.id.llReq_to_Doc_pic);

                String gender,dob,name,cnic,contact,sugar,temp,pulse,breathrate,weight,problem,BPstolic,BPdistolic;
                int pid=-1;

                dob=null;
                gender=null;

                dob=null;
                cnic=null;
                contact=null;
                sugar=null;
                pulse=null;
                weight=null;
                problem=null;
                BPdistolic=null;
                BPstolic=null;
                temp=null;
                breathrate=null;

                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject=jsonArray.getJSONObject(i);
                    dob=jsonObject.getString("dob");
                    gender=jsonObject.getString("gender");
                    cnic=jsonObject.getString("cnic");
                    breathrate=jsonObject.getString("BR");
                    BPdistolic=jsonObject.getString("BPds");
                    BPstolic=jsonObject.getString("BPs");
                    temp=jsonObject.getString("temp");
                    pulse=jsonObject.getString("pulse");
                    sugar=jsonObject.getString("sugarlevel");
                    weight=jsonObject.getString("weight");
                    pid=jsonObject.getInt("pid");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ii.setDrawingCacheEnabled(true);
                Bitmap b=ii.getDrawingCache();

                 name=null;
                problem=null;

                name=t.getText().toString();

                problem=tt.getText().toString();

                Intent intent = new Intent(getActivity(),DoctorCheckPatientVisitingActivity.class);

                intent.putExtra("name", name);
                intent.putExtra("bitmap",b);
                intent.putExtra("BR",breathrate);
                intent.putExtra("BPs",BPstolic);
                intent.putExtra("BPds",BPdistolic);
                intent.putExtra("temp",temp);
                intent.putExtra("sugar",sugar);
                intent.putExtra("pulse",pulse);
                intent.putExtra("gender",gender);
                intent.putExtra("weight",weight);
                intent.putExtra("problem",problem);
                intent.putExtra("docname","ali@");
                intent.putExtra("pid",pid);
                intent.putExtra("Docemail",docemail);



                if(t!=null)
                {
                    t=null;
                }
                startActivity(intent);





            }
        });

        return view;
    }
    public void getnewReq()
    {

//
//        try {
//            t.wait(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if(count>0) {
            wcf = WCFHandler.GetJsonResult("GetReqfordoc" + "/ali@");

            jsonArray = null;

            try {
                jsonArray = new JSONArray(wcf);
                myListAdopter.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        getnewReq();


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
