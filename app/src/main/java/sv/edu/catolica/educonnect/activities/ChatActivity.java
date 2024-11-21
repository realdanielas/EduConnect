package sv.edu.catolica.educonnect.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sv.edu.catolica.educonnect.models.ChatMessage;
import sv.edu.catolica.educonnect.R;
import sv.edu.catolica.educonnect.adapters.ChatAdaptador;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ChatAdaptador chatAdaptador;
    private List<ChatMessage> chatMessages;
    private EditText messageInput;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private ListenerRegistration listenerRegistration;
    private String senderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_chat);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        messageInput = findViewById(R.id.messageInput);
        Button sendButton = findViewById(R.id.sendButton);

        chatMessages = new ArrayList<>();
        chatAdaptador = new ChatAdaptador(chatMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdaptador);

        //Obtener el usuario actual
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null){
            senderId = currentUser.getUid();
            loadMessages();
        } else {
            Toast.makeText(this, "Usuario no autenticado. ", Toast.LENGTH_SHORT).show();
            finish();
        }

        sendButton.setOnClickListener(v -> sendMessage());

    }

    private void loadMessages(){
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) return;

        String currentUserId = currentUser.getUid();
        Query query = db.collection("chatMessages")
                .whereEqualTo("receiverId", currentUserId)//Carga los mensajes donde el usuario actual es el recibidor
                .orderBy("timestamp");
        listenerRegistration = query.addSnapshotListener((snapshots, e) -> {
            if (e != null){
                Toast.makeText(ChatActivity.this, "Error cargando mensajes.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (snapshots != null){
                chatMessages.clear();
                for (QueryDocumentSnapshot doc : snapshots){
                    ChatMessage message = doc.toObject(ChatMessage.class);
                    chatMessages.add(message);
                }
            }
            chatAdaptador.notifyDataSetChanged();
            recyclerView.scrollToPosition(chatMessages.size() - 1);
        });
    }

    private void sendMessage(){
        String messageText = messageInput.getText().toString().trim();
        if (messageText.isEmpty()){
            Toast.makeText(this, "Por favor escriba un mensaje.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Usa el senderId del usuario autenticado
        String receiverId = "";
        ChatMessage chatMessage = new ChatMessage(senderId, receiverId, messageText, new Date());

        db.collection("chatMessages").add(chatMessage)
                .addOnSuccessListener(documentReference -> {
                    messageInput.setText(""); //Limpia el input
                })
                .addOnFailureListener(e -> Toast.makeText(ChatActivity.this, "Error enviando mensaje.", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (listenerRegistration != null){
            listenerRegistration.remove();
        }
    }
}
