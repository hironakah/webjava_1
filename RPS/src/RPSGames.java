import java.util.Scanner;

public class RPSGames {
  public static void main(String[] args) {
    System.out.println("🔶🔶じゃんけんゲームスタート🔶🔶" + "\r\n");

    boolean flag = false;       // メニュー入力エラー時のフラグ

    do {                // 正しく入力されるまでループ
      System.out.println("＜じゃんけんゲームメニュー＞"+ "\r\n\r\n" + 
          "マニュアル対戦 ：m" + "\r\n" + "オート対戦     ：a" + "\r\n" +
          "ゲーム終了     ：e" + "\r\n\r\n" + "コマンドを入力してください＞＞");
      Scanner scan = new Scanner(System.in);
      String input = scan.next();       // じゃんけんの手を入力させる

      switch (input) {
        case "m":
          System.out.println("\r\n" + "＜マニュアル対戦スタート＞" + "\r\n");
          manual();
          break;
        case "a":
          System.out.println("\r\n" + "＜オート対戦スタート＞" + "\r\n");
          auto();
          break;
        case "e":
          System.out.println("\r\n" + "ゲームを終了しました。" + "\r\n");
          return;
        default:
          System.out.println("\r\n" + " m か a か e を入力してください。" + "\r\n");
          flag = true;          // 入力が正しくないとき、フラグがtrueとなり繰り返す
          break;
      }
    } while (flag);
  }

  private static void manual() {
    int cntPlayerWin = 0;   // ユーザーの勝利回数
    int cntCpWin = 0;       // 対戦相手の勝利回数
    int cntResult = 0;      // 試合数

    boolean mflag = false;          // ユーザーの手の入力エラー時のフラグ初期化
    boolean oneMore = false;       // あいこのときの掛け声のフラグ初期化
    RPSType usrRps = null;          // ユーザーの手の初期化

    do {                        // あいこの場合のループ
      if (oneMore) {            // 前回のゲームがあいこだった場合
        System.out.println("あいこで、、、、" + "\r\n");
        oneMore = false;            // あいこ掛け声後、フラグ初期化
      } else {
        System.out.println("最初はグー！じゃんけん、、、、" + "\r\n");
      }

      do {                  // ユーザーの手の入力エラー時ループ
        System.out.println("グー　　:１" + "\r\n" + "チョキ　:２" 
            + "\r\n" + "パー　　:３" + "\r\n" + "終了する:e" 
              + "\r\n\r\n" + "コマンドを入力してください＞＞");

        Scanner scan = new Scanner(System.in);
        String yourInput = scan.next();         // ユーザーの手の入力

        System.out.println("ぽん！！！");
        switch (yourInput) {
          case "1":
            System.out.println("あなた：グー");
            usrRps = RPSType.ROCK;
            break;
          case "2":
            System.out.println("あなた：チョキ");
            usrRps = RPSType.SCISSORS;
            break;
          case "3":
            System.out.println("あなた：パー");
            usrRps = RPSType.PAPER;
            break;
          case "e":
            return;
          default:
            System.out.println("1か2か3を入力してください。" + "\r\n");
            mflag = true;          // 入力が正しくないとき、フラグがfalseとなり繰り返す
            break;
        }
      } while (mflag);

      // 試合数カウント
      cntResult++;

      // 対戦相手の手
      RPSPlayer rps = new RPSPlayer();
      RPSType cpRps = rps.go();

      switch (cpRps) {
        case ROCK:
          System.out.println("相手：グー" + "\r\n");
          break;
        case SCISSORS:
          System.out.println("相手：チョキ" + "\r\n");
          break;
        case PAPER:
          System.out.println("相手：パー" + "\r\n");
          break;
        default:
          // 処理なし
          break;
      }

      // 勝ち負け判定
      if (usrRps.getId() != cpRps.getId()) {
        switch (usrRps) {
          case ROCK:
            if (cpRps == RPSType.SCISSORS) {
              System.out.println("あなたの勝ち！");
              cntPlayerWin++;       // ユーザー勝利の場合
            }else {
              System.out.println("あなたの負け");
              cntCpWin++;           //対戦相手勝利の場合
            }
            break;
          case SCISSORS:
            if (cpRps == RPSType.PAPER) {
              System.out.println("あなたの勝ち！");
              cntPlayerWin++;
            }else {
              System.out.println("あなたの負け");
              cntCpWin++;
            }
            break;
          case PAPER:
            if (cpRps == RPSType.ROCK) {
              System.out.println("あなたの勝ち！");
              cntPlayerWin++;
            }else {
              System.out.println("あなたの負け");
              cntCpWin++;
            }
            break;
          default:
            // 処理なし：想定外の値
            break;
        }
        // 結果表示
        System.out.println("ユーザーの勝利数：" + cntPlayerWin + "\r\n" + "対戦相手の勝利数："
            + cntCpWin + "\r\n" + "試合数：" + cntResult + "\r\n");
      
      } else {
        oneMore = true;
        // あいこの試合数はノーカウント
        cntResult--;
      }
      
    } while (true);
  }

