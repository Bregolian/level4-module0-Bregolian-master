package _03_Conways_Game_of_Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;

	private Timer timer;

	// 1. Create a 2D array of Cells. Do not initialize it.
	Cell[][] cells;

	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;

		// 2. Calculate the cell size.
		cellSize = h / cpr;
		// 3. Initialize the cell array to the appropriate size.
		cells = new Cell[cpr][cpr];
		// 3. Iterate through the array and initialize each cell.
		// Don't forget to consider the cell's dimensions when
		// passing in the location.
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				cells[row][col] = new Cell(col * cellSize, row * cellSize, cellSize);
			}
		}
	}

	public void randomizeCells() {
		// 4. Iterate through each cell and randomly set each
		// cell's isAlive memeber to true of false
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				if ((int) (Math.random() * 2) == 1) {
					cells[row][col].isAlive = false;
				} else {
					cells[row][col].isAlive = false;
				}
			}
		}
		repaint();
	}

	public void clearCells() {
		// 5. Iterate through the cells and set them all to dead.
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				cells[row][col].isAlive = false;
			}
		}
		repaint();
	}

	public void startAnimation() {
		timer.start();
	}

	public void stopAnimation() {
		timer.stop();
	}

	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}

	@Override
	public void paintComponent(Graphics g) {
		// 6. Iterate through the cells and draw them all
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				cells[row][col].draw(g);
			}
		}
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	// advances world one step
	public void step() {
		// 7. iterate through cells and fill in the livingNeighbors array
		// . using the getLivingNeighbors method.
		int[][] livingNeighbors = new int[cellsPerRow][cellsPerRow];
		for (int row = 0; row < cells.length; row++) {
			for (int col = 0; col < cells[0].length; col++) {
				livingNeighbors[row][col] = getLivingNeighbors(row, col);
				cells[row][col].liveOrDie(livingNeighbors[row][col]);
			}
		}
		// 8. check if each cell should live or die

		repaint();
	}

	// 9. Complete the method.
	// It returns an int of 8 or less based on how many
	// living neighbors there are of the
	// cell identified by x and y
	public int getLivingNeighbors(int x, int y) {
		int output = 0;
		if (x > 0) {
			if (cells[x - 1][y].isAlive) {
				output++;
			}
			if (y > 0) {
				if (cells[x - 1][y - 1].isAlive) {
					output++;
				}
				if (cells[x][y - 1].isAlive) {
					output++;
				}
			}
		}
		if (y > 0) {
			if (x < 49) {
			if (cells[x + 1][y - 1].isAlive) {
				output++;
			}
			}
		}
		if (x < 49) {
		if (cells[x + 1][y].isAlive) {
			output++;
		}
		}
		if (y < 49) {
			if (x < 49) {
		if (cells[x + 1][y + 1].isAlive) {
			output++;
		}
			}
		}
		if (y < 49) {
		if (cells[x][y + 1].isAlive) {
			output++;
		}
		}
		if (x > 0) {
			if (y < 49) {
				if (cells[x - 1][y + 1].isAlive) {
					output++;
				}
			}
		}
		return output;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 10. Use e.getX() and e.getY() to determine
		// which cell is clicked. Then toggle
		// the isAlive variable for that cell.
		if (cells[e.getY() / cellSize][e.getX() / cellSize].isAlive) {
			cells[e.getY() / cellSize][e.getX() / cellSize].isAlive = false;
		} else {
			cells[e.getY() / cellSize][e.getX() / cellSize].isAlive = true;
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();
	}
}
