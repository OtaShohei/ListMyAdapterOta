package jp.egaonohon.activity.list.myadpter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 * @author 1107AND
 *
 */
public class MyListAdapter extends BaseAdapter {

	/**
	 * メンバ変数
	 */
	private Context context = null;
	private ArrayList<ListItem> data = null;// ListItem型が入るArrayList
	private int resource = 0;// 一行の書式を保存してある。

	/**
	 * MyListAdapterについては、MainActivityと照らしあわせて確認してみよう。
	 *
	 *
	 * @param context
	 * @param data
	 * @param resource
	 */
	public MyListAdapter(Context context, ArrayList<ListItem> data, int resource) {
		this.context = context;
		this.data = data;
		this.resource = resource;
	}

	/**
	 * BaseAdapterを継承してオーバーライドしたメソッド
	 * Listに、データの個数を返却するメソッド。自分のAdapterの中に何個入っていますか?　を知らせる。
	 */
	public int getCount() {
		return data.size();
	}

	/**
	 * BaseAdapterを継承してオーバーライドしたメソッド Adapterに登録されているデータを返却するメソッド。引数（int
	 * position）が何行目かを0オリジンで記述したもの。位置を示す。
	 * 一番先頭行ならば0でくる。その時に入っているデータは何なのかを(Adapterを利用するListViewに)返却する。
	 * ちなみに、Adapterの利用主なのでたまたま今回ListViewだっただけ。
	 */
	public Object getItem(int position) {
		return data.get(position);
	}

	/**
	 * BaseAdapterを継承してオーバーライドしたメソッド Adapterに登録されている、個別のオブジェクトのID番号を返却する。
	 * Databaseでいうところのユニークなレコード番号みたいなもの。単純なポジションではなく、振られた番号を返却する。ユニークな番号。
	 *
	 * ここまでのオーバーライドは定石の書き方で大丈夫。
	 */
	public long getItemId(int position) {
		return data.get(position).getId();
	}

	/**
	 * このgetView()メソッドがポイント!! 一行のビューを作成して返却するメソッド。
	 * このメソッドで、データと行の見た目の対応付けが行われる。Viewが戻り値になっているが、それが一行のフォーマットとなる。
	 * イメージを返したり、テキストを返したり。データとの対応付けが行われていないといけない。
	 * ActivityでいうところのsetContentViewみたいなもの。ここをいい加減に書くと表示が大変なことになる。
	 *
	 * 第1引数(int position):何番目の行かを示す。一行ごとに呼ばれるから。今回作成するデータ番号を示す。 第2引数(View
	 * convertView):あとで大活躍する。再利用可能な行のオブジェクト。
	 * スクロールされて画面に表示されなくなった行を再利用して（上書きして）次に表示される行にする。
	 * したがって一番最初の表示時はnull。数百行をnewするとメモリの無駄だから。 第3引数(ViewGroup
	 * parent):ListViewの参照。getViewは一行一行表示するので元の参照を入れておく。
	 */
	public View getView(int position, View convertView, ViewGroup parent) {

		/**
		 * やることその1 表示する元データを取得する。次は10番目だぞ、とか。そんな感じ。 itemにタイトルやその他の情報が入っている。
		 */
		ListItem item = (ListItem) getItem(position);

		/**
		 * やることその2 一行毎のレイアウトを作成する。このときにLayoutInflater()を使う。 xmlからインスタンスを作る。
		 */
		Activity activity = (Activity) context;

		/**
		 * resourceには一行のフォーマット（List_Item.xml）が入っている。 結果的にvの中に、画面の書式が生成される。
		 * list_item.xmlで定義されている一行のレイアウトがLinearLayoutなのでLinearLayout vにしている。
		 *
		 * ただ、View vでも実はいい。
		 *
		 * Activityならセットコンテントビューすればxmlをviewにできたが、ここではgetLayoutInflater(
		 * Activityのメソッド)でxmlをviewにしている。
		 * inflateはLayoutInflaterのメソッド。リソースIDにしたがってviewを生成。 (LinearLayout)
		 * でダウンキャストしている。
		 */
		LinearLayout v = (LinearLayout) activity.getLayoutInflater().inflate(
				resource, null);
		/**
		 * やることその3 画面のオブジェクトが生成されるので、そこにデータを設定する。 元のデータはitemに入っている。
		 * item.getTitle()でタイトルを取得してtitle位置に設定していく…というのをTagとDescでも繰り返す。
		 */
		((ImageView) v.findViewById(R.id.coverPh)).setImageBitmap(item.getImageData());
		((TextView) v.findViewById(R.id.title)).setText(item.getTitle());
		((TextView) v.findViewById(R.id.desc)).setText(item.getDesc());
		return v;// 一行分のデータをレイアウトにはめ込み終えたのでvを戻り値として戻す。
	}
}
