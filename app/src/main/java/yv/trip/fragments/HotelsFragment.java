package yv.trip.fragments;

/**
 * Created by yaron on 22/02/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yv.trip.MainActivity;
import yv.trip.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HotelsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    List<HashMap<String,String>> hotelsList = new ArrayList<HashMap<String,String>>();



    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HotelsFragment newInstance(int sectionNumber) {
        HotelsFragment fragment = new HotelsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public HotelsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hotels_main, container, false);

        initList();

        // getting the ListView component from the layout
        ListView lv = (ListView) rootView.findViewById(R.id.listView);

        SimpleAdapter simpleAdpt = new SimpleAdapter(rootView.getContext(), hotelsList, R.layout.trip_list_item, new String[]{"hotel"}, new int[]{android.R.id.text1});

        lv.setAdapter(simpleAdpt);

        return rootView;
    }

    private void initList() {
        // We populate the flights
        hotelsList.add(createFlight("Tokyo", "24/3/15", "30/3/15"));
        hotelsList.add(createFlight("Osaka", "24/3/15", "30/3/15"));
        hotelsList.add(createFlight("Hiroshima", "24/3/15", "30/3/15"));
        hotelsList.add(createFlight("Koya-San", "24/3/15", "30/3/15"));
    }

    private HashMap<String, String> createFlight(String city, String checkIn, String checkOut) {
        HashMap<String, String> hotel = new HashMap<String, String>();
        hotel.put("hotel", city);

        return hotel;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}