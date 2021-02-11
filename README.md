# Automated Sensor Technology for the Operation of Smart Homes

## Table of Contents
- [Abstract](#abstract)
- [Internet of Things (IoT)](#iot)
- [Arduino-Java Connection](#arduino-java-connection)
- [Creating the Graphical User Interface](#creating-the-graphical-user-interface)
- [Results](#results)
- [Publisher](#publisher)
- [Disclaimer](#disclaimer)

## Abstract
The concept of smart home technology has become more common in modern homes and has allowed for detailed monitoring of many household aspects. Data collected around the home, such as energy usage and waste produced, can be used to make daily life more convenient and create a more energy efficient household. 

The purpose of this project was to create a series of sensors to monitor a house that functions as an Internet of Things (IoT). This was accomplished through the development of  a temperature/humidity sensor, door motion sensor, light sensor, and color sensor. These sensors were chosen to monitor the characteristics of a hypothetical house, so a program could give suggestions for conserving energy. The house monitoring system was built out of Arduino microcontrollers and a Java graphical user interface (GUI). The values taken from these sensors are compiled in a central computing source which adjusts air conditioning or sends alerts to the user based on the data collected around the home. The sensors are also linked through Bluetooth to create a fully functional household Internet of Things. This sensor system aims to eliminate wasted energy by creating an automated system to efficiently regulate the climate of the home.

## Internet of Things (IoT)
Using  IoT,  this  project  seeks  to  create  an  energy  and  cost efficient  home.  Most  climate  regulating  smart  home  systems take into account only indoor conditions, whether it be indoor temperature/humidity or whether or not there are people in the home.  In  addition,  most  IoT  systems  only  aid  in  one  aspect, such  as  lighting  or  AC  alone.  However,  this  project’s  “smarthome”  incorporates  a  variety  of  sensors  and  aims  to  take advantage of outdoor conditions when regulating temperature within the home, as well as regulating lighting and monitoring doors to create a functional IoT energy-efficient home.

## Arduino-Java Connection
In  order  to  link  Arduino  and  Java,  the  external  library, “jSerialComm,”  is  used.  “jSerialComm”  links  Arduino  and Java to enable two way communication. The library allows data  from  the  COM  port  of  the  Arduino  to  be  imported directly  to  Java  through  an  input  stream  read  by  a  scanner. Additionally, this library allows Java to send information to the COM port of the Arduino using a printWriter class, allowing for two way communication across the interfaces.

## Creating the Graphical User Interface
To  create  a  GUI  in  Java,  the  program  extended  the  class JFrame.   Using   methods and commands available in JFrame, the program displays  the  status  of  the  indoor  andoutdoor  temperature  sensors  as  well  as  lighting  and  doorsensors.  The  interface  also  allows  the  user  to  input  desired temperature,  humidity,  and  brightness  levels.  The  GUI,  asshown in Figure 2, displays indoor and outdoor temperatures and  humidities,  lighting  percentage,  AC  status  (on/off),  and door statuses (open/closed) using the data from sensors around the house.

In order to create a direct exchange of information between the  various  Arduino  boards  and  Java,  the  program  utilizes the  jSerialComm  library.  From  the  jSerialComm  library  the method getOutputStream() is used to send data to the Arduino and getInputStream() is used to receive data. In order to keepthe information displayed in the interface contemporary, each piece  of  data  is  updated  to  the  most  recent  reading  available every half second.

After  collecting  incoming  data  from  each  Arduino  board, the program then makes a series of decisions. With respect to climate, the program determines whether the AC should be on or off according to the chart in Figure 3. This data is also used to recommend an efficient window status (open or closed).

In  addition  to  regulating  the  climate,  the  sensor  system displays  the  status  of  two  doors  of  the  users  choice,  and lighting. The GUI shows whether each door is open or closed, showing  a  time  stamp  if  the  door  has  been  left  open.  The program also allows the user to set a desired brightness level ranging  from  1  to  10,  sending  the  selected  value  out  to  the Arduino  board  and  displaying  what  percentage  of  energy  is being used on the interface.

## Results
In  its  current  state,  the  Arduino  works  to  save  energy  by monitoring lighting and air conditioning. Since the Arduino is also  compact  and  has  few  outputs,  it  does  not  require  much energy  to  operate.  In  addition,  the  “smart  house”  has  other helpful features: the Arduino can immediately alert users about open  doors  and  return  the  exact  color  of  objects.  Moreover, the “smart house” is an inexpensive option to regulate housing conditions. The versatility of the Arduino units makes them easy  to  use  as  a  part  of  a  larger  communications  network, forming an IoT by functioning as a liaison between the sensors and the central computer.

## Publisher
This project has been published in the IEEE Digital Xplore Library. For more details about the project, please visit [https://ieeexplore.ieee.org/document/9244802]. 

## Disclaimer
Automated Sensor Technology for the Operation of Smart Homes is not affiliated, associated, authorized, endorsed by, or in any way officially connected with Github, or any of its subsidiaries or its affiliates.  
