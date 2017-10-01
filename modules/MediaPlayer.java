package old80house.modules;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MediaPlayer {
    
    Clip music;

    // Load wav files from root folder
    public File empty;
    public File soundTheme = new File("sound/theme.wav");
    public File soundBeamgun = new File("sound/beamgun.wav");
    public File soundDripping = new File("sound/dripping.wav");
    public File soundChair = new File("sound/chair.wav");
    public File soundPiano = new File("sound/old-piano-rolls.wav");
    public File soundScary = new File("sound/scary3.wav");
    public File soundHallDoorSlam = new File("sound/door-stony-corridor.wav");
    public File soundOldClock = new File("sound/old-clock-pendulum-ticking.wav");
    public File soundNeonLamp = new File("sound/neon-lamp-on.wav");
    public File soundMedic = new File("sound/smallmedkit1.wav");
    public File soundPain = new File("sound/pain.wav");
    public File soundPills = new File("sound/pills.wav");
    public File soundSpookeywater = new File("sound/spookywater.wav");
    public File soundScaryEffect = new File("sound/scary-sound.wav");
    public File soundChildren = new File("sound/horror-kids-01.wav");
    public File soundStairsUp = new File("sound/newstairs.wav");
    public File soundScaryScream = new File("sound/scaryscream.wav");
    public File soundEndBoss = new File("sound/endboss.wav");

    // Generic mediaplayer
    public void PlaySound(File Sound, Boolean x) {

        if (x) {
            try {
                music = AudioSystem.getClip();
                music.open(AudioSystem.getAudioInputStream(Sound));
                
                
                music.setLoopPoints(0, -1);
                
                music.start();    
                music.loop(500);   //Loops the music 500 times....for ze slow players
                    
                
                //Thread.sleep(clip.getMicrosecondLength()/1000); // App wait for clip to end
            } catch (Exception e) {
                //System.out.println("Media error!");            //we acctually use this Exception to make sure theres no sound.
            }
        } else {
            try {
                Clip effect = AudioSystem.getClip();
                effect.open(AudioSystem.getAudioInputStream(Sound));
                effect.start();

                //Thread.sleep(clip.getMicrosecondLength()/1000); // App wait for clip to end
            } catch (Exception e) {
                //System.out.println("Media error!");
            }
        }

    }

    public void stopMusic() {
        music.stop();
        
    }

}
