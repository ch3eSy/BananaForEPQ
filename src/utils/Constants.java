package utils;

public class Constants {
	
	public static class Directions{
		public static final int LEFT = 0;		
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN= 3;
	}

	public static class PlayerConstants{
		
		public static final int attack = 0;
		public static final int idle = 1;
		public static final int runningleft = 3;
		public static final int runningright =2;
		public static final int jumpingleft = 4;
		public static final int jumpingright = 5;
		public static final int attackright = 7;
		
		
		public static int GetSpriteAmount(int player_action) {
			
			switch(player_action) {
			case runningright:
				return 5;
			case runningleft:
				return 5;
			case idle:
				return 5;
			case attack:
				return 5;
			case attackright:
				return 5;
			case jumpingleft:
				return 5;
			case jumpingright:
				return 5;
			default:
				return 1;
			}
			
		}
	}

	public static class enemyConstants{
		
		public static final int attack = 0;
		public static final int idle = 1;
		public static final int runningright =3;
		public static final int runningleft = 2;
		
		
		public static int GetSpriteLength(int enemy_action) {
			
			switch(enemy_action) {
			case runningright:
				return 5;
			case runningleft:
				return 5;
			case idle:
				return 5;
			case attack:
				return 5;
			default:
				return 1;
			}
			
		}
	}
	
	public static class spikeConstants{
		
		public static final int up = 0;
		public static final int down = 2;
		public static final int left = 3;
		public static final int right = 1;
		
		public static int GetSpriteLength(int enemy_action) {
			
			switch(enemy_action) {
			case up:
				return 5;
			case down:
				return 5;
			case left:
				return 5;
			case right:
				return 5;
			default:
				return 1;
			}
			
		}
	}
}
