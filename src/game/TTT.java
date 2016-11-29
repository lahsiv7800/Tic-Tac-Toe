package game;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TTT extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BOARD_SIZE = 3;
	private static final String CROSS_TEXT = "X";
	private static final String ZERO_TEXT = "0";
	
	private static final int X_WINS = 0;
	private static final int ZERO_WINS = 1;
	private static final int TIE = 2;
	private static final int INCOMPLETE = 3;
	
	private JButton[][] board = new JButton[BOARD_SIZE][BOARD_SIZE]; 
	private boolean crossTurn;
	
	public TTT() 
	{
		super("Tic Tac Tao");
		super.setVisible(true);
		super.setSize(600,600);
		super.setResizable(false);
		
		GridLayout layout = new GridLayout(BOARD_SIZE,BOARD_SIZE);
		super.setLayout(layout);
		
		for(int row=0;row < BOARD_SIZE;row++)
		{
			for(int column=0;column < BOARD_SIZE;column++)
			{
					JButton button = new JButton();
					button.setFont(new Font("Times New Roman",1,225));
					button.addActionListener(this);
					this.board[row][column] = button;
					super.add(button);
			}
		}
		 
	}
	
	public void startGame()
	{
		crossTurn = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton clickedButton = (JButton)e.getSource();
		
		if(clickedButton.getText().equals(""))
		{
			makeMove(clickedButton);
			int gameStatus = getGameStatus();
			
			if(gameStatus == INCOMPLETE)
			{
				//change Turn
				crossTurn = !crossTurn;
				
				super.setTitle(crossTurn ? "X's turns":"Y's turns");
			}
			else
			{
				declareResult(gameStatus);
				dispose();
			}
		}
		
		else
		{
			JOptionPane.showMessageDialog(null,"Invalid Move");
		}
			
		
	}
	
	private void makeMove(JButton clickedButton)
	{
		String message = crossTurn ? CROSS_TEXT: ZERO_TEXT;
		clickedButton.setText(message);
	}
	

	private int getGameStatus()
	{
		int row=0,col=0;
		String text1 = "",text2="";
		
		//rows check
		row=0;
		col=0;
		for(row=0; row < BOARD_SIZE; row++)
		{
			col=0;
			while(col<BOARD_SIZE-1)
			{
				text1 = this.board[row][col].getText();
				text2 = this.board[row][col+1].getText();
				
				if(text1.equals("")|| !text1.equals(text2))
				{
					break;
				}
				else
				{
					col++;
				}
			}
			
			if(col == BOARD_SIZE-1)
			{
				return text1.equals(ZERO_TEXT) ? ZERO_WINS:X_WINS;
			}
			
		}
		
		//cols
		row=0;
		col=0;
		
		for(col=0;col<BOARD_SIZE;col++)
		{
			row=0;
			
			while(row<BOARD_SIZE-1)
			{
				text1 = this.board[row][col].getText();
				text2 = this.board[row+1][col].getText();
				
				if(text1.equals("")|| !text1.equals(text2))
				{
					break;
				}
				else
				{
					row++;
				}
				
				if(row==BOARD_SIZE-1)
				{
					return text1.equals(ZERO_TEXT) ? ZERO_WINS:X_WINS;
				}
			}
			
		}
		
		
		//Diagonal 1
		row =0;
		col=0;
		 while(row<BOARD_SIZE-1)
		  {
			  text1 = this.board[row][col].getText();
			  text2 = this.board[row+1][col+1].getText();
			  
			  if(text1.equals("") || !text1.equals(text2))
			  {
				  break;
			  }
			  else
			  {
				  row++;
				  col++;
			  }
			
			  if(row == BOARD_SIZE-1)
			  {
				  return text1.equals(CROSS_TEXT)? X_WINS:ZERO_WINS;
			  }
		  }
		
		//Diagonal 2
			row =0;
			col=2;
			 while(row<BOARD_SIZE-1)
			  {
				  text1 = this.board[row][col].getText();
				  text2 = this.board[row+1][col-1].getText();
				  
				  if(text1.equals("") || !text1.equals(text2))
				  {
					  break;
				  }
				  else
				  {
					  row++;
					  col--;
				  }
				
				  if(row == BOARD_SIZE-1)
				  {
					  return text1.equals(CROSS_TEXT)? X_WINS:ZERO_WINS;
				  }
			  }
			 
			 //Test Incomplete
			 row=0;
			 col=0;
			 
			 for(row=0;row < BOARD_SIZE;row++)
			 {
				 for(col=0;col < BOARD_SIZE;col++)
				 {
					 if(this.board[row][col].getText().equals(""))
						 
					 {
						 return INCOMPLETE;
					 }
					 
				 }
			 }
			 
			 //TIE
			 return TIE;
		}
    
	public void declareResult(int gameStatus)
	{
		if(gameStatus == X_WINS)
		{
			JOptionPane.showMessageDialog(null,"X wins");
		}
		
		else if(gameStatus == ZERO_WINS)
		{
			JOptionPane.showMessageDialog(null,"Zero Wins");
		}
		
		else
		{
			JOptionPane.showMessageDialog(null,"Tie");
			
		}
	}
}



 