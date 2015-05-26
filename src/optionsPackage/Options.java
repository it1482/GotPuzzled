package optionsPackage;

public class Options {
	private boolean music;
	private boolean soundEffects;
	private boolean fullScreen;
	
	public Options(boolean music, boolean soundEffects,
			boolean fullScreen) {
		this.music = music;
		this.soundEffects = soundEffects;
		this.fullScreen = fullScreen;
	}
	public boolean isMusic() {
		return music;
	}
	public void setMusic(boolean music) {
		this.music = music;
	}
	public boolean isSoundEffects() {
		return soundEffects;
	}
	public void setSoundEffects(boolean soundEffects) {
		this.soundEffects = soundEffects;
	}
	public boolean isFullScreen() {
		return fullScreen;
	}
	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}
	
	
}
