package projlab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Window extends JFrame {

//	JPanel Panel=new JPanel();

	String [][] DrawKey;

	private int XPos = 0;
	private int YPos = 26;
	
	private int MapX = Map.x * 100;
	private int MapY = Map.y * 100 + 26;
	
	public Boolean ShowMenu = false;

	private InputHandlerInterface window;
        
	/**
     * Be√°ll√≠tja a param√©terben kapott interf√©szt tagv√°ltoz√≥k√©nt
     * @param iface 
     */
    public void setInputHandler(InputHandlerInterface iface)
    {
        window = iface;
    }
    /**
     * A fel√ºletre egy Alert Dialogot dob fel a megadott param√©terrel
     * @param msg 
     */
    public void showAlert(String msg)
    {
        JOptionPane.showMessageDialog(null, msg);
    }
    
	public Window(String [][] s)
	{
		super("Sokoban");
		setSize(MapX, MapY);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		DrawKey = s;
		setVisible(true);
        KeyEventDispatcher dispatcher = new KeyEventDispatcher() {
        	@Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
        		if (e.getID() == KeyEvent.KEY_TYPED) {
        			window.DispatchInput(e.getKeyChar());
        		}
        		return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(dispatcher);
	}

	@Override
	public void paint(Graphics g)
	{
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		if (ShowMenu)
		{
			g.fillRect(0, 0, MapX, MapY);
			g.setColor(Color.BLACK);
			g.drawString("Q - J·tÈk folytat·sa", 100, 100);
			g.drawString("N - ⁄j j·tÈk indÌt·sa", 100, 130);
		}
		else
		{
			for (int i = 0; i < Map.x; i++)
			{
				for (int j = 0; j < Map.y; j++)
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
	
					g.fillRect(XPos + 10, YPos + 10, 80, 80);
	
					switch(DrawKey[i][j].charAt(2))
					{
					case 'B':
						g.setColor(Color.ORANGE);
						g.fillOval(XPos + 10, YPos + 10, 80, 80);
						break;
					case 'W':
						g.setColor(Color.RED);
						g.fillOval(XPos + 10, YPos + 10, 80, 80);
						break;
					default:
						break;
					}
					YPos += 100;
				}
				YPos = 26;
				XPos += 100;
			}
			XPos = 0;
		}
	}
	public void close()
	{
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
