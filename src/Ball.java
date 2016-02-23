import org.lwjgl.opengl.GL11;

public class Ball {
	
	float x;
	float y;
	float x_vector=20f;
	float y_vector=3f;
	int ball_id;
	float friction = 0.1f;
	
	public Ball(int newX, int newY, int new_ball_id){
		x = newX;
		y = newY;
		ball_id = new_ball_id;
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
		drawEllipse((int)x,(int)y,12,12,0,0,0,0);
	}
	public void update(int delta){
		x+=x_vector;
		y+=y_vector;
		if(x_vector<0.1f && x_vector>-0.1f)x_vector=0;
		if(y_vector<0.1f && y_vector>-0.1f)y_vector=0;
		if(x_vector>0.0f)x_vector-=friction;
		if(x_vector<0.0f)x_vector+=friction;
		if(y_vector>0.0)y_vector-=friction;
		if(y_vector<0.0f)y_vector+=friction;
		
		if(x>850){
			x=849;
			x_vector=-x_vector;
			x_vector=x_vector/1.2f;
		}
		if(x<50){
			x=51;
			x_vector=-x_vector;
		}
		
	}

}
