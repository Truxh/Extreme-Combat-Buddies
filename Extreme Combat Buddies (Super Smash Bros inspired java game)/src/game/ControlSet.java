package game;

public class ControlSet {
	public int left;
	public int right;
	public int jump;
	public int crouch;
	public int upTele;
	public int downTele;
	public int fire;
	public int burst;
	
	public ControlSet(int left, int right, int jump, int crouch, int upTele, int downTele, int fire, int burst) {
		this.left = left;
		this.right = right;
		this.jump = jump;
		this.crouch = crouch;
		this.upTele = upTele;
		this.downTele = downTele;
		this.fire = fire;
		this.burst = burst;
	}
	
	public ControlSet(int[] keys) {
		this.left = keys[0];
		this.right = keys[1];
		this.jump = keys[2];
		this.crouch = keys[3];
		this.upTele = keys[4];
		this.downTele = keys[5];
		this.fire = keys[6];
		this.burst = keys[7];
	}
	
	public ControlSet(SpriteAI spriteai, boolean confirm) {
		
	}
	
}
