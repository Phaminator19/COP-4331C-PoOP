package com.example.project_1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{
    Context context;
    List<Event> eventList;

    public EventAdapter(Context, context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_event, root: null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBinViewHolder(@NonNull EventViewHolder eventViewHolder, int i){
        Event event = eventList.get(i);

        eventViewHolder.eventtitle.setText(event.getEventitle());
        eventViewHolder.eventcategory.setText(event.getEventcategory());
        eventViewHolder.eventpicture.setImageDrawable(context.getResources().
                getDrawable(event.getEventpicture()));

    }

    @Override
    public int getItemCount(){
        return 0;
    }

    class EventViewHolder extends RecycleView.ViewHolder{
        TextView eventtitle, eventcategory;
        ImageView eventpicture;

        public EventViewHolder(@NonNull View itemView){
            super(itemView);

            eventtitle = itemView.findViewById(R.id.eventtitle);
            eventcategory = itemView.findViewById(R.id.eventcategory);
            eventpicture = itemView.findViewById(R.id.eventpicture);
        }
    }
}
