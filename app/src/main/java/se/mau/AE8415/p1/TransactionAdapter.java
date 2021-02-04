package se.mau.AE8415.p1;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private ArrayList<IncomeItem> items;
    private AppController controller;




    public TransactionAdapter(ArrayList<IncomeItem> arrayList,AppController controller){

        items=arrayList;
        this.controller=controller;
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder{

        public ImageView img;
        public TextView dateText;
        public TextView amountText;
        public TextView categoryText;
        public CardView card;
        public View v;
        private int type;

        public int getType(){
            return  type;
        }


        public TransactionViewHolder(View itemView, int viewType) {
            super(itemView);
            v=itemView;
            if(viewType==1) {
                dateText = itemView.findViewById(R.id.tvDate);
                amountText = itemView.findViewById(R.id.tvAmount);
                categoryText = itemView.findViewById(R.id.tvCategory);
                card = itemView.findViewById(R.id.incomeCard);
                type = viewType;
            }
            else{
               dateText = itemView.findViewById(R.id.tvDateO);
               amountText = itemView.findViewById(R.id.tvAmountO);
               img = itemView.findViewById(R.id.ivCategory);
               card = itemView.findViewById(R.id.outcomeCard);
               type = viewType;
            }
        }
    }



    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==1){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_item,parent, false);
            TransactionViewHolder transactionViewHolder = new TransactionViewHolder(v,viewType);
            return transactionViewHolder;
        }
        else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.outcome_item,parent, false);
            TransactionViewHolder transactionViewHolder = new TransactionViewHolder(v, viewType);
            return transactionViewHolder;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        final IncomeItem currentItem = items.get(position);

        //holder.categoryText.setText(currentItem.getCategory());
        holder.amountText.setText(currentItem.getAmount()+" kr");
        holder.dateText.setText(currentItem.getDate());
        if(holder.getType()==1){
            holder.categoryText.setText(currentItem.getCategory());
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //showInfo
                    //controller.viewOverView();
                    controller.viewIncomeInfo(currentItem.getEntity());
                }
            });
        }
        else{
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //controller.viewUserLogin();
                    controller.viewIncomeInfo(currentItem.getEntity());
                    controller.viewOutcomeInfo(currentItem.getEntity());
                    //showInfo
                }
            });
            //holder.img.setImageResource(R.mipmap.news_watch);
            if(currentItem.getCategory().equals("bills")){
                holder.img.setImageResource(R.mipmap.bills);
            }
            else if(currentItem.getCategory().equals("activites")){
                holder.img.setImageResource(R.mipmap.fotboll);
            }
            else if(currentItem.getCategory().equals("technology")){
                holder.img.setImageResource(R.mipmap.technology);
            }
            else if(currentItem.getCategory().equals("food")){
                holder.img.setImageResource(R.mipmap.food);
            }
            else{
               holder.img.setImageResource(R.mipmap.misc);
            }
        }


    }

    @Override
    public int getItemViewType(int position) {

        if(items.get(position).getEntity().getAmount()>0){
            return 1;
        }
        else{
            return 0;
        }

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setController(AppController controller){
        this.controller=controller;
    }
}
