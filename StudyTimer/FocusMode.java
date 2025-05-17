package StudyTimer;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Random;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class FocusMode implements Runnable{
	private int focusTime;
	private int breakTime;
	Random random = new Random();
	private final String [] Motivation = {
										 "Mom trusts your dreams, and so should you. Every minute is precious—make it count 👩‍👦",
										 "No one else will walk your journey or build your future. The responsibility is yours alone embrace it ⏳",
										 "Education is the key that opens every door. Invest in it now, and watch your world expand  🚶‍♂️",
										 "Lord Krishna says: ‘Come, study with courage. I am always by your side.’  🎯",
										 "Yes, you may feel hurt or tired, but remember: you are the architect of your tomorrow 🎓",
										 "Time is the one resource you cannot reclaim—guard it, respect it, and use it wisely 🙏",
										 "Your pain is real, but your potential is greater. Rise and claim the future waiting for you 💪",
										 "In this moment, choose focus over distraction. Your future self will thank you  🔥",
										 "Mom believes in you, the universe supports you, and now it’s time for you to believe in yourself 👣",
										 "The hardest battles are the ones that shape us most. Keep going—your story is just beginning 🌟",
										 };
	
	int generatedQuote = random.nextInt(1,11);
	
	public FocusMode(int focusTime, int breakTime) {
		this.focusTime = focusTime;
		this.breakTime = breakTime;
	}

	public void run() {
		System.out.println();
		System.out.println(Motivation[generatedQuote]);
		TimerSet(focusTime, "FOCUS");
	}
	
	public void TimerSet(int time, String mode) {
		int elapsedSecond = 0;
		int totalSecond = time * 60;
		
		while(elapsedSecond < totalSecond) {
			int hrs = elapsedSecond / 3600;
			int min = (elapsedSecond % 3600) / 60;
			int sec = elapsedSecond % 60;
			
			System.out.printf("\r%02d:%02d:%02d",hrs, min, sec);
			try {
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) {
				System.out.println("Sorry the thread was interpted 😐");
			}
			elapsedSecond++;
		}
		
		System.out.println("\n__________________________________________________________________");
		AudioPlayer();
		System.out.println("\nCongragulations your " +mode+ " session has been successfully completed 😇");
		System.out.println("__________________________________________________________________");
		
		if(mode.equals("FOCUS")) {
			TimerSet(breakTime, "BREAK"); 
		}
	}
	
	public void AudioPlayer() {
		String path = System.getProperty("user.dir");
		String fullPath = path + "/src/StudyTimer/beep.wav";
	
		try {
			File file = new File(fullPath);
			AudioInputStream audio = AudioSystem.getAudioInputStream(file);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
				
			} 
			catch (LineUnavailableException e) {
				System.out.println("Sorry something went wrong in clip ❌");
			}
		} 
		catch (UnsupportedAudioFileException e) {
			System.out.println("Sorry the file format is not supported 🛡");
		} 
		catch (IOException e) {
			System.out.println("Sorry something went wrong ❌");
		}
		
	}
}

