/**
 * 
 * The GUI will hopefully make the front-end process much easier for the user
 * 
 * Bashir Kadri
 * Jan 13, 2021
 *
 */
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI implements ActionListener
{
	Library lib;
	
	//rules components
	JFrame rules;
	JTextArea rulesText;
	JButton rulesContinue;
	
	//intro components
	JFrame intro;
	JTextField introInfo;
	JPanel introSignUp;
	JButton introSignUpButton;
	JLabel introSignUpInfo;
	JPanel introLogIn;
	JButton introLogInButton;
	JLabel introLogInInfo;
	JLabel introLogInInfo2;
	//I know these names are confusing, but it's what I'm going with
	
	//signUp comps
	JFrame signUp;
	JLabel signUpName;
	JTextField signUpTextName;
	JLabel signUpAge;
	JTextField signUpTextAge;
	JButton signUpButton;
	
	//logIn comps
	JFrame logIn;
	JLabel logInText;
	JTextField logInEnterCard;
	JButton logInButton;
	
	//library comps
	JFrame library;
	JTextField librarySearch;
	JButton librarySearchButton;
	JButton librarySearchFilter;
	JTextArea libraryAccount;
	JButton libraryReturn;
	JTextArea libraryTextArea;
	JScrollPane libraryScroll;
	JButton libraryLogOut;
	
	private void checkOut(Media m, Person p)
	{	
		int response;
	
		//Check if the current Media is available or not
		if (m.availability) //is available
		{
			if (m.id == 3 && p.age < ((VideoGames) m).rating)
			{
				JOptionPane.showMessageDialog(null, "This rating is not compatible for your age!");
			}
			else
			{
				//If available, check if the Person has enough room to borrow the book
				if (p.possessions.size() < p.MAX_POSSESSIONS)
				{
					
					JOptionPane.showMessageDialog(null, "Information about item:\n" + m.toString());
					
					response = JOptionPane.showConfirmDialog(null, "Would you like to check out " + m.title);
					
					if (response == 0)
					{
						JOptionPane.showMessageDialog(null, "You have succesfully checked out " + m.title);
						p.possessions.add(m);
						m.availability = false;
						this.libraryAccount.setText(this.lib.currentUser.toString());
						this.librarySearch.setText("");
					}
					else if (response == 1 || response == 2) //nothing changes, give them some message
					{
						JOptionPane.showMessageDialog(null, m.title + " has not been checked out.");
					}
					
				}
				else //item is available, but their "cart" is full, give some message
				{
					JOptionPane.showMessageDialog(null, "Sorry, you have too many items currently checked out. Please try again another time.");
				}
			}
		}
		else if (this.lib.currentUser.findPossession(m)) //if the user already has current item
        {
			JOptionPane.showMessageDialog(null, "Sorry, you already have " + m.title + " checked out. You cannot currently put this on hold.");
        }
        else if (this.lib.currentUser.findRequest(m)) //if the user already requested for the current item
        {
        	JOptionPane.showMessageDialog(null, "Sorry, you are already waiting in line for  " + m.title + ". You cannot currently put this on hold.");
        }
		else //not available, put Person in queue
		{
			response = JOptionPane.showConfirmDialog(null, "Sorry, " + m.title + " is not available, would you like to put this item on hold?");
			
			//and then finally, if they answer yes, add them to queue, if no, do nothing
			if (response == 0)
			{
				JOptionPane.showMessageDialog(null, "You have succesfully been added to the queue for " + m.title);
				m.hold.enQueue(p);
				this.lib.currentUser.requests.add(m);
				this.libraryAccount.setText(this.lib.currentUser.toString());
				this.librarySearch.setText("");
			}
			else if (response == 1 || response == 2) //nothing changes, give them some message
			{
				JOptionPane.showMessageDialog(null, m.title + " has not been added to the queue.");					
			}
		}
	}
	
	public GUI(Library library)
	{
		this.lib = library;
		
		this.createRules();
	}
	
	private void createRules()
	{
		//when program is launched, rules frame should appear as a textbox with the rules and a continue button
		this.rules = new JFrame(this.lib.name);
		this.rules.setLayout(null);
		this.rules.setSize(650, 500);
		this.rules.setResizable(false);
		this.rules.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.rules.setVisible(true);
				
		this.rulesText = new JTextArea();
		this.rules.add(this.rulesText);
		this.rulesText.setText("                    Welcome to the library!\nAs your go through the library, many of the steps are \nexplained however, in the library you can:\n --> Create a new account or sign into your account,\n --> Search items by title or by filter,\n --> Put items on hold\n --> Return any items, and\n --> Log out of your account.\nHappy reading! ");
		this.rulesText.setEditable(false);
		this.rulesText.setLineWrap(true);
		this.rulesText.setLocation(25, 25);
		this.rulesText.setSize(600, 350);
		this.rulesText.setFont(new Font("Verdana", Font.PLAIN, 20));
		
		this.rulesContinue = new JButton("Continue");
		this.rules.add(this.rulesContinue);
		this.rulesContinue.addActionListener(this);
		this.rulesContinue.setSize(120, 50);
		this.rulesContinue.setLocation(265, 393);
		this.rulesContinue.setFont(new Font("Verdana", Font.BOLD, 20));
	}
	private void createIntro()
	{
		//okay this one turned out really bad and I don't wanna waste my time fixing every small thing so I'm leaving it like this for now**
		//probably shrink intro frame by a considerably portion (shrink vertically)
		intro = new JFrame(this.lib.name);
		this.intro.setLayout(null);
		this.intro.setSize(650, 300);
		this.intro.setResizable(false);
		this.intro.setVisible(true);
		this.intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.introInfo = new JTextField("Welcome to " + this.lib.name + ", Please choose an option");
		this.intro.add(this.introInfo);
		this.introInfo.setEditable(false);
		this.introInfo.setHorizontalAlignment(JTextField.CENTER);
		this.introInfo.setLocation(25, 30);
		this.introInfo.setSize(600, 25);
		this.introInfo.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		this.introSignUp = new JPanel();
		this.intro.add(this.introSignUp);
		this.introSignUp.setLayout(null);
		this.introSignUp.setSize(250, 250);
		this.introSignUp.setLocation(38, 35);
		
		this.introSignUpInfo = new JLabel("New User? Sign Up Here");
		this.introSignUp.add(this.introSignUpInfo);
		this.introSignUpInfo.setFont(new Font("Verdana", Font.ITALIC, 15));
		this.introSignUpInfo.setSize(200, 80);
		this.introSignUpInfo.setLocation(40, 25);
		
		this.introSignUpButton = new JButton("Sign Up");
		this.introSignUp.add(this.introSignUpButton);
		this.introSignUpButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.introSignUpButton.addActionListener(this);
		this.introSignUpButton.setSize(150, 40);
		this.introSignUpButton.setLocation(65, 110);
		
		this.introLogIn = new JPanel();
		this.intro.add(this.introLogIn);
		this.introLogIn.setLayout(null);
		this.introLogIn.setSize(250, 250);
		this.introLogIn.setLocation(362, 35);
		
		
		this.introLogInInfo = new JLabel("Already have an account?");
		this.introLogIn.add(this.introLogInInfo);
		this.introLogInInfo.setFont(new Font("Verdana", Font.ITALIC, 15));
		this.introLogInInfo.setSize(200, 40);
		this.introLogInInfo.setLocation(25, 25);
		
		this.introLogInInfo2 = new JLabel("Log In Here");
		this.introLogIn.add(this.introLogInInfo2);
		this.introLogInInfo2.setFont(new Font("Verdana", Font.ITALIC, 15));
		this.introLogInInfo2.setSize(200, 40);
		this.introLogInInfo2.setLocation(70, 65);
		
		
		this.introLogInButton = new JButton("Log In");
		this.introLogIn.add(this.introLogInButton);
		this.introLogInButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.introLogInButton.addActionListener(this);
		this.introLogInButton.setSize(150, 40);
		this.introLogInButton.setLocation(65, 110);
		
	}
	private void createSignUp()
	{
		//let user sign in, just takes their card num to sign in
		this.signUp = new JFrame(this.lib.name);
		this.signUp.setLayout(null);
		this.signUp.setSize(400, 250);
		this.signUp.setResizable(false);

		this.signUp.setVisible(true);
		this.signUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.signUpName = new JLabel("Enter Name:");
		this.signUp.add(this.signUpName);
		this.signUpName.setSize(240, 40);
		this.signUpName.setLocation(20, 20);
		this.signUpName.setFont(new Font("Verdana", Font.ITALIC, 15));
		
		this.signUpTextName = new JTextField();
		this.signUp.add(this.signUpTextName);
		this.signUpTextName.setSize(240, 40);
		this.signUpTextName.setLocation(20, 70);;
		this.signUpTextName.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		this.signUpAge = new JLabel("Enter Age:");
		this.signUp.add(this.signUpAge);
		this.signUpAge.setSize(240, 40);
		this.signUpAge.setLocation(20, 120);
		this.signUpAge.setFont(new Font("Verdana", Font.ITALIC, 15));
		
		this.signUpTextAge = new JTextField();
		this.signUp.add(this.signUpTextAge);
		this.signUpTextAge.setSize(240, 40);
		this.signUpTextAge.setLocation(20, 170);;
		this.signUpTextAge.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		this.signUpButton = new JButton("Confirm");
		this.signUp.add(this.signUpButton);
		this.signUpButton.addActionListener(this);
		this.signUpButton.setSize(100, 100);
		this.signUpButton.setLocation(280, 110);
		this.signUpButton.setFont(new Font("Verdana", Font.BOLD, 15));
	}
	private void createLogIn()
	{
		this.logIn = new JFrame(this.lib.name);
		this.logIn.setLayout(null);
		this.logIn.setSize(300, 200);
		this.logIn.setResizable(false);
		this.logIn.setVisible(true);
		this.logIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.logInText = new JLabel("Please Enter Your Card Number:");
		this.logIn.add(this.logInText);
		this.logInText.setSize(270, 40);
		this.logInText.setLocation(15, 10);
		this.logInText.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		this.logInEnterCard = new JTextField();
		this.logIn.add(this.logInEnterCard);
		this.logInEnterCard.setSize(200, 80);
		this.logInEnterCard.setLocation(15, 60);
		this.logInEnterCard.setFont(new Font("Verdana", Font.PLAIN, 30));
		
		this.logInButton = new JButton("Confirm");
		this.logIn.add(this.logInButton);
		this.logInButton.addActionListener(this);
		this.logInButton.setSize(50, 80);
		this.logInButton.setLocation(235, 60);
		this.logInButton.setFont(new Font("Verdana", Font.PLAIN, 10));
		
	}
	private void createLibrary()
	{
		this.library = new JFrame(this.lib.name);
		this.library.setLayout(null);
		this.library.setSize(650, 500);
		this.library.setResizable(false);
		this.library.setVisible(true);
		this.library.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.libraryLogOut = new JButton("Log Out");
		this.library.add(this.libraryLogOut);
		this.libraryLogOut.addActionListener(this);
		this.libraryLogOut.setBounds(25, 30, 100, 40);
		this.libraryLogOut.setFont(new Font("Verdana", Font.ITALIC, 15));
		
		this.librarySearch = new JTextField();
		this.library.add(this.librarySearch);
		this.librarySearch.setBounds(25, 80, 400, 60);
		this.librarySearch.setFont(new Font("Verdana", Font.PLAIN, 25));
		
		this.librarySearchButton = new JButton("Search");
		this.library.add(this.librarySearchButton);
		this.librarySearchButton.addActionListener(this);
		this.librarySearchButton.setBounds(450, 80, 175, 60);
		this.librarySearchButton.setFont(new Font("Verdana", Font.BOLD, 30));
		
		this.librarySearchFilter = new JButton("Filter");
		this.library.add(this.librarySearchFilter);
		this.librarySearchFilter.addActionListener(this);
		this.librarySearchFilter.setBounds(470, 150, 135, 40);
		this.librarySearchFilter.setFont(new Font("Verdana", Font.ITALIC, 15));
		
		this.libraryAccount = new JTextArea(); 
		this.library.add(this.libraryAccount);
		this.libraryAccount.setBounds(25, 200, 280, 150);
		this.libraryAccount.setEditable(false);
		this.libraryAccount.setLineWrap(true);
		this.libraryAccount.setFont(new Font("Verdana", Font.PLAIN, 15));
		this.libraryAccount.setText(this.lib.currentUser.toString());
		
		this.libraryReturn = new JButton("Return");
		this.library.add(this.libraryReturn);
		this.libraryReturn.addActionListener(this);
		this.libraryReturn.setBounds(80, 380, 170, 60); 
		this.libraryReturn.setFont(new Font("Verdana", Font.BOLD, 30));
		
		this.libraryTextArea = new JTextArea();
		this.libraryTextArea.setEditable(false);
		this.libraryTextArea.setLineWrap(true);
		this.libraryTextArea.setFont(new Font("Verdana", Font.PLAIN, 15));

		String s = "";
		
		for (int i = 0; i < this.lib.collection.size(); i++)
		{
			s += (this.lib.collection.get(i).title + "\n");
		}
		
		this.libraryTextArea.setText(s.trim());
		
		this.libraryScroll = new JScrollPane(this.libraryTextArea);
		this.library.add(this.libraryScroll);
		this.libraryScroll.setBounds(355, 200, 270, 250);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.rulesContinue)
		{
			//move on to the next frame
			this.rules.setVisible(false);
			this.createIntro();
		}
		else if (e.getSource() == this.introSignUpButton)
		{
			this.intro.setVisible(false);
			this.createSignUp();
		}
		else if (e.getSource() == this.introLogInButton)
		{
			this.intro.setVisible(false);
			this.createLogIn();
		}
		else if (e.getSource() == this.logInButton)
		{
			if (this.lib.findPerson(Integer.parseInt(this.logInEnterCard.getText())) != null)
			{
				this.lib.currentUser = this.lib.findPerson(Integer.parseInt(this.logInEnterCard.getText()));
				this.logIn.setVisible(false);
				this.createLibrary(); 
				this.library.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid Library Card Number.");
			}
		}
		else if (e.getSource() == this.signUpButton)
		{
			this.lib.currentUser = this.lib.createPerson(this.signUpTextName.getText().trim(), Short.parseShort(this.signUpTextAge.getText()));
			JOptionPane.showMessageDialog(null, "Your card number is: " + this.lib.currentUser.cardNum + ". Please don't forget it!");
			this.signUp.setVisible(false);
			this.createLibrary();
			this.library.setVisible(true);
		}
		else if (e.getSource() == this.librarySearchButton)
		{
			if (this.lib.findMedia(this.librarySearch.getText().trim()) != null)
			{
				checkOut(this.lib.findMedia(this.librarySearch.getText().trim()), this.lib.currentUser);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid title, please try again");
			}
		}
		else if (e.getSource() == this.libraryReturn)
		{
			if (this.lib.currentUser.possessions.isEmpty()) 
			{
				JOptionPane.showMessageDialog(null, "You currently have no items checked out.");
			}
			else
			{
				String items = "";
				
				for (int i = 0; i < this.lib.currentUser.possessions.size(); i++)
				{
					items += (i + 1) + ": " + this.lib.currentUser.possessions.get(i).title + "\n";
				}		
				
				String output = String.format("You currently have %d items checked out.\nTo return an item, please enter its correlating number.\n\n%s", this.lib.currentUser.possessions.size(), items);
				String answer = JOptionPane.showInputDialog(null, output);
				
				if (this.lib.currentUser.findPossession(this.lib.currentUser.possessions.get(Integer.parseInt(answer) - 1)))
				{
					this.lib.returnBook(this.lib.currentUser.possessions.get(Integer.parseInt(answer) - 1), this.lib.currentUser);
					this.libraryAccount.setText(this.lib.currentUser.toString());
					JOptionPane.showMessageDialog(null, "You have succesfully returned your item.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid number.");
				}
			}
		}
		else if (e.getSource() == this.libraryLogOut)
		{
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?");
			
			if (answer == 0) //yes
			{
				JOptionPane.showMessageDialog(null, "Logging out.");
				this.library.setVisible(false);
				this.intro.setVisible(true);
			}
		}
		else if (e.getSource() == this.librarySearchFilter)
		{
			String answer = JOptionPane.showInputDialog(null, "Would you like to filter by genre ('genre') or by type ('type')");
			
			if (answer.trim().toLowerCase().equals("genre"))
			{
				answer = JOptionPane.showInputDialog(null, "What genre would you like to filter by?");
				
				if (this.lib.printFilteredCollection(answer) != null)
				{
					JOptionPane.showMessageDialog(null, this.lib.printFilteredCollection(answer));
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Sorry, o ");
				}
			}
			else if (answer.trim().toLowerCase().equals("type"))
			{
				JOptionPane.showMessageDialog(null, Library.sortByMedia(this.lib.collection)); //prints all books
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid response.");
			}
		}
	}
}
