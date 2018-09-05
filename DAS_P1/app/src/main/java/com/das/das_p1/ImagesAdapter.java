package com.das.das_p1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private String[] mIDs;
    private String[] mDatas;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public CardView mCardView;
        public ViewHolder(View v) {
            super(v);
            mTextView= (TextView) v.findViewById(R.id.idTV);
            mCardView= (CardView) v.findViewById(R.id.imagCV);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ImagesAdapter(String[] pIDs, String[] pDatas) {
        mIDs=pIDs;
        mDatas=pDatas;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_images,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mIDs[position]);

        byte[] out = Base64.decode(mDatas[position], Base64.DEFAULT);
        Bitmap decode = BitmapFactory.decodeByteArray(out, 0,out.length);
        BitmapDrawable decodeDraw = new BitmapDrawable(decode);
        holder.mCardView.setBackground(decodeDraw);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mIDs.length;
    }
}
