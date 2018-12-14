package com.example.sandhya.android.grayroutes_maplist.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
        mapsListViewHolder.setData(list.get(i));    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class mapsViewHolder extends RecyclerView.ViewHolder {

        public ImageView mapImage;
        public TextView latlng;
        public TextView address;
        public MapData list;

        public mapsViewHolder(View itemView) {
            super(itemView);
            mapImage = itemView.findViewById(R.id.map_image);
            latlng = itemView.findViewById(R.id.map_latLng);
            address = itemView.findViewById(R.id.map_addr);
        }

        private void setData(MapData mapData) {
            this.list = mapData;

            mapImage.setImageBitmap(list.getImage());
            String latLong = list.getLat() + "," + list.getLon();
            latlng.setText(latLong);
            address.setText(list.getAddress().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
      }
    }
