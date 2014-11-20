package com.stjerndal.androidgames;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	public static final int UP = 3;
	public static final int LEFT = 2;
	public static final int DOWN = 1;
	public static final int RIGHT = 0;

	public List<SnakePart> parts = new ArrayList<SnakePart>();
	public int direction;

	public Snake() {
		direction = UP;
		parts.add(new SnakePart(5, 6));
		parts.add(new SnakePart(5, 7));
		parts.add(new SnakePart(5, 8));
	}

	public void turnCounterClockwise() {
		int nextDirection = direction - 1;
		if (nextDirection < RIGHT)
			nextDirection = UP;
		if (!isOneEighty(nextDirection)) {
				direction = nextDirection;
		}
	}

	public void turnClockwise() {
		int nextDirection = direction + 1;
		if (nextDirection > UP)
			nextDirection = RIGHT;
		if (!isOneEighty(nextDirection)) {
			direction = nextDirection;
			
		}
	}
	
	public void turnLeft() {
		int nextDirection = LEFT;
		if(direction == UP || direction == DOWN) {
			if (!isOneEighty(nextDirection)) {
				direction = nextDirection;
				
			}
		}
	}
	
	public void turnRight() {
		int nextDirection = RIGHT;
		if(direction == UP || direction == DOWN) {
			if (!isOneEighty(nextDirection)) {
				direction = nextDirection;
				
			}
		}
	}
	
	public void turnUp() {
		int nextDirection = UP;
		if(direction == LEFT || direction == RIGHT) {
			if (!isOneEighty(nextDirection)) {
				direction = nextDirection;
				
			}
		}
	}
	
	public void turnDown() {
		int nextDirection = DOWN;
		if(direction == LEFT || direction == RIGHT) {
			if (!isOneEighty(nextDirection)) {
				direction = nextDirection;
				
			}
		}
	}

	public void eat() {
		SnakePart end = parts.get(parts.size() - 1);
		parts.add(new SnakePart(end.x, end.y));
	}

	public void advance() {
		SnakePart head = parts.get(0);

		int len = parts.size() - 1;
		SnakePart before;
		SnakePart part;
		for (int i = len; i > 0; i--) {
			before = parts.get(i - 1);
			part = parts.get(i);
			part.x = before.x;
			part.y = before.y;
		}

		if (direction == UP)
			head.y -= 1;
		if (direction == LEFT)
			head.x -= 1;
		if (direction == DOWN)
			head.y += 1;
		if (direction == RIGHT)
			head.x += 1;

		if (head.x < 0)
			head.x = World.WORLD_WIDTH - 1;
		if (head.x > World.WORLD_WIDTH - 1)
			head.x = 0;
		if (head.y < 0)
			head.y = World.WORLD_HEIGHT - 1;
		if (head.y > World.WORLD_HEIGHT - 1)
			head.y = 0;
	}

	public boolean checkBitten() {
		int len = parts.size();
		SnakePart head = parts.get(0);
		for (int i = 1; i < len; i++) {
			SnakePart part = parts.get(i);
			if (part.x == head.x && part.y == head.y)
				return true;
		}
		return false;
	}

	public boolean isOneEighty(int nextDirection) {
		SnakePart head = parts.get(0);
		SnakePart prevPart = parts.get(1);
		switch (nextDirection) {
		case UP:
			if (prevPart.x == head.x && prevPart.y == head.y - 1)
				return true;
			break;
		case LEFT:
			if (prevPart.x == head.x - 1 && prevPart.y == head.y)
				return true;
			break;
		case DOWN:
			if (prevPart.x == head.x && prevPart.y == head.y + 1)
				return true;
			break;
		case RIGHT:
			if (prevPart.x == head.x + 1 && prevPart.y == head.y)
				return true;
			break;
		}
		return false;
	}
}
