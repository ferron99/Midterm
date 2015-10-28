//// Nick Ferro
// CST112 Midterm
// 10-28-2015


float nickX,  nickY,  nickDX,  nickDY;   
float windX,  windY,  windDX,  windDY;        
float fernX,  fernY,  fernDX,  fernDY;                
String title=  "CST112 MIDTERM EXAM";
String author=  "Nick Ferro";
float left=50, right=590, top=150, bottom=400;        // Table boundaries
float middle=(left+right)/2;
boolean wall=true;
boolean mouse=false;            //toggle for mouse
float miceX, miceY, miceDX;
int frame;                      //frame counter
int tableRed=150, tableGreen=250, tableBlue=150;      // green pool table
int score=0,m=0,k=0;

   
void setup() {
    size( 640, 480 );         
    reset();
 }

void draw() {
  background( 250,250,200 );
  rectMode( CORNERS );
  table( left, top, width-50, bottom );
  buttons();
  bounce();
  collisions();
  show();
  mouseDraw();
  messages();
}

void reset(){
   nickX=  random( middle,right );   nickY=  random( top, bottom );
   windX=  random( middle,right );   windY=  random( top, bottom );
   fernX=  random( middle,right );   fernY=  random( top, bottom );
   miceX= width-50;                   miceY=  420;
   // Random speeds
  
   nickDX=  random( -5,5 );   nickDY=  random( -5,5 );
   windDX=  random( -5,5 );   windDY=  random( -5,5 );
   fernDX=  random( -5,5 );   fernDY=  random( -5,5 );
   miceDX= -1;
}


void keyPressed() {
  if (key == 'q') { exit();  }
  if (key == 'r') { reset(); }
  if (key == 'w') {wall = false;}
  if (key == 'p') {tablePink();}
  if (key == 'm') {mouse = true;}
   
}



//// SCENE:  draw the table with wall down the middle
void table( float east, float north, float west, float south ) {
  fill( tableRed, tableGreen, tableBlue );    // pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( east-20, north-20, west+20, south+20 );

  if (wall) {
    float middle=  (east+west)/2;    
    stroke( 0, 127, 0 );
    line( middle,north+10, middle,south-10 );
  }
  stroke(0);
  strokeWeight(1);
}


void bounce() {
  if (wall){
  nickX += nickDX;  if ( nickX<middle || nickX>right ) nickDX *= -1;
  nickY += nickDY;  if ( nickY<top || nickY>bottom ) nickDY *= -1;
  windX += windDX;  if ( windX<middle || windX>right ) windDX *= -1;
  windY += windDY;  if ( windY<top || windY>bottom ) windDY *= -1;
  fernX += fernDX;  if ( fernX<middle || fernX>right ) fernDX *= -1;
  fernY += fernDY;  if ( fernY<top || fernY>bottom ) fernDY *= -1;
  }else{
  nickX += nickDX;  if ( nickX<left || nickX>right ) nickDX *= -1;
  nickY += nickDY;  if ( nickY<top || nickY>bottom ) nickDY *= -1;
  windX += windDX;  if ( windX<left || windX>right ) windDX *= -1;
  windY += windDY;  if ( windY<top || windY>bottom ) windDY *= -1;
  fernX += fernDX;  if ( fernX<left || fernX>right ) fernDX *= -1;
  fernY += fernDY;  if ( fernY<top || fernY>bottom ) fernDY *= -1;
  }
}
void collisions() {
   float tmp;
  if ( dist( nickX,nickY, windX,windY ) < 30 ) {         //wind and nick
    tmp=windDX;  windDX=nickDX;  nickDX=tmp;
    tmp=windDY;  windDY=nickDY;  nickDY=tmp;
    k +=1;
  }
  if ( dist( fernX,fernY, windX,windY ) < 30 ) {         //wind and fern
    tmp=windDX;  windDX=fernDX;  fernDX=tmp;
    tmp=windDY;  windDY=fernDY;  fernDY=tmp;
    k +=1;
  }
  if ( dist( nickX,nickY, fernX,fernY ) < 30 ) {         //fern and nick
    tmp=fernDX;  fernDX=nickDX;  nickDX=tmp;
    tmp=fernDY;  fernDY=nickDY;  nickDY=tmp;
    k +=1;
  }
}


void show() {        ///display code for balls
  stroke(0);
  strokeWeight(1);
  fill( 255,0,0 );    ellipse( nickX,nickY, 30,30 );
  fill( 255,255,0 );  ellipse( windX,windY, 30,30 );
  fill( 0,0,255 );    ellipse( fernX,fernY, 30,30 );
  fill(0);
  text("1", nickX,nickY);
  text("2", windX,windY);
  text("3", fernX,fernY);
}

void mouseDraw(){           //code for mouse
  if (mouse){                //only display mouse if true
    fill(128,128,128);
    ellipse(miceX, miceY,20,20);
    miceX += miceDX;
    frame = frame + 1;
     if ( miceX<left || miceX>right ) miceDX *= -1;        //animation code for mouse legs
     if (miceDX == -1){
       if(frame/30 % 2 == 0){
         line(miceX+10,miceY+4,miceX+20,miceY+4);
       }else{
         line(miceX+10,miceY+4,miceX+20,miceY-1);
       }
     }else if (miceDX == 1){
       if(frame/30 % 2 == 0){
         line(miceX-10,miceY+4,miceX-20,miceY+4);
       }else{
         line(miceX-10,miceY+4,miceX-20,miceY-1);
       }
   }
 }
 }

void tablePink(){
      tableRed = 255;
      tableGreen = 192;
      tableBlue = 203;  
}

void buttons() {
  rectMode(CORNER);                //display buttons
  fill(0);
  strokeWeight(4);
  stroke(255);
  rect(50,50,100,50);
  rect(170,50,100,50);
  rect(290,50,100,50);
  rect(410,50,100,50);
  fill(255);
  text("Reset Balls 'R' ", 70, 70);
  text("Remove Wall ", 190, 70);
  text(" 'W' ", 190, 90);
  text("Turn Table ", 310, 70);
  text("Pink 'P' ", 310, 90);
  text("Spawn Mouse ", 430, 70);
  text("'M' ", 430, 90);
  //text(width-50, 100,100);
  
}
void messages() {
  fill(0);
  text( title, width/3, 15 );
  text("Collisions", 50, 450);
  text(k, 120, 450);
  text( author, 10, height-5 );
}

void mousePressed(){
  if (mouseX >50 && mouseX<150 && mouseY>50 && mouseY<100){
    reset();
  }
  if (mouseX >170 && mouseX<270 && mouseY>50 && mouseY<100){
      wall = false;
  }
  if (mouseX >290 && mouseX<390 && mouseY>50 && mouseY<100){  
      tablePink(); 
  }
  if (mouseX >410 && mouseX<510 && mouseY>50 && mouseY<100){ 
    mouse = true;
  }
}
