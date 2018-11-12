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

	@Override
	public int calculateDirection() {
		return 1;
	}
	public int calculateDirectionOneal(Oneal oneal) {
		// TODO: cài đặt thuật toán tìm đường đi
		int x_bomber =_bomber.getXTile();
		int y_bomber =_bomber.getYTile();
		int x_oneal = oneal.getXTile();
		int y_oneal = oneal.getYTile();
		return 1;
	}

}
