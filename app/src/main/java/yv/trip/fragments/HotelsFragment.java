package yv.trip.fragments;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yv.trip.R;

/**
 * Hotels fragment
 */
public class HotelsFragment extends Fragment {

    private List<HashMap<String,String>> hotelsList = new ArrayList<>();

    private Map<String, List<String>> hotelsInfo = new HashMap<>();

    private SimpleAdapter simpleAdpt = null;

    private int INDEX_CITY = 0;
    private int INDEX_HOTEL_NAME = 1;
    private int INDEX_CHECK_IN = 2;
    private int INDEX_CHECK_OUT = 3;

    public HotelsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hotels_main, container, false);

        initList();

        // getting the ListView component from the layout
        ListView lv = (ListView) rootView.findViewById(R.id.listView);

        simpleAdpt = new SimpleAdapter(rootView.getContext(), hotelsList, R.layout.trip_list_item, new String[]{"hotel"}, new int[]{android.R.id.text1});

        lv.setAdapter(simpleAdpt);

        registerForContextMenu(lv);

        return rootView;
    }

    private void initList() {
        // We populate the flights
        hotelsList.add(createFlight("Tokyo", "Tokyu Stay Nishi Shinjuku", "24/3/15", "1/4/15"));
        hotelsList.add(createFlight("Takayama", "Spa Hotel Alpina Hida Takayama", "1/4/15", "3/4/15"));
        hotelsList.add(createFlight("Takayama", "Hotel Associa Takayama Resort", "1/4/15", "3/4/15"));
        hotelsList.add(createFlight("Matsumoto","Hotel Morschein", "3/4/15", "5/4/15"));
        hotelsList.add(createFlight("Matsumoto","Matsumoto Hotel Kagetsu", "3/4/15", "5/4/15"));
        hotelsList.add(createFlight("Kyoto", "Sakura Terrace", "5/4/15", "10/4/15"));
        hotelsList.add(createFlight("Kyoto", "Kyoto Apartment Emblem Kyomon", "10/4/15", "11/4/15"));
        hotelsList.add(createFlight("Koyasan", "Shukubo Koya-san Eko-in Temple", "11/4/15", "12/4/15"));
        hotelsList.add(createFlight("Hiroshima", "Grand Prince Hotel Hiroshima", "12/4/15", "13/4/15"));
        hotelsList.add(createFlight("Osaka", "Arietta Hotel Osaka", "13/4/15", "14/4/15"));
        hotelsList.add(createFlight("Hong Kong", "99 Bonham", "14/4/15", "17/4/15"));
        hotelsList.add(createFlight("Maccau", "Sheraton Macao Hotel, Cotai Central", "17/4/15", "18/4/15"));
        hotelsList.add(createFlight("Hong Kong", "Hotel Madera Hong Kong", "18/4/15", "19/4/15"));
        hotelsList.add(createFlight("Yangshuo", "Yangshuo Rosewood Boutique Hotel", "19/4/15", "23/4/15"));
        hotelsList.add(createFlight("Yangshuo", "River View Hotel", "19/4/15", "23/4/15"));
        hotelsList.add(createFlight("Beijing", "Inner Mongolia Grand Hotel Wangfujing", "23/4/15", "27/4/15"));
        hotelsList.add(createFlight("Beijing", "Beijing Prime Hotel Wangfujing ", "23/4/15", "27/4/15"));
        hotelsList.add(createFlight("Yangshuo", "River View Hotel", "19/4/15", "23/4/15"));
        hotelsList.add(createFlight("Yangshuo", "River View Hotel", "19/4/15", "23/4/15"));
    }

    private HashMap<String, String> createFlight(String city, String name, String checkIn, String checkOut) {
        HashMap<String, String> hotel = new HashMap<>();
        String key = city + " - " + name;
        hotel.put("hotel", key);

        List<String> info = new ArrayList<>();
        info.add(INDEX_CITY, city);
        info.add(INDEX_HOTEL_NAME, name);
        info.add(INDEX_CHECK_IN, checkIn);
        info.add(INDEX_CHECK_OUT, checkOut);

        hotelsInfo.put(key, info);
        return hotel;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo aInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

        HashMap map =  (HashMap) simpleAdpt.getItem(aInfo.position);

        String key = map.get("hotel").toString();

        List<String> hotelInfo = hotelsInfo.get(key);

        menu.setHeaderTitle(map.get("hotel").toString());
        menu.add(1, 1, 1, hotelInfo.get(INDEX_CITY));
        menu.add(1, 2, 2, hotelInfo.get(INDEX_HOTEL_NAME));
        menu.add(1, 2, 2, hotelInfo.get(INDEX_CHECK_IN) + " - " + hotelInfo.get(INDEX_CHECK_OUT));
    }
}