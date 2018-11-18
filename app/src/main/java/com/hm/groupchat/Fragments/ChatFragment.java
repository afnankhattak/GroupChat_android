package com.hm.groupchat.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hm.groupchat.Models.Author;
import com.hm.groupchat.Models.Message;
import com.hm.groupchat.R;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private MessageInput messageInput;
    private MessagesList messagesList;

    private Author currentUser;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        messageInput = (MessageInput) view.findViewById(R.id.input);
        messagesList = (MessagesList) view.findViewById(R.id.messagesList);

        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, @Nullable String url, @Nullable Object payload) {

                Picasso.get().load(url).into(imageView);
            }
        };

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUser = new Author(firebaseUser.getUid(), firebaseUser.getDisplayName());

        final MessagesListAdapter<Message> adapter = new MessagesListAdapter<>(currentUser.getId(), imageLoader);
        messagesList.setAdapter(adapter);

        messageInput.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {

                Message message = new Message(input.toString(), currentUser, new Date());

                adapter.addToStart(message, true);

                return true;
            }
        });

        return view;
    }

}
