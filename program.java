
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import java.text.AttributedCharacterIterator;
import java.text.SimpleDateFormat;
import com.fazecast.jSerialComm.*;
import javax.swing.*;

import com.fazecast.jSerialComm.SerialPort;


public class display extends JFrame {



	private Container frame=getContentPane();


	private JTextField userTemp =new JTextField("");
	private JTextField userHum =new JTextField("");
	private JTextField userBright =new JTextField("");

	//FINAL LABELS
	private JLabel air=new JLabel("Air: ");
	private JLabel temp=new JLabel("Set Temperature:");
	private JLabel humid=new JLabel("Set Humidity:");
	private JLabel light=new JLabel("Set Brightness:");
	private JLabel tempL=new JLabel("Temperature:");
	private JLabel outT=new JLabel("Temperature:");
	private JLabel outH=new JLabel("Humidity:");
	private JLabel outside=new JLabel("Outside:");
	private JLabel inside=new JLabel("Inside:");
	private JLabel door1=new JLabel("Door 1:");
	private JLabel door2=new JLabel("Door 2:");
	private JLabel openWindow=new JLabel("");
	private JLabel bright=new JLabel("Lights");
	private JLabel cLabel=new JLabel("Color Sensor");

	//CHANGERS
	private JLabel humidL=new JLabel("Humidity:");
	private JLabel indoorT=new JLabel("0\u00b0F");
	private JLabel indoorH = new JLabel("0%");
	private JLabel onOff =new JLabel("ON");
	private JLabel time=new JLabel();
	private JLabel open1=new JLabel("CLOSED");
	private JLabel open2=new JLabel("CLOSED");
	private JLabel lightP=new JLabel("0%");
	private JLabel rgb=new JLabel("");
	private JLabel alpha=new JLabel("");

	private JLabel oTemp=new JLabel("0\u00b0F");
	private JLabel oHum =new JLabel("0%");
	private JLabel ts1= new JLabel();
	private JLabel ts2= new JLabel();

	private JLabel nums=new JLabel();



	//BUTTONS
	static JButton refresh =new JButton("r");
	private JButton color=new JButton();

	private JComboBox coms;
	private JComboBox coms2;


	//SCANNERS
	private Scanner in1;
	private Scanner in2;
	private Scanner in3;

	//SERIAL PORTS
	static SerialPort p1;
	static SerialPort p2;
	static SerialPort p3;

	//PRINT WRITERS
	private PrintWriter out1;
	private PrintWriter out2;
	private PrintWriter out3;


	//PRIMATIVES

	private String door;
	private double desired=70;
	static boolean opened=false;
	private double inT=0;
	private double inH=0;
	private double inD=0;
	private double T=0;
	private double H=0;
	private double Door=0;
	private double idealHum=-1;
	private int r=0;
	private int g=0;
	private int b=0;
	private int a=0;





