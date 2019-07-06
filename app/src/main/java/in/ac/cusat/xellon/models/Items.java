package in.ac.cusat.xellon.models;

public class Items {
    public String author;
    public String categ;
    public String cno;
    public String dec;
    public String price;
    public String title;
    public String image;

    public Items() {
    }

    public Items(String author, String categ, String cno, String dec, String price, String title,String image) {
        this.author = author;
        this.categ = categ;
        this.cno = cno;
        this.dec = dec;
        this.price = price;
        this.title = title;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


