package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;

import java.net.URL;

public class Bomb extends AnimatedEntitiy {

	protected double _timeToExplode = 120; //2 seconds
	public int _timeAfter = 20;
	
	protected Board _board;
	protected Flame[] _flames;
	protected boolean _exploded = false;
	protected boolean _allowedToPassThru = true;
	
	public Bomb(int x, int y, Board board) {
		_x = x;
		_y = y;
		_board = board;
		_sprite = Sprite.bomb;
	}
	
	@Override
	public void update() {
		if(_timeToExplode > 0) 
			_timeToExplode--;
		else {
			if(!_exploded) 
				explosion();
			else
				updateFlames();
			
			if(_timeAfter > 0) 
				_timeAfter--;
			else
				remove();
		}
			
		animate();
	}
	
	@Override
	public void render(Screen screen) {
		if(_exploded) {
			_sprite =  Sprite.bomb_exploded2;
			renderFlames(screen);
		} else
			_sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
		
		int xt = (int)_x << 4;
		int yt = (int)_y << 4;
		
		screen.renderEntity(xt, yt , this);
	}
	
	public void renderFlames(Screen screen) {
		for (int i = 0; i < _flames.length; i++) {
			_flames[i].render(screen);
		}
	}
	
	public void updateFlames() {

		for (int i = 0; i < _flames.length; i++) {
			_flames[i].update();
		}
	}

    /**
     * Xử lý Bomb nổ
     */
	public void explode() {
		_timeToExplode = 0;
	}
	protected void explosion() {
		_allowedToPassThru = true;
		_exploded = true;
		URL resource = getClass().getResource("/music/BombBlastSound.mp3");

		_board.playSound(resource.getPath());
		
		// TODO: xử lý khi Character đứng tại vị trí Bomb
        int x = (int)_x;
        int y = (int)_y;

//        Character character = _board.getCharacterAtExcluding(x,y,null);
//        if (character != null) character.kill();
		Entity entity = _board.getEntityAt(x,y);
		if (entity != null) {
			if (entity instanceof Character) ((Character) entity).kill();
//			if (entity instanceof Bomb) ((Bomb) entity).explode();
//			if (entity instanceof Bomb) System.out.println("bom 2");;
		}


				// TODO: tạo các Flame

		_flames = new Flame[4];

        for (int i =0 ; i <_flames.length ; i ++)
            _flames[i] = new Flame(x,y,i, Game.getBombRadius(),_board);



	}
	
	public FlameSegment flameAt(int x, int y) {
		if(!_exploded) return null;
		
		for (int i = 0; i < _flames.length; i++) {
			if(_flames[i] == null) return null;
			FlameSegment e = _flames[i].flameSegmentAt(x, y);
			if(e != null) return e;
		}
		
		return null;
	}

	@Override
	public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi ra sau khi vừa đặt bom (_allowedToPassThru)
        // TODO: xử lý va chạm với Flame của Bomb khác
		_allowedToPassThru = true;
        if(e instanceof Bomber) {
            double x = e.getX() - Coordinates.tileToPixel(getX());
            double y = e.getY() - Coordinates.tileToPixel(getY());

            if(!(x >= -10 && x< 16 && y >= 1 && y <= 28)) {
                _allowedToPassThru = false;
            }else return _allowedToPassThru;
        }

        if(e instanceof Flame) {
			System.out.println("s");
        	explode();
            return true;
        }
		if(e instanceof Enemy) {
			return false;
		}
        return true;
	}
}
