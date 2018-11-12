package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.character.enemy.Oneal;

public class AIMedium extends AI {
	Bomber _bomber;
	Enemy _e;
	
	public AIMedium(Bomber bomber, Enemy e) {
		_bomber = bomber;
		_e = e;
	}
	public AIMedium() {

	}
	@Override
	public int calculateDirection() {
		int x_bomber =_bomber.getXTile();
		int y_bomber =_bomber.getYTile();
		int x_oneal = _e.getXTile();
		int y_oneal = _e.getYTile();
		int x = x_oneal-x_bomber;
		int y = y_oneal-y_bomber;
		if (x*x -y*y >0) {
			if (x < 0) return 1;
			if (x > 0) return 3;
		}
		else {
			if (y > 0) return 0;
		}
		return 2;
	}


}
