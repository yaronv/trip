package yv.trip.fragments;

/**
 * Created by yaron on 22/02/15.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yv.trip.MainActivity;
import yv.trip.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FlightsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    List<HashMap<String,String>> flightsList = new ArrayList<HashMap<String,String>>();

    Map<String, List<String>> flightsInfo = new HashMap<String, List<String>>();

    SimpleAdapter simpleAdpt = null;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FlightsFragment newInstance(int sectionNumber) {
        FlightsFragment fragment = new FlightsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FlightsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.flights_main, container, false);

        initList();

        // getting the ListView component from the layout
        ListView lv = (ListView) rootView.findViewById(R.id.listView);

        simpleAdpt = new SimpleAdapter(rootView.getContext(), flightsList, R.layout.trip_list_item, new String[]{"flight"}, new int[]{android.R.id.text1});

        lv.setAdapter(simpleAdpt);

        // react to user clicks on item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {

                TextView clickedView = (TextView) view.findViewById(android.R.id.text1);

                Toast.makeText(rootView.getContext(), clickedView.getText() , Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(lv);

        return rootView;
    }

    private void initList() {
        // We populate the flights
        flightsList.add(createFlight("Tel Aviv", "Moscow", "24/3/15", "01:00 - 10:10"));
        flightsList.add(createFlight("Moscow", "Tokyo", "24/3/15", "20:00 - 11:40"));
        flightsList.add(createFlight("Osaka", "Hong Kong", "14/4/15", "13:25 - 16:35"));
        flightsList.add(createFlight("Hong Kong", "Guilin", "19/4/15", "8:50 - 10:15"));
        flightsList.add(createFlight("Guilin", "Beijing", "23/4/15", "11:15 - 14:05"));
        flightsList.add(createFlight("Beijing", "Moscow", "28/4/15", "00:40 - 3:40"));
        flightsList.add(createFlight("Moscow", "Tel Aviv", "28/4/15", "10:00 - 13:50"));
    }

    private HashMap<String, String> createFlight(String from, String to, String date, String timeRange) {
        HashMap<String, String> flight = new HashMap<String, String>();
        String key = from + " - " + to;
        flight.put("flight", key);
        List<String> info = new ArrayList<String>();
        info.add(from);
        info.add(to);
        info.add(date);
        info.add(timeRange);
        flightsInfo.put(key, info);
        return flight;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo aInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // We know that each row in the adapter is a Map
        HashMap map =  (HashMap) simpleAdpt.getItem(aInfo.position);

        String key = map.get("flight").toString();

        List<String> flightInfo = flightsInfo.get(key);

        menu.setHeaderTitle(map.get("flight").toString());
        menu.add(1, 1, 1, flightInfo.get(2));
        menu.add(1, 2, 2, flightInfo.get(3));
    }
}