package com.example.sandhya.android.grayroutes_maplist.activity;

import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandhya.android.grayroutes_maplist.R;
import com.example.sandhya.android.grayroutes_maplist.objects.MapData;

import java.util.List;

class MapDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MapData> list;

    public MapDataAdapter(List<MapData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new mapsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mapdata_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mapsViewHolder mapsListViewHolder = (mapsViewHolder) viewHolder;
        mapsListViewHolder.setData(list.get(i));
    }

    @Override
    public int getItemCount() {
        Log.i("SIZE", list.size() + " ");
        return list.size();
    }

    public static class mapsViewHolder extends RecyclerView.ViewHolder {

        public ImageView mapImage;
        public TextView latlng;
        public TextView address_view;
        public MapData list;
        public Address address;

        public mapsViewHolder(View itemView) {
            super(itemView);
            mapImage = itemView.findViewById(R.id.map_image);
            latlng = itemView.findViewById(R.id.map_latLng);
            address_view = itemView.findViewById(R.id.map_addr);
        }

        private void setData(MapData mapData) {
            this.list = mapData;

            mapImage.setImageBitmap(list.getImage());
            String latLong = list.getLat() + "," + list.getLon();
            latlng.setText("Latitude&Longitude: " +latLong);

            StringBuilder sb = new StringBuilder();
            if (mapData.getAddress().size() > 0) {

                address = mapData.getAddress().get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                }
                sb.append(address.getCountryName());
                String result = sb.toString();
                address_view.setText("Address: "+result);

            }
        }
    }
}