  private static void auto () {
    int cp1Win = 0;
    int cp2Win = 0;
    int cntResult = 0;
    for(int i = 1; i < 51; i++) {
      System.out.println(i+"回目" + "\r\n" + "じゃんけん、、、" + "\r\n\r\n" + "ぽん！！！" + "\r\n");
      
      // コンピュータ１の手
      RPSPlayer cp1 = new RPSPlayer();
      RPSType cpRps1 = cp1.go();

      switch (cpRps1) {
        case ROCK:
          System.out.println("コンピュータ1：グー" + "\r\n");
          break;
        case SCISSORS:
          System.out.println("コンピュータ1：チョキ" + "\r\n");
          break;
        case PAPER:
          System.out.println("コンピュータ1：パー" + "\r\n");
          break;
        default:
          // 処理なし：想定外の値
          break;
      }
      // コンピュータ２の手
      RPSPlayer cp2 = new RPSPlayer();
      RPSType cpRps2 = cp2.go();

      switch (cpRps2) {
        case ROCK:
          System.out.println("コンピュータ2：グー" + "\r\n");
          break;
        case SCISSORS:
          System.out.println("コンピュータ2：チョキ" + "\r\n");
          break;
        case PAPER:
          System.out.println("コンピュータ2：パー" + "\r\n");
          break;
        default:
          // 処理なし：想定外の値
          break;
      }
      cntResult++;
      // 勝ち負け判定
      if (cpRps1.getId() != cpRps2.getId()) {

        switch (cpRps1) {
          case ROCK:
            if (cpRps2 == RPSType.SCISSORS) {
              System.out.println("コンピュータ1の勝ち！");
              cp1Win++;     // コンピュータ1勝利の場合
            }else {
              System.out.println("コンピュータ2の勝ち！");
              cp2Win++;     // コンピュータ2勝利の場合
            }
            break;
          case SCISSORS:
            if (cpRps2 == RPSType.PAPER) {
              System.out.println("コンピュータ1の勝ち！");
              cp1Win++;
            }else {
              System.out.println("コンピュータ2の勝ち！");
              cp2Win++;
            }
            break;
          case PAPER:
            if (cpRps2 == RPSType.ROCK) {
              System.out.println("コンピュータ1の勝ち！");
              cp1Win++;
            }else {
              System.out.println("コンピュータ2の勝ち！");
              cp2Win++;
            }
            break;
          default:
            // 処理なし：想定外の値
            break;
        }
      } else {          // あいこの試合数はノーカウント
        cntResult--;
        i--;
        System.out.println("あいこ！！もう一回！！" + "\r\n");
      }
      
      // 結果表示
      System.out.println("コンピュータ1の勝利数：" + cp1Win + "\r\n" + "コンピュータ2の勝利数："
          + cp2Win + "\r\n" + "試合数：" + cntResult + "\r\n");
    }
    System.out.println("試合数が50回を超えたので終了します。");
  }

}
