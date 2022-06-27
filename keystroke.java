package keystroke;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class keystroke extends Frame implements KeyListener/* キーリスナークラスを実装 */ {
	int keycode, key;
	String keytext;
	long start, stop, interval;
	boolean shift = false;

	//コンストラクタ
	keystroke() {

		//テキストフィールドを構築
		TextField tf1 = new TextField();
		tf1.addKeyListener(this);
		add(tf1);

		//画面サイズを設定
		setSize(500, 500);
		show();
	}

	//キーを押したときに呼ばれるメソッド
	public void keyPressed(KeyEvent e) {

		//キーを押したときの時刻をミリ秒で取得
		start = System.currentTimeMillis();

		//押したキーを取得(int型のキーコードを取得する．キーコードからString型の文字列を取得する)，出力
		key = e.getKeyCode();

		if ((key & e.SHIFT_DOWN_MASK) != 0) {
			int keycode = e.getKeyCode();
			String keytext = e.getKeyText(keycode);
			System.out.println("Press" + keytext);
		} else {
			int keycode = e.getKeyCode();
			String keytext = e.getKeyText(keycode);
			System.out.println("Press" + keytext);
		}

		//FileWriteStringメソッドの呼び出し
		FileWriteString(keytext);

	}

	//キーを離したときに呼ばれるメソッド
	public void keyReleased(KeyEvent e) {

		//キーを離したときの時刻をミリ秒で取得
		stop = System.currentTimeMillis();

		//離したキーを取得(int型のキーコードを取得する．キーコードからString型の文字列を取得する)，出力
		int keycode = e.getKeyCode();
		String keytext = e.getKeyText(keycode);
		System.out.println("Release" + keytext);

		//Press-Release間隔(キーを押してから離すまでの時間間隔(interval))を求め，出力
		interval = stop - start;
		System.out.println(interval + "ms");

		//FileWriteString (keytext);

		int it = (int) interval;
		//FileWriteIntメソッドの呼び出し
		FileWriteInt(it);
		System.out.println();
	}

	//キーをタイプしたときに呼ばれるメソッド
	public void keyTyped(KeyEvent e) {
	}

	//メインメソッド
	public static void main(String[] args) {
		new keystroke();
	}

	//FileWriteStringメソッド
	//押した(離した)キーの種類をデスクトップのdata.txtファイルに出力
	public static void FileWriteString(String s) {
		try {
			//ユーザ名の部分に注意
			FileWriter fw = new FileWriter("C:\\Documents and Settings\\20fi067\\Desktop\\data.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.print("押したキー" + s + ":");
			pw.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	//FileWriteIntメソッド
	//Press-Release間隔をデスクトップのdata.txtファイルに出力
	public static void FileWriteInt(int i) {
		try {
			//ユーザ名の部分に注意
			FileWriter fw = new FileWriter("C:\\Documents and Settings\\20fi067\\Desktop\\data.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.println("処理時間" + i + "ms");
			//pw.println();
			pw.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}
}