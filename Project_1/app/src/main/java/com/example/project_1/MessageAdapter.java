package com.example.project_1;

import android.app.Activity;
import android.content.Context;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter
{

    List<Message> messages = new ArrayList<>();
    Context c;

    public MessageAdapter(Context c)
    {
        this.c = c;
    }

    static String getUserNameInitials(String username)
    {
        // if user doesn't have a username yet, return placeholder
        String initials = "__";
        if (username == null)
        {
            return initials;
        }

        // if user has a username get their initials
        String[] splitInits = username.split(" ");
        int len = splitInits.length;
        try
        {
            if (len == 1)
            {
                initials = splitInits[0].substring(0, 2).toUpperCase();
            }
            else
            {
                initials = (splitInits[0].charAt(0) + splitInits[1].substring(0, 1)).toUpperCase();
            }
        }
        catch (Exception e)
        {
            initials = "__";
        }

        return initials;
    }

    public void add(Message message)
    {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    // this makes each message bubble
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) c.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);

        // initialize message holder with the activity componenets
        convertView = messageInflater.inflate(R.layout.message, null);
        holder.avatarChar = convertView.findViewById(R.id.message_Inits);
        holder.messageBody = convertView.findViewById(R.id.message_text);
        holder.name = convertView.findViewById(R.id.message_user);
        holder.timestamp = convertView.findViewById(R.id.message_timestamp);
        holder.avatar = convertView.findViewById(R.id.avatar);
        convertView.setTag(holder);
        Drawable background = holder.avatar.getBackground();

        int clrset = R.color.acc_green;

        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(ContextCompat.getColor(c, clrset));
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(ContextCompat.getColor(c, clrset));
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(ContextCompat.getColor(c, clrset));
        }

        // put all the message information into the message holder
        holder.name.setText(message.getUserName());
        holder.messageBody.setText(message.getMessage());
        holder.timestamp.setText(message.getTimestamp());
        holder.avatarChar.setText(getUserNameInitials(message.getUserName()));

        return convertView;
    }

    // override needed to implement BaseAdapter
    @Override
    public int getCount()
    {
        return messages.size();
    }

    @Override
    public Object getItem(int i)
    {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

}

class MessageViewHolder {

    public TextView name;
    public TextView messageBody;
    public TextView timestamp;
    public TextView avatarChar;
    public View avatar;
}