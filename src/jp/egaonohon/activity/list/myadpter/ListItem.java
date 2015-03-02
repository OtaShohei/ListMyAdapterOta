package jp.egaonohon.activity.list.myadpter;

import android.graphics.Bitmap;

/**
 * エンティティのクラス。 入れ物だけを格納。
 *
 * @author 1107AND
 *
 */
public class ListItem {
	/**
	 * メンバ変数。coverPh、title、desc
	 * 画像は、Bitmapクラスに格納する。
	 */
	private long id = 0;
	private Bitmap coverPh;
	private String title = null;
	private String desc = null;

	/**
	 * セッターとゲッター
	 *
	 * @return
	 */

	public long getId() {
		return id;
	}

    public Bitmap getImageData() {
        return coverPh;
    }

	public String getTitle() {
		return title;
	}


	public String getDesc() {
		return desc;
	}

	public void setId(long id) {
		this.id = id;
	}

    public void setImagaData(Bitmap image) {
    	coverPh = image;
    }

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
