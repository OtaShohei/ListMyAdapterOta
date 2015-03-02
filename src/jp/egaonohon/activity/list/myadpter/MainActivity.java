package jp.egaonohon.activity.list.myadpter;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

/**
 *
 * @author 1107AND
 *
 */
public class MainActivity extends Activity {

	/**
	 * setOnItemClickListenerでListViewを扱うために、メンバ変数として宣言しておく。
	 */
	ListView list = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/**
		 * 元データの持ち方はSimpleAdapterクラスを用いた時と同様。
		 */
		String titles[] = { "風の歌を聴け", "1973年のピンボール", "羊をめぐる冒険",
				"世界の終りとハードボイルド・ワンダーランド", "ノルウェイの森" };

		/**
		 * 画像を配列に格納するときは、BitmapFactory.decodeResource()でRの参照を持ってきて格納するみたい。
		 */
		Bitmap coverPh[] = {
				BitmapFactory.decodeResource(getResources(), R.drawable.kaze),
				BitmapFactory.decodeResource(getResources(), R.drawable.m1973),
				BitmapFactory.decodeResource(getResources(), R.drawable.hituji),
				BitmapFactory.decodeResource(getResources(), R.drawable.sekai),
				BitmapFactory.decodeResource(getResources(), R.drawable.norway) };
		String descs[] = { "講談社刊。1979年7月25日発売。『群像』1979年6月号掲載。",
				"講談社刊。1980年6月20日発売。『群像』1980年3月号掲載。",
				"講談社刊。1982年10月15日発売。『群像』1982年8月号掲載。", "新潮社刊。1985年6月15日発売。",
				"講談社刊。1987年9月10日発売。上下二分冊で刊行された。" };

		/**
		 * HashMapではなくなったのが違い。 エンティティとして作成したクラスであるListItem型を格納するArrayListになっている。
		 */
		ArrayList<ListItem> data = new ArrayList<ListItem>();
		for (int i = 0; i < titles.length; i++) {
			ListItem item = new ListItem();
			item.setId((new Random()).nextLong());
			item.setImagaData(coverPh[i]);
			item.setTitle(titles[i]);
			item.setDesc(descs[i]);
			data.add(item);
		}
		MyListAdapter adapter = new MyListAdapter(this, data,
				R.layout.list_item);
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(adapter);

		/**
		 * ラジオボタン選択時の処理 このリスナーの中で、
		 *
		 * CharSequence msg = ((TextView) view).getText();
		 *
		 * としてテキストを引っ張ってくるだけでラジオボタンで選択されているテキストを拾ってくるようになる。
		 *
		 */
		// list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		// public void onItemClick(AdapterView<?> av, View view, int position,
		// long id) {
		// CharSequence msg = ((TextView) view).getText();
		// Toast.makeText(MainActivity.this,
		// String.format("選択したのは%s", msg.toString()),
		// Toast.LENGTH_SHORT).show();
		// }
		// });

		/**
		 * チェックボックス選択時の処理
		 */
		// list.setOnItemClickListener(
		// new AdapterView.OnItemClickListener() {
		// public void onItemClick(AdapterView<?> av,
		// View view, int position, long id) {
		// String msg = "選択したのは、";
		//
		// /**
		// * チェックボックスが選択されたら、
		// * getChildCountでリストビューの長さを取得
		// * リストビューの行をlist.getChildAt(i)で1つずつ引っ張ってきて
		// * チェックされているなら　check.isChecked　、メッセージを足し込んでいく。check.getText()
		// */
		//
		// for (int i = 0; i < list.getChildCount(); i++) {
		// CheckedTextView check = (CheckedTextView) list.getChildAt(i);
		// if (check.isChecked()) {
		// msg += check.getText() + ",";
		// }
		// }
		// /**
		// * substring()メソッドで、文字列の最初（0）からメッセージの長さから-1番目までを切り出すことで、「,」を削除している。
		// */
		// msg = msg.substring(0, msg.length() - 1);
		// Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
		// }
		// }
		// );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
