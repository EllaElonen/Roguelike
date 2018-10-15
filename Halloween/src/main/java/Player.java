public class Player {

    int amountOfLivesLeft;
    double x;
    double y;
    int speed;

    public Player(int amountOfLives, int XPosition, int YPosition, int speed){
    	
        amountOfLivesLeft = this.amountOfLivesLeft;
        x = this.x;
        speed = this.speed;
    }

    public void SetLives() {
        amountOfLivesLeft = this.amountOfLivesLeft;
    }

    public void setTestSpeed() {
        this.speed = speed;
    }

    public void JumpToPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(){

    }

    public void equals(){
    }
}
