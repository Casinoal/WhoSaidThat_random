package com.example.randomimages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

 private static final int MAX_SIZE_OF_ALL_POOL = 25; //how many sounds in the game
 final private int SIZE_OF_SCENE = 5; //how many to pull into the scene

 	MediaPlayer right;
 	MediaPlayer who;
	MediaPlayer wrong;
	int file;
	int imagefile;
	String listen;
	
 TextView textViewSound;
 TextView textViewAnswer;
 TextView textViewLeftSize;
 boolean isWaitingForAnswer = false;
 Button button1;
 final ImageView imageView[] = new ImageView[SIZE_OF_SCENE];

 final Random random = new Random();
 int indexOfUserAnswer;
 int indexOfSound;

 private static List<ImageSoundItem> poolOfImagesAndSounds;
 List<ImageSoundItem> scenePool;
 List<ImageSoundItem> temporaryPool;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);

  textViewSound = (TextView) findViewById(R.id.textViewSound);
  
  textViewAnswer = (TextView) findViewById(R.id.textViewAnswer);
  textViewLeftSize = (TextView) findViewById(R.id.textViewLeftSize);
  
  button1 = (Button) findViewById(R.id.button1);
  
  imageView[0] = (ImageView) findViewById(R.id.imageView1);
  imageView[1] = (ImageView) findViewById(R.id.imageView2);
  imageView[2] = (ImageView) findViewById(R.id.imageView3);
  imageView[3] = (ImageView) findViewById(R.id.imageView4);
  imageView[4] = (ImageView) findViewById(R.id.imageView5);

  populatePool(MAX_SIZE_OF_ALL_POOL);
  Log.i("onCreate", poolOfImagesAndSounds.toString());

  generateScene();
  chooseIndexOfSound(scenePool);
  showScene();

  OnClickListener onClickListener = new OnClickListener() {

   @Override
   public void onClick(View arg0) {
    switch (arg0.getId()) {
    case R.id.imageView1:
     indexOfUserAnswer = 0;
     Log.i("onClick", "textView1");
     break;
    case R.id.imageView2:
     indexOfUserAnswer = 1;
     Log.i("onClick", "textView2");
     break;
    case R.id.imageView3:
     indexOfUserAnswer = 2;
     Log.i("onClick", "textView3");
     break;
    case R.id.imageView4:
     indexOfUserAnswer = 3;
     Log.i("onClick", "textView4");
     break;
    case R.id.imageView5:
     indexOfUserAnswer = 4;
     Log.i("onClick", "textView5");
     break;
    case R.id.button1:
     generateScene();
     chooseIndexOfSound(scenePool);
     showScene();
     textViewAnswer.setText(null);
     //this part will end game if pool is too small - reference also like 130
     if (poolOfImagesAndSounds.size() - 1 < SIZE_OF_SCENE) {
      button1.setEnabled(false);
      button1.setText("The end of Game :)");
     }
     return;
    default:
     return;

    }

    doSomethingAfterUserAnswer();

   }
  };

  for (int i = 0; i < SIZE_OF_SCENE; i++) {
   imageView[i].setOnClickListener(onClickListener);
  }
  button1.setOnClickListener(onClickListener);

 }

 private void showScene() {
  	 
	 //textViewSound.setText(scenePool.get(indexOfSound).getSound()); //playing sound here
	 
	 textViewSound.setText("Listen!");
	 listen = scenePool.get(indexOfSound).getSound();
	 file = (this.getResources().getIdentifier(listen, "raw", this.getPackageName()));
	 //Log.i("intro", "listen!");
	//play the random animal noise 
		
	who = MediaPlayer.create(this, file);
	who.start();
	 
  
    for (int i = 0; i < SIZE_OF_SCENE; i++) {
    	
    	  	
    imagefile = (this.getResources().getIdentifier((scenePool.get(i).getImage()), "drawable", this.getPackageName()));
   imageView[i].setImageResource(imagefile); //dynamic show x images (scene pool)

  

   
  }
  isWaitingForAnswer = true;

 }

 private void doSomethingAfterUserAnswer() {
  Log.i("doSomethingAfterUserAnswer","before:"+poolOfImagesAndSounds.size()+"isWaitingForAnswer="+isWaitingForAnswer);

  if (indexOfSound != indexOfUserAnswer) {
   Log.i("doSomethingAfterUserAnswer", "Wrong answer!");
   textViewAnswer.setText("Wrong answer!"); //play sound wrong answer
   
   wrong = MediaPlayer.create(this, R.raw.notright);
		   wrong.start();	   
   
  } else {
   Log.i("doSomethingAfterUserAnswer", "Right answer!");
   textViewAnswer.setText("Right answer!");
   right = MediaPlayer.create(this, R.raw.right);
		   right.start();
   if (isWaitingForAnswer) {
    Log.i("doSomethingAfterUserAnswer","removing");
    poolOfImagesAndSounds.remove(scenePool.get(indexOfSound) //can comment out here, and will keep pool the same.  If active, this will reduce pool with each correct answer
      .getIndex());
   }
  }
  textViewLeftSize.setText("left:" + poolOfImagesAndSounds.size());
  isWaitingForAnswer = false;
  Log.i("doSomethingAfterUserAnswer","after:"+poolOfImagesAndSounds.size());
 }

 
 //edit here with real image and sound locations.
 private void populatePool(int quantity) {
	  poolOfImagesAndSounds = new ArrayList<ImageSoundItem>();
	  poolOfImagesAndSounds.add(new ImageSoundItem("Bear", "bear"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Cat", "cat"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Chicken", "chicken"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Cougar", "cougar"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Cow", "cow"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Dog", "dog"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Duck", "duck"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Elephant", "elephant"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Frog", "frog"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Hyena", "hyena"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Lion", "lion"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Monkey", "monkey"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Mosquito", "mosquito"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Owl", "owl"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Parrot", "parrot"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Pig", "pig"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Rhino", "rhino"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Rooster", "rooster"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Snake", "snake"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Sheep", "sheep"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Songbird", "bird"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Tiger", "tiger"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Wolf", "wolf"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Woodpecker", "woodpecker"
			  ));
	  poolOfImagesAndSounds.add(new ImageSoundItem("Zebra", "zebra"
			  ));

 }
 
 //this will pull sound/image pairs from a resource XML file
 //poolOfImagesAndSounds.add(new ImageSoundItem(imageNameStringArray[i], soundNameStringArray[i]);

 synchronized private void generateScene() {

  scenePool = new ArrayList<ImageSoundItem>(SIZE_OF_SCENE);
  temporaryPool = new ArrayList<ImageSoundItem>(SIZE_OF_SCENE);

  ImageSoundItem oneItem;
  for (int i = 0; i < poolOfImagesAndSounds.size(); i++) {
   oneItem = poolOfImagesAndSounds.get(i);
   oneItem.setIndex(i);
   temporaryPool.add(oneItem);
  }

  int randomIndex;
  for (int i = 0; i < SIZE_OF_SCENE; i++) {
   // temporaryPool.size() exclusive (from 0 to temporaryPool.size())
   randomIndex = random.nextInt(temporaryPool.size());
   oneItem = temporaryPool.get(randomIndex);
   scenePool.add(oneItem);
   Log.i("remove", temporaryPool.get(randomIndex).getImage()
     + ", size=" + temporaryPool.size());
   temporaryPool.remove(randomIndex);
  }

  
  
 }
//this is correct answer selected - and sound index
 private void chooseIndexOfSound(final List<ImageSoundItem> scenePool) {
  Log.i("onCreate", "choosed sound with index=" + indexOfSound + ", "
    + scenePool.get(indexOfSound).getSound());
  indexOfSound = random.nextInt(scenePool.size());
 }
}