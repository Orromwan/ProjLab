package projlab;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class Window extends JFrame {

//	JPanel Panel=new JPanel();

	String [][] DrawKey;

	private int XPos=0;

	private int YPos=26;

	Window(String [][] s)
	{
		super("Sokoban");
		setSize(Map.x*100, Map.y*100+26);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		DrawKey=s;
		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		for(int i=0; i<Map.x; i++)
		{
			for(int j=0; j<Map.y; j++)
			{
				switch(DrawKey[i][j].charAt(0))
				{
				case '_':
					g.setColor(Color.GREEN);
					break;
				case ':':
					g.setColor(Color.YELLOW);
					break;
				default:
					g.setColor(Color.LIGHT_GRAY);
					break;
				}

				g.fillRect(XPos, YPos, 100, 100);

				switch(DrawKey[i][j].charAt(1))
				{
				case 'o':
					g.setColor(Color.GRAY);
					break;
				case 'O':
					g.setColor(Color.BLACK);
					break;
				case 'L':
					g.setColor(Color.BLUE);
					break;
				case 'X':
					g.setColor(Color.DARK_GRAY);
					break;
				case '!':
					g.setColor(Color.WHITE);
					break;
				default:
					g.setColor(Color.LIGHT_GRAY);
					break;
				}

				g.fillRect(XPos+10, YPos+10, 80, 80);

				switch(DrawKey[i][j].charAt(2))
				{
				case 'B':
					g.setColor(Color.ORANGE);
					g.fillOval(XPos+10, YPos+10, 80, 80);
					break;
				case 'W':
					g.setColor(Color.RED);
					g.fillOval(XPos+10, YPos+10, 80, 80);
					break;
				default:
					break;
				}
				YPos+=100;
			}
			YPos=26;
			XPos+=100;
		}
		XPos=0;	
	}
}
