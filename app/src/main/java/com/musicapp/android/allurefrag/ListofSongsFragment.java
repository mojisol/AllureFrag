package com.musicapp.android.allurefrag;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Scene;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListofSongsFragment extends Fragment {
    /**
     * Handles play back of all the sound file
     */


    private MediaPlayer mMediaPlayer;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                        // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                        // our app is allowed to continue playing sound but at a lower volume. We'll treat
                        // both cases the same way because our app is playing short sound files.

                        // Pause playback and reset player to the start of the file. That way, we can
                        // play the word from the beginning when we resume playback.
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // The Audio Focus gain means we have regained focus and can resume playback
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // The AUDIOFOSS_LOSS case means we have regained focus
                        // And can resume playback

                    }
                }
            };
    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManager;
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

        }
    };

    public ListofSongsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        final ArrayList<Sound> sounds = new ArrayList<Sound>();
        // sounds.add("one");
        //This is for a single output for more than one we use an object to specify it.

        sounds.add(new Sound("Olamide ft. Don Jazzy", "Skelemba", R.raw.ola_don, R.drawable.music_icon));
        sounds.add(new Sound("Fonsi", "Despacito", R.raw.luis, R.drawable.music_icon));

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listofsongs, container, false);
        RecyclerView listofsong = (RecyclerView)view.findViewById(R.id.listofsong_recycler_view);
        listofsong.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        listofsong.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        mAdapter = new SongAdapter(sounds);
        listofsong.setAdapter(mAdapter);
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mArtistTextView;
        public TextView mSongTextView;
        private Sound sounds;
        public SoundHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.music_imageview);
            mSongTextView = (TextView) itemView.findViewById(R.id.songtitle_textview);
            mArtistTextView = (TextView) itemView.findViewById(R.id.songartist_textview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

        public void bindData(Sound s) {
            sounds = s;
            mImageView.setImageResource(s.getImageResourceId());
            mArtistTextView.setText(s.getSongArtist());

    private class ScientistAdapter extends RecyclerView.Adapter<SoundHolder> {
                private ArrayList<Sound> sounds;

                public ScientistAdapter(ArrayList<Sound> Scientists) {
                    sounds = Sound;
                }

                @Override
                public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                    View view = layoutInflater.inflate(R.layout.category_list_item_1, parent, false);
                    return new SoundHolder(view);
                }

                @Override
                public void onBindViewHolder(SoundHolder holder, int position) {
                    Sound s = sounds.get(position);
                    holder.bindData(s);
                }

                @Override
                public int getItemCount() {
                    return sounds.size();
                    /**
                     * Clean up the media player by releasing its resources.
                     */

    private void releaseMediaPlayer() {
                        // If the media player is not null, then it may be currently playing a sound.
                    // if (mMediaPlayer != null) {
                    // Regardless of the current state of the media player, release its resources
                    // because we no longer need it.
                    mMediaPlayer.release();

                    // Set the media player back to null. For our code, we've decided that
                    // setting the media player to null is an easy way to tell that the media player
                    // is not configured to play an audio file at the moment.
                    mMediaPlayer = null;
                    mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
                }

            }

            }


        }
    }
}}