package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Tile {

	protected Board _board;

	public Portal(int x, int y, Board board,Sprite sprite) {
		super(x, y, sprite);
		_board = board;
	}
	
	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý khi Bomber đi vào

		if (e instanceof Bomber  && _board.detectNoEnemies()) _board.nextLevel();
//		System.out.println(_board.detectNoEnemies());

		return false;
	}

}