	private boolean airOn;
	private boolean start=false;



    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawLine(430, 75, 430, 400);

    }

    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }

		display(){

	//////////////////////LABEL FOR JTEXTBOX
		frame.add(temp);
		temp.setSize(200,25);
		temp.setLocation(50,50);
		temp.setVisible(true);
		temp.setForeground(Color.GRAY);

		frame.add(humid);
		humid.setSize(200,25);
		humid.setLocation(250,50);
		humid.setVisible(true);
		humid.setForeground(Color.GRAY);

		frame.setLayout(null);
		frame.add(inside);
		inside.setVisible(true);
		inside.setSize(100,25);
		inside.setLocation(50,120);
		inside.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,20));


		frame.add(outside);
		outside.setVisible(true);
		outside.setLocation(50,250);
		outside.setSize(100,25);
		outside.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,20));

		frame.add(tempL);
		tempL.setLocation(175,150);
		tempL.setSize(200,30);
		tempL.setVisible(true);
		tempL.setForeground(Color.GRAY);

		frame.add(indoorT);
		indoorT.setLocation(175,175);
		indoorT.setSize(100,30);
		indoorT.setVisible(true);
		indoorT.setFont(new Font("Arial", Font.PLAIN,25));

		frame.add(humidL);
		humidL.setLocation(325,150);
		humidL.setSize(200,30);
		humidL.setVisible(true);
		humidL.setForeground(Color.GRAY);

		frame.add(air);
		air.setVisible(true);
		air.setLocation(50,150);
		air.setSize(200,30);
		air.setForeground(Color.GRAY);

		frame.add(onOff);
		onOff.setVisible(true);
		onOff.setLocation(50,175);
		onOff.setSize(200,30);
		onOff.setFont(new Font("Arial", Font.PLAIN,25));


		////////////////////////////////////////////??RIGHT SIDE OF FRAME//////////////////////////////////////////////\
		frame.add(bright);
		bright.setVisible(true);
		bright.setSize(200,30);
		bright.setLocation(480,100);
		bright.setForeground(Color.GRAY);

		frame.add(lightP);
		lightP.setVisible(true);
		lightP.setSize(200,30);
		lightP.setLocation(480,125);
		lightP.setFont(new Font("Arial", Font.PLAIN,25));

		frame.add(door1);
		door1.setVisible(true);
		door1.setSize(200,30);
		door1.setLocation(480,200);
		door1.setForeground(Color.GRAY);

		frame.add(door2);
		door2.setVisible(true);
		door2.setSize(200,30);
		door2.setLocation(480,300);
		door2.setForeground(Color.GRAY);

		frame.add(open1);
		open1.setVisible(true);
		open1.setSize(200,30);
		open1.setLocation(480,225);
		open1.setFont(new Font("Arial", Font.PLAIN,25));

		frame.add(open2);
		open2.setVisible(true);
		open2.setSize(200,30);
		open2.setLocation(480,325);
		open2.setFont(new Font("Arial", Font.PLAIN,25));

		frame.add(ts1);
		ts1.setVisible(true);
		ts1.setLocation(480,250);
		ts1.setSize(200,30);

		frame.add(ts2);
		ts2.setVisible(true);
		ts2.setLocation(480,350);
		ts2.setSize(200,30);





		SerialPort ports[]=SerialPort.getCommPorts();
		String[] portNames=new String[ports.length];
		for(int i=0;i<ports.length;i++)
			portNames[i]=ports[i].getSystemPortName();
		coms=new JComboBox(portNames);
		coms2=new JComboBox(portNames);

		frame.add(coms);
		coms.setVisible(false);
		//coms.setLocation(200,400);
		//coms.setSize(200,150);
		coms.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p1=ports[8];//inside



				if(p1.openPort()){
					System.out.println("port 1 works");
				}

				p2=ports[6];//outside

				if(p2.openPort()){
					System.out.println("port 2 works");
				}

				p3=ports[10];//light

				if(p3.openPort()){
					System.out.println("port 3 works");
				}


				p1.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
				p2.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
				p3.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
				in1=new Scanner(p1.getInputStream());
				in2=new Scanner(p2.getInputStream());
				in3=new Scanner(p3.getInputStream());
				out1=new PrintWriter(p1.getOutputStream());
				out2=new PrintWriter(p2.getOutputStream());
				out3=new PrintWriter(p3.getOutputStream());

				System.out.println("done");
				refresh.doClick();
			}
		});


		frame.add(indoorH);
		indoorH.setLocation(325,175);
		indoorH.setSize(100,30);
		indoorH.setVisible(true);
		indoorH.setFont(new Font("Arial", Font.PLAIN,25));

		frame.add(userTemp);
		userTemp.setLocation(160,50);
		userTemp.setSize(50,25);
		userTemp.setVisible(true);
		userTemp.setText("70");

		frame.add(openWindow);
		openWindow.setVisible(true);
		openWindow.setLocation(50,375);
		openWindow.setSize(200,50);
		openWindow.setFont(new Font("Arial", Font.PLAIN, 20));

		frame.add(light);
		light.setVisible(true);
		light.setLocation(480,50);
		light.setSize(500,25);
		light.setForeground(Color.GRAY);
		frame.add(userBright);
		userBright.setLocation(575,50);
		userBright.setSize(50,25);
		userBright.setVisible(true);
		userBright.setText("1");



		frame.add(userHum);
		userHum.setLocation(340,50);
		userHum.setSize(50,25);
		userHum.setVisible(true);
		userHum.addActionListener(new ActionListener() {///////////////////////////////////////////////////////////////////////////////////////

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					idealHum=Double.parseDouble(userHum.getText());

				}catch (Exception r) {
					userHum.setText("");
				}
			}
		});


		frame.add(outT);
		outT.setVisible(true);
		outT.setSize(200,30);
		outT.setLocation(50,280);
		outT.setForeground(Color.GRAY);


		////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>COLOR SENSOR<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		frame.add(cLabel);				//Label
		cLabel.setVisible(true);
		cLabel.setLocation(685,120);
		cLabel.setSize(100,30);

		frame.add(rgb);				//rgb value label
		rgb.setVisible(true);
		rgb.setLocation(650,310);
		rgb.setSize(150,30);


		frame.add(alpha);
		alpha.setVisible(true);
		alpha.setLocation(700,330);
		alpha.setSize(70,30);

		frame.add(color);				//Box
		color.setVisible(true);
		color.setSize(150,150);
		color.setLocation(650,150);
		color.setOpaque(true);
		color.setText("Start");
		color.setBorderPainted(false);
		color.setBackground(Color.white);
		color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!start)
				{
					start=true;
					color.setText(" ");

				}
				else{
					start=false;
					color.setText("start");
					color.setBackground(Color.white);
					rgb.setText("");
					alpha.setText("");
				}



			}
		});


		frame.add(outH);
		outH.setVisible(true);
		outH.setSize(200,30);
		outH.setLocation(175,280);
		outH.setForeground(Color.GRAY);

		frame.add(oHum);
		oHum.setLocation(175,305);
		oHum.setSize(150,30);
		oHum.setVisible(true);
		oHum.setFont(new Font("Arial", Font.PLAIN,25));

		frame.add(oTemp);
		oTemp.setLocation(50,305);
		oTemp.setSize(150,30);
		oTemp.setVisible(true);
		oTemp.setFont(new Font("Arial", Font.PLAIN,25));










		userTemp.addActionListener(new ActionListener() {////////////////////////////////////////////////////////////////////////////

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				desired=Double.parseDouble(userTemp.getText());}catch (Exception u) {
					userTemp.setText("");
				}
			}

			});//top

		//////////////////////////////////////TIMESTAMP

		frame.add(time);
		time.setVisible(true);
		time.setLocation(600,390);
		time.setSize(250,20);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String ts = sdf.format(date);
		time.setText("Last updated "+ts);



		//////////////////////////////////////UPDATE

		frame.add(refresh);
		refresh.setLocation(500,500);
		refresh.setSize(10,10);
		refresh.setVisible(false);
		refresh.addActionListener(new ActionListener() {///////////////////////////////////////////////////////////////////////////////

			@Override
			public void actionPerformed(ActionEvent e) {

				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
				String ts = sdf.format(date);
				time.setText("Last updated "+ts);


			//opened=false;
				if(!opened){
					if(p1.openPort()){
						opened=true;
						System.out.println("yes");
						p1.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
						p2.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
						p3.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
						in1=new Scanner(p1.getInputStream());
						in2=new Scanner(p2.getInputStream());
						in3=new Scanner(p3.getInputStream());
						out1=new PrintWriter(p1.getOutputStream());
						out2=new PrintWriter(p2.getOutputStream());
						out3=new PrintWriter(p3.getOutputStream());

						out3.print("1");
						out3.flush();
						try
						{
						    Thread.sleep(200);
						}
						catch(InterruptedException ex)
						{
						    Thread.currentThread().interrupt();
						}

					}
					else{
						light.setText("NOPE");
						System.out.println("no");
					}
				 }

				int A,B, C,D,E,F,G;
				p1.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
				in1=new Scanner(p1.getInputStream());
				out1=new PrintWriter(p1.getOutputStream());

			out1.print("a");
				out1.flush();

				while(!in1.hasNext()){
					System.out.println("waiting");
				}
				String s=in1.nextLine();
				//Get color sense and reed
				C=s.indexOf("d");
				D=s.indexOf("r");
				E=s.indexOf("g");
				F=s.indexOf("b");
				G=s.indexOf("a");

				try{

					inD=Double.parseDouble(s.substring(C+1,D));
					r=Integer.parseInt(s.substring(D+1,E));
					g=Integer.parseInt(s.substring(E+1,F));
					b=Integer.parseInt(s.substring(F+1,G));
					a=Integer.parseInt(s.substring(G+1));
				}catch (Exception w) {}




				r=(1020-r)*255/153;
				g=(g-80)*255/283;
				b=(b-47)*255/245;
				a=(1300-a)*255/750;

				System.out.println(b);
				if(r>255)
					r=255;
				if(g>255)
					g=255;
				if(b>255)
					b=255;
				if(r<0)
					r=0;
				if(g<0)
					g=0;
				if(b<0)
					b=0;
				if(a>255)
					a=255;
				if(a<0)
					a=0;

				if(start){
				try{
					color.setBackground(new Color(r,g,b,a));
					rgb.setText("R: "+r+"  G: "+g+"  B: "+b);
					alpha.setText("A: "+a);

				}catch (Exception u) {
					alpha.setText("ahh");
				}

				}




				if(inD==0)
				{
					if(open1.getText().equals("CLOSED"))
						ts1.setText(sdf.format(date));
					open1.setText("OPEN");
					//if(Integer.parseInt(sdf.format(date).substring(13,15))>Integer.parseInt(ts1.getText().substring(13,15))+1);
						Toolkit.getDefaultToolkit().beep();

				}
				else{
					open1.setText("CLOSED");
					ts1.setText("");

				}


				p2.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
				in2=new Scanner(p2.getInputStream());
				out2=new PrintWriter(p2.getOutputStream());

				out2.print("a");
				out2.flush();

				s=in2.nextLine();
				A=s.indexOf("t");
				B=s.indexOf("h");
				C=s.indexOf("d");
				try{
					desired=Double.parseDouble(userTemp.getText());
					T=Double.parseDouble(s.substring(A+1,B));
					H=Double.parseDouble(s.substring(B+1,C));
					Door=Double.parseDouble(s.substring(C+1));
				}catch (Exception w) {}



				try{
					int w=Integer.parseInt(userBright.getText());
					w%=10;
				out3.print(w);
				out3.flush();}
				catch (Exception r) {
					// TODO: handle exception
				}

				try{
				s=in3.nextLine();
				}catch (Exception q) {
					// TODO: handle exception
				}
				A=s.indexOf("t");
				B=s.indexOf("h");
				C=s.indexOf("l");
				double per=0;
				desired=Double.parseDouble(userTemp.getText());

				try{

				inT=Double.parseDouble(s.substring(A+1,B));
				inH=Double.parseDouble(s.substring(B+1,C));
				per=Double.parseDouble(s.substring(C+1));
				}
				catch (Exception w) {
				}


				indoorT.setText(inT+"\u00b0F");//degrees F)
				indoorH.setText(inH+"%");









				//OUTDOOR TEMPERATURE AND DOOR SETTINGS

				oTemp.setText(T+"\u00b0F");//degrees F)
				oHum.setText(H+"%");
				if(Door==0)
				{
					if(open2.getText().equals("CLOSED"))
						ts2.setText(sdf.format(date));
					open2.setText("OPEN");
					//if(Integer.parseInt(sdf.format(date).substring(13,15))>Integer.parseInt(ts2.getText().substring(13,15))+1);
						Toolkit.getDefaultToolkit().beep();
				}
				else{
					open2.setText("CLOSED");
					ts2.setText("");
				}

				lightP.setText(per/160*100+"%");


				//CHECK TEMPERATURE
				if(T<=desired ||inT< desired)
				{
					airOn=false;
					onOff.setText("OFF");
				}
				else if(T>desired || inT>desired){
					airOn=true;
					onOff.setText("ON");
				}


				//CHECK HUMIDITY
				if(!userHum.getText().equals(""))
				{
					if(H>=Integer.parseInt(userHum.getText())){
						airOn=true;
						onOff.setText("ON");
					}
				}



				//ALERT USER OF WINDOW STATUS
				if(T<=desired)
				{
					if(openWindow.getText().equals("Close Windows"))
						for(int i=0;i<20;i++)
							Toolkit.getDefaultToolkit().beep();

					openWindow.setText("Open Windows");
				}
				else
				{
					if(openWindow.getText().equals("Open Windows"))
						for(int i=0;i<20;i++)
							Toolkit.getDefaultToolkit().beep();

					openWindow.setText("Close Windows");
				}



			}
		});

	}


	//TO CALL REFRESH FROM MAIN
	static void update(){//////////////////////////////////////////////////////////////////////////////////////////////////////

		if(opened)
			refresh.doClick();
	}

	static void setup(){
		SerialPort ports[]=SerialPort.getCommPorts();

		SerialPort p1= ports[12];


		if(p1.openPort())
			opened=true;
		else System.out.println("p1 connection failed");
	}


	public static void main(String[] args) throws FileNotFoundException  {


		display d=new display();

		d.setVisible(true);
		d.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		d.setLocation(100,100);
		d.setSize(850,440);
		d.setTitle("Home Sensor");
		d.setLayout(null);


		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String ts = sdf.format(date);


		while (true){
			///System.out.println("h");
			update();
			try
			{
			    Thread.sleep(100);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
		}



	}



}
