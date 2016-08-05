package com.yes.youthexploringscience.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yes.youthexploringscience.R;


public class LinksFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView ivPaylocity;
    private ImageView ivStudents;
    private ImageView ivSLSCWebsite;
    private ImageView ivYESWebsite;

    public LinksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_links, container, false);

        ivPaylocity = (ImageView) view.findViewById(R.id.ivPaylocity);
        ivPaylocity.setOnClickListener(this);

        ivStudents = (ImageView) view.findViewById(R.id.ivStudents);
        ivStudents.setOnClickListener(this);

        ivSLSCWebsite = (ImageView) view.findViewById(R.id.ivSLSCWebsite);
        ivSLSCWebsite.setOnClickListener(this);

        ivYESWebsite = (ImageView) view.findViewById(R.id.ivYESWebsite);
        ivYESWebsite.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Uri uri;
        Intent intent;
        switch (view.getId()) {
            case R.id.ivPaylocity:
                uri = Uri.parse(getString(R.string.link_paylocity));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.ivSLSCWebsite:
                uri = Uri.parse(getString(R.string.link_slsc_website));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.ivStudents:
                uri = Uri.parse(getString(R.string.link_students));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.ivYESWebsite:
                uri = Uri.parse(getString(R.string.link_yes_website));
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }

}
