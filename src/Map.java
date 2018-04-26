import java.awt.Graphics;

public class Map {
	private Grid[][] map;
	private int rows, columns;
	private int width, height;
	private int xPos, yPos;
	
	public Map(){
		xPos = 0;
		yPos = 0;
		rows = 3;
		columns = 3;
//		map = new Grid[rows][columns];
		map = makeMap(rows, columns);
	}
	
	public Grid[][] makeMap(int rows, int columns){
		Grid[][] map = new Grid[rows][columns];
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				map[row][column] = new Grid(this, column, row);
				if(row == 0){
					for(int i = 0; i < map[row][column].getColumns(); i++){
						map[row][column].getBlock(i, 0).fill(true);
					}
				}
				if(row == map[row].length - 1){
					for(int i = 0; i < map[row][column].getColumns(); i++){
						map[row][column].getBlock(i, map[row][column].getRows() - 1).fill(true);
					}
				}
				if(column == 0){
					for(int i = 0; i < map[row][column].getRows(); i++){
						map[row][column].getBlock(0, i).fill(true);
					}
				}
				if(column == map.length - 1){
					for(int i = 0; i < map[row][column].getRows(); i++){
						map[row][column].getBlock(map[row][column].getColumns() - 1, i).fill(true);
					}
				}
				
			}
		}
		return map;
	}
	
	public void reset(){
		for(int row = 0; row < rows; row++){
			for(int column = 0; column < columns; column++){
				map[row][column].reset();
				if(row == 0){
					for(int i = 0; i < map[row][column].getColumns(); i++){
						map[row][column].getBlock(i, 0).fill(true);
					}
				}
				if(row == map[row].length - 1){
					for(int i = 0; i < map[row][column].getColumns(); i++){
						map[row][column].getBlock(i, map[row][column].getRows() - 1).fill(true);
					}
				}
				if(column == 0){
					for(int i = 0; i < map[row][column].getRows(); i++){
						map[row][column].getBlock(0, i).fill(true);
					}
				}
				if(column == map.length - 1){
					for(int i = 0; i < map[row][column].getRows(); i++){
						map[row][column].getBlock(map[row][column].getColumns() - 1, i).fill(true);
					}
				}
				
			}
		}
	}
	
	public void setXPos(int x){
		xPos = x;
	}
	
	public void setYPos(int y){
		yPos = y;
	}
	
	public int getXPos(){
		return xPos;
	}
	
	public int getYPos(){
		return yPos;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns(){
		return columns;
	}
	
	public Grid getRoom(int x, int y){
		return map[y][x];
	}
	
	public void draw(Graphics myBuffer){
		map[xPos][yPos].draw(myBuffer);
	}
}
