package apps.arw.whosaidthatexpansion;

public class ImageSoundItem {
	private String image;
	private String sound;
	private int index;

	ImageSoundItem() {

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	ImageSoundItem(String imageName, String soundName) {
		this.image = imageName;
		this.sound = soundName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	@Override
	public String toString() {
		return "[" + image + ", " + sound + "]";
	}

}
