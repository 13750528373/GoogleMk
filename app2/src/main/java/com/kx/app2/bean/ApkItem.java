package com.kx.app2.bean;

/**
 * Created by KX on 2017/9/6.
 */

public class ApkItem {

    /**
     * des : 鍙风爜鐧句簨閫氣€濇槸涓€娆炬暣鍚堢敓娲绘湇鍔′俊鎭拰瀵艰埅鐨勫鍔熻兘鎵嬫満杞欢锛屾彁渚涘叏鏂逛綅涓板瘜鐑棬鐢�
     * downloadUrl : app/com.besttone.hall/com.besttone.hall.apk
     * iconUrl : app/com.besttone.hall/icon.jpg
     * id : 1575518
     * name : 鍙风爜鐧句簨閫�
     * packageName : com.besttone.hall
     * size : 7885527
     * stars : 3
     */

    private String des;
    private String downloadUrl;
    private String iconUrl;
    private int id;
    private String name;
    private String packageName;
    private int size;
    private float stars;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
