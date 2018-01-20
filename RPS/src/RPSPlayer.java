import java.util.Random;

abstract class RPSPlayerBase {
  
  public static void main(String[] args) {
    RPSPlayerBase player = new RPSPlayer();
    player.go();
  }
  
  abstract public RPSType go();
}

public class RPSPlayer extends RPSPlayerBase {
  
  public RPSType go() {
    Random ran = new Random();
    int input = ran.nextInt(2) + 1;     // 乱数を0～2で指定し、1加えることで1～3の乱数にする
    RPSType rps = null;
    switch(input){
      case 1:
        rps = RPSType.ROCK;
        break;
      case 2:
        rps = RPSType.PAPER;
        break;
      case 3:
        rps = RPSType.SCISSORS;
        break;
      default:
        break;
    }
    
    return rps;
  }

}
