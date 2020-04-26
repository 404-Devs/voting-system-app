package com.discussiongroup.voting;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class voteFrag extends Fragment {


    static String pn;
    ImageView profimg, partyimg;
    TextView Name, Party;

    public voteFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vote, container, false);
        Party = view.findViewById(R.id.frag_party);
        Party.setText(pn);
        return inflater.inflate(R.layout.fragment_vote, container, false);
    }
}
