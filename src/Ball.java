import org.lwjgl.opengl.GL11;

public class Ball {
	
	
	
	float x;
	float y;
	float x_vector=0f;
	float y_vector=0f;
	int ball_id;
	float friction = 1.02f;
	int radius;
	
	boolean is_x_moving;
	boolean is_y_moving;
	boolean is_moving;
	
	public Ball(int newX, int newY, int new_ball_id, int newRadius){
		x = newX;
		y = newY;
		ball_id = new_ball_id;
		radius = newRadius;
	}
	
	public void addImpulse(float newX, float newY){
		x_vector+=newX;
		y_vector+=newY;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public boolean getIsMoving(){
		return is_moving;
	}
	
	public void drawEllipse(int newX, int newY, float xradius, float yradius, float r, float g, float b, float a )
	{
		GL11.glColor4f(r,g,b,a);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glScalef(xradius, yradius, 1);

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(0, 0);
		for(int i = 0; i <= 50; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
		    double angle = Math.PI * 2 * i / 50;
		    GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	
	
	public void draw(){
		if(ball_id==0){
			drawEllipse((int)x,(int)y,radius,radius,1,1,1,0);
		}
		if(ball_id==1){
			drawEllipse((int)x,(int)y,radius,radius,0,0,0,0);
		}
	}
	public void update(int delta){
		x+=x_vector;
		y+=y_vector;
		
		
		is_moving=true;
		if(x_vector<0.1f && x_vector>-0.1f){
			x_vector=0;
			is_x_moving=false;
		}else{
			is_x_moving=true;
		}
		if(y_vector<0.1f && y_vector>-0.1f){
			y_vector=0;
			is_y_moving=false;
		}else{
			is_y_moving=true;
		}
		if(!is_x_moving && !is_y_moving)is_moving=false;
		
		if(x_vector>0.0f)x_vector/=friction;
		if(x_vector<0.0f)x_vector/=friction;
		if(y_vector>0.0)y_vector/=friction;
		if(y_vector<0.0f)y_vector/=friction;
		
		if(x+radius>850){
			x=849-radius;
			x_vector=-x_vector;
			//x_vector=x_vector/1.5f;
		}
		if(x-radius<50){
			x=51+radius;
			x_vector=-x_vector;
		}
		
		
		if(y+radius>450){
			y=449-radius;
			y_vector=-y_vector;
			//x_vector=x_vector/1.5f;
		}
		if(y-radius<50){
			y=51+radius;
			y_vector=-y_vector;
			
		}
		
		
		
	}

}
