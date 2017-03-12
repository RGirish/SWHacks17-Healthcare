package com.girish.raman.healthcare.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.girish.raman.healthcare.R;
import com.girish.raman.healthcare.model.Phone;
import com.girish.raman.healthcare.model.Practice;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Practice> practices;

    public RecyclerViewAdapter(List<Practice> articles) {
        this.practices = articles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v1 = inflater.inflate(R.layout.contents_practice, viewGroup, false);
        viewHolder = new PracticeViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PracticeViewHolder practiceViewHolder = (PracticeViewHolder) viewHolder;
        configurePracticeViewHolder(practiceViewHolder, position);
    }

    private void configurePracticeViewHolder(PracticeViewHolder practiceViewHolder, int position) {
        Practice practice = practices.get(position);
        TextView tvname = practiceViewHolder.tvname;
        TextView tvwebsite = practiceViewHolder.tvwebsite;
        TextView tvaddress = practiceViewHolder.tvaddress;
        TextView tvinsurance = practiceViewHolder.tvinsurance;
        TextView tvphone = practiceViewHolder.tvphone;

        tvname.setText(practice.getName());
        tvaddress.setText(practice.getAddress().toString());
        List<Phone> phones = practice.getPhones();
        StringBuilder builder = new StringBuilder();
        for (Phone phone : phones)
            builder.append(phone.getNumber() + " (" + phone.getType() + "), ");
        String phonesString = builder.toString().substring(0, builder.toString().length() - 1);
        tvphone.setText(phonesString);

        tvwebsite.setText(practice.getWebsite());

        List<String> insurances = practice.getInsuranceData();
        StringBuilder builder2 = new StringBuilder();
        for (String s : insurances)
            builder2.append(s + ", ");
        String insuranceString = builder2.toString().substring(0, builder2.toString().length() - 1);
        tvinsurance.setText(insuranceString);
    }

    @Override
    public int getItemCount() {
        return (null != practices ? practices.size() : 0);
    }

    private class PracticeViewHolder extends RecyclerView.ViewHolder {
        TextView tvname;
        TextView tvwebsite;
        TextView tvaddress;
        TextView tvphone;
        TextView tvinsurance;

        public PracticeViewHolder(View view) {
            super(view);
            this.tvaddress = (TextView) view.findViewById(R.id.address);
            this.tvname = (TextView) view.findViewById(R.id.name);
            this.tvwebsite = (TextView) view.findViewById(R.id.website);
            this.tvphone = (TextView) view.findViewById(R.id.phone);
            this.tvinsurance = (TextView) view.findViewById(R.id.insurace);
        }
    }
}