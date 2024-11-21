package sv.edu.catolica.educonnect.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sv.edu.catolica.educonnect.models.ChatMessage;
import sv.edu.catolica.educonnect.R;

public class ChatAdaptador extends RecyclerView.Adapter<ChatAdaptador.ChatViewHolder> {
    private List<ChatMessage> chatMessages;

    public ChatAdaptador(List<ChatMessage> chatMessages){
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position){
        ChatMessage chatMessage = chatMessages.get(position);
        holder.messageText.setText(chatMessage.getMessage());
        holder.timestampText.setText(formatDate(chatMessage.getTimestamp()));
    }

    @Override
    public int getItemCount(){
        return chatMessages.size();
    }

    private String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        return sdf.format(date);
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView messageText, timestampText;

        ChatViewHolder(View itemView){
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
            timestampText = itemView.findViewById(R.id.timestampText);
        }
    }
}
