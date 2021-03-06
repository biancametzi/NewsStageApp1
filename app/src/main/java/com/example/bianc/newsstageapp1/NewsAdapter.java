package com.example.bianc.newsstageapp1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context  of the app
     * @param newsList is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news at the given position in the list of news
        News currentNews = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Display the title of the current news in that TextView
        titleView.setText(currentNews.getTitle());

        // Find the TextView with view ID section
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        // Display the section name of the current news in that TextView
        sectionView.setText(currentNews.getSectionName());

        // Find the TextView with view ID author
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        if (currentNews.getAuthor() != null) {
            // Display the author of the current News in that TextView or set the visibility in
            // case there is no author mentioned in the news
            authorView.setText(currentNews.getAuthor());
        } else {
            authorView.setVisibility(View.INVISIBLE);
        }

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Sep 3, 1991")
        String formattedDate = formatDate(currentNews.getDateTime());
        if (currentNews.getDateTime() != null) {
            // Display the date of the current news in that TextView or set the visibility in
            // case there is no date available
            dateView.setText(formattedDate);
        } else {
            dateView.setVisibility(View.INVISIBLE);
        }


        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "6:00PM")
        String formattedTime = formatTime(currentNews.getDateTime());
        if (currentNews.getDateTime() != null) {
            // Display the time of the current news in that TextView or set the visibility in
            // case there is no time available
            timeView.setText(formattedTime);
        } else {
            dateView.setVisibility(View.INVISIBLE);
        }
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Sep 3, 1991") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "6:00 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
