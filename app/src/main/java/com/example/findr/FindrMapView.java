package com.example.findr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.api.IMyLocationOverlay;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.ResourceProxyImpl;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FindrMapView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FindrMapView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindrMapView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ResourceProxyImpl mResourceProxy;
    private MapView mMapView;
    private LocationManager mLocMgr;
    private IMyLocationOverlay mMyLocationOverlay;
    private Context mContext;

    private OnFragmentInteractionListener mListener;

    private ArrayList<OverlayItem> mItems = new ArrayList<OverlayItem>();
    private ArrayList<Event> mEvents = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext = inflater.getContext();
        mResourceProxy = new ResourceProxyImpl(mContext.getApplicationContext());
        mMapView = new MapView(inflater.getContext(), 256, mResourceProxy);
        mMapView.setTileSource(TileSourceFactory.MAPQUESTOSM);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        mMapView.setUseDataConnection(true);
        IMapController mapCtrl = mMapView.getController();
        mapCtrl.setCenter(new GeoPoint(43.5, -80.5));
        mapCtrl.setZoom(10);
        mMapView.invalidate();

        return mMapView;
    }

    public void addEvent(Event event) {
        String name = event.name;
        double latitude = event.coordinates[0];
        double longitude = event.coordinates[1];
        mItems.add(new OverlayItem(name, name, new GeoPoint(latitude, longitude)));
        mEvents.add(event);
    }

    public void startOverlays() {
        ItemizedOverlay<OverlayItem> overlay = new ItemizedOverlayWithFocus<OverlayItem>(mContext, mItems,
            new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

                @Override
                public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                    Log.i("Touch", "icon tapped");
                    Intent myIntent = new Intent(getActivity(), EventInfoActivity.class);
                    myIntent.putExtra("event", (Serializable) mEvents.get(index)); //Optional parameters
                    getActivity().startActivity(myIntent);
                    return true;
                }

                @Override
                public boolean onItemLongPress(final int index, final OverlayItem item) {
                    return false;
                }
            });

        mMapView.getOverlays().add(overlay);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindrMapView.
     */
    // TODO: Rename and change types and number of parameters
    public static FindrMapView newInstance(String param1, String param2) {
        FindrMapView fragment = new FindrMapView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FindrMapView() {
        // Required empty public constructor
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
